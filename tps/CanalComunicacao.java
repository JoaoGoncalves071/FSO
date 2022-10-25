package tps;

import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.IllformedLocaleException;

public class CanalComunicacao {
    //ficheiro
    private File ficheiro;

    // canal que liga o conteúdo do ficheiro ao buffer
    private FileChannel canal;

    //cadeado do canal
    private FileLock lock;
    private int[] ArrayPosicoes = {6,12,18,24};

    //buffer
    private MappedByteBuffer buffer;

    // dimensão máxima em bytes do buffer
    final int BUFFER_MAX = 30;

    // Variáveis auxiliares ao ID
    private int currentID;
    private int lastID;

    // Indica se se pode escrever
    private boolean canWrite;


    // construtor onde se cria o canal
    public CanalComunicacao() {

        //Construtor
        ficheiro = null;
        lock = null;
        canal = null;
        buffer = null;
    }

    /*
     * Método que abre canal de comunicacao
     */
    public boolean abrirCanal(String pathname) {
        // cria um ficheiro com o nome comunicacao.dat
        this.ficheiro = new File(pathname);

        try {
            canal = new RandomAccessFile(ficheiro, "rw").getChannel();
            System.out.println("Channel Open!");

        } catch (FileNotFoundException e) {
            return false;
        }
        // mapeia para memória o conteúdo do ficheiro
        try {
            buffer = canal.map(FileChannel.MapMode.READ_WRITE, 0, BUFFER_MAX);

        } catch (IOException e) {
            return false;
        }
        return true;
    }


    /*
     * Receber uma mensagem do Buffer
     */
    public Mensagem receberMensagem() {
		FileLock f1 = null;
		Mensagem res = null;
            try{
                f1 = this.canal.lock();
                //System.out.println("Espera....");
                
                buffer.position(0);
                
                int num = buffer.getShort();
                if(num == 0) {
                	return res;
                }
                buffer.position(4);
                int idget = buffer.getShort();
                
                buffer.position(idget);
                int Type = buffer.getShort();
                int val1 = buffer.getShort();
                int val2 = buffer.getShort();
                
                //Mensagem msg;
                switch (Type) {
                    case (Mensagem.TypeReta):
                        res = new MensagemReta(val1);
                        break;
                    case (Mensagem.TypePara):
                        boolean val = (val1 == 1) ? true : false;
                        res= new MensagemParar(val);
                        break;
                    case (Mensagem.TypeCurvaDireita):
                        res = new MensagemCurvaDireita(val1, val2);
                        break;
                    case (Mensagem.TypeCurvaEsquerda):
                        res = new MensagemCurvaEsquerda(val1, val2);
                        break;
                    default:
        				System.out.println("MENSAGEM INEXISTENTE");
            
                }
                
                //atualizar o numero da mensagem
                num-=1;
                buffer.position(0);
                buffer.putShort((short)num);
                
                //atualizar idGet
                if(idget>=24) {
                	idget=6;
                }else {
                	idget = idget+6;
                }
                buffer.position(4);
                buffer.putShort((short)idget);
                return res;
            } catch (IOException e){
                throw new IllegalArgumentException("Erro ao fazer lock");
            }finally {
            	if(f1 != null) {
            		try {
            			f1.release();
            		}catch (IOException e) {
						throw new IllegalArgumentException("Erro ao fazer unlock");
					}
            	}
            }
    }


    /*
     * Enviae uma Mesagem para o Buffer
     */
    public boolean enviarMensagem(Mensagem msg)  {
    		FileLock f1 = null;
    		try {
    			f1 = this.canal.lock();
    			System.out.println("A espera...");
    			buffer.position(0);
    			
    			System.out.println("A escrever");
    			
    			//ler pos para escrever
    			buffer.position(2);
    			int pos = buffer.getShort();
    			int[] mensagem = msg.obterMensagem();
    			
    			int val = mensagem[0];
    			
    			if(val>=4) {//mudar dependento do numero do grupo
    				System.out.println("Canal cheio");
    				return false;
    			}
    			
    		
    			buffer.position(pos);
    			buffer.putShort((short)mensagem[1]);//tipo
    			buffer.putShort((short)mensagem[2]);//val1
    			buffer.putShort((short)mensagem[3]);//val2
    			val+=1;

    			System.out.println(msg.toString()+ " na posição " + pos);
    			//atualizar o numero de mensagens
    			buffer.position(0);
    			buffer.putShort((short)val);
    			
    			//Atualizar id
    			if(pos >= 24) {
    				pos = ArrayPosicoes[0];
    			}else {
    				pos = pos+6;
    			}
    			buffer.putShort((short)pos);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			throw new IllegalArgumentException("Erro a fazer lock");
    		}finally {
    			if(f1!=null) {
    				try {
    					f1.release();
    				}catch (Exception e) {
						throw new IllformedLocaleException("Erro a fazer unlock");
					}
    			}
    		}
    		return true;
    		
    	}
        




    /*
     * fecha o canal de comunicação
     */
    public void fecharCanal() {
        if (canal != null) {
            try {
                canal.close();
            } catch (Exception e) {
                canal = null;
            }
        }
    }


}
