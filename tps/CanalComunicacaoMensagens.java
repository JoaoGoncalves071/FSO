package tps;

import java.io.IOException;
import java.nio.channels.FileLock;

public class CanalComunicacaoMensagens extends CanalComunicacao {

    private final int[] ArrayPos= {6,14,22,30};



    public boolean enviarMensagem(Mensagem msg) {
        FileLock fl = null;

        try {
            fl = this.canal.lock();

            System.out.println("Waiting to write...");
            buffer.position(0);
            int numM = buffer.getShort();
            if(numM>=4) {
                System.out.println("Channel full");
                return false;
            }

            System.out.println("Writing ...");

            //ler pos para escrever
            buffer.position(2);
            int pos = buffer.getShort();

            buffer.position(pos);
            buffer.putShort(msg.getType());
            buffer.putShort(msg.getParam1());
            buffer.putShort(msg.getParam2());
            numM+=1;

            System.out.println("Mensagem "+ (int)msg.getType() + ", " + (int)msg.getParam1() +", "+ (int)msg.getParam2()+ " written in position: " + pos);

            //atualizar numero de mensagens
            buffer.position(0);
            buffer.putShort((short)numM);

            // atualizar idPut
            if(pos>=37) {
                pos = ArrayPos[0];
            }else {
                pos=pos+8;
            }
            buffer.putShort((short)pos);



        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao fazer lock");
        }finally {
            if(fl!=null) {
                try {
                    fl.release();
                }catch(IOException e) {
                    throw new IllegalArgumentException("Erro ao fazer unlock");
                }
            }
        }

        return true;
    }



    public Mensagem receberMensagemM() {
        FileLock fl = null;
        Mensagem res = null;

        try {
            fl = this.canal.lock();

            buffer.position(0);
            int numM = buffer.getShort();


            if(numM == 0) {
                return res;
            }

            buffer.position(4);
            int idget = buffer.getShort();

            buffer.position(idget);
            short type = buffer.getShort();
            short para1 = buffer.getShort();
            short para2 = buffer.getShort();
            short id = buffer.getShort();

            switch(type) {
                case(Mensagem.TypeReta):
                    res = new MensagemReta(para1, id);
                    break;
                case(Mensagem.TypeCurvaDireita):
                    res = new MensagemCurvaDireita(para1, para2, id);
                    break;
                case(Mensagem.TypeCurvaEsquerda):
                    res = new MensagemCurvaEsquerda(para1, para2, id);
                    break;
                case(Mensagem.TypePara):
                    res = new MensagemParar(para1, id);
                    break;
                default:
                    System.out.println("MENSAGEM INEXISTENTE");
            }

            //atualiza??????o do n???mero de mensagens
            numM-=1;
            buffer.position(0);
            buffer.putShort((short)numM);

            //atualizar o idGet
            if(idget>=37) {
                idget = 6;
            }else {
                idget = idget + 8;
            }
            buffer.position(4);
            buffer.putShort((short)idget);

            return res;


        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao fazer lock");
        }finally {
            if(fl!=null) {
                try {
                    fl.release();
                }catch(IOException e) {
                    throw new IllegalArgumentException("Erro ao fazer unlock");
                }
            }
        }
    }




















}
