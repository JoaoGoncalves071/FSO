package tps;

import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class CanalComunicacao {
    //ficheiro
    private File ficheiro;

    // canal que liga o conteúdo do ficheiro ao buffer
    private FileChannel canal;

    //cadeado do canal
    private FileLock lock;

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
        if(this.ficheiro!=null){
            try{
                this.lock = this.canal.lock();

                buffer.position(0);
                int ID = buffer.getInt(0);
                int Type = buffer.getInt(1);
                int val1 = buffer.getInt(2);
                int val2 = buffer.getInt(3);

                Mensagem msg;

                switch (Type) {
                    case Mensagem.TypeReta:
                        msg = new MensagemReta(val1);
                        msg.setID(ID);
                        return msg;

                    case Mensagem.TypePara:
                        boolean val = (val1 == 1) ? true : false;
                        msg = new MensagemParar(val);
                        msg.setID(ID);
                        return msg;

                    case Mensagem.TypeCurvaDireita:
                        msg = new MensagemCurvaDireita(val1, val2);
                        msg.setID(ID);
                        return msg;

                    case Mensagem.TypeCurvaEsquerda:
                        msg = new MensagemCurvaEsquerda(val1, val2);
                        msg.setID(ID);
                        return msg;
                }
            } catch (Exception e){
                System.out.println("Erro ao receber mensagem -> " + e.getMessage());
            }
        }
        return null;
    }


    /*
     * Enviae uma Mesagem para o Buffer
     */
    public void enviarMensagem(Mensagem msg)  {
        if(this.ficheiro != null) {
            try {
                // Obter as varias infos do obterMensagem - (ID,type,value(radius,angle))
                int[] mensagem = msg.obterMensagem();
                int id = mensagem[0];
                id++;
                msg.setID(id);

                // As mensagens têm de ficar à espera, de forma ordenada, enquanto a mensagem previamente escrita ainda não tiver sido lida
                // A primeira a sair corresponde à que incrementa de 1 ID da ùltima mensagem no buffer
                while(this.lastID < 10){
                    this.lock = this.canal.lock();
                    //buffer.position(0); --- antigo
                    this.buffer.position(id);
                    for(int i=0; i<mensagem.length;i++){
                        this.buffer.putInt(i);
                    }
                    this.lastID = id;
                    this.lock.release();
                }
                /* antigo
                this.lock = this.canal.lock();
                //Começar a escrever no buffer a Mensagem
                this.buffer.position(0);
                for(int i=0; i<mensagem.length;i++){
                    this.buffer.putInt(i);
                }
                this.lock.release();

                 */

                System.out.println("Mensagem Enviada ----> "+ msg.toString());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
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
