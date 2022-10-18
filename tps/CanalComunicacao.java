package tps;

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
    public boolean abrirCanal() {
        // cria um ficheiro com o nome comunicacao.dat
        ficheiro = new File("comunicacao.dat");
        //cria um canal de comunicação de leitura e escrita
        try {
            canal = new RandomAccessFile(ficheiro, "rw").getChannel();
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
     * recebe uma mensagem convertendo-a numa String
     */
    public String receberMensagem() {
        String msg = new String();
        buffer.position(0);
        char c = buffer.getChar();
        while (c != '\0') {
            msg += c;
        }
        return msg;
    }


    /*
     * envia uma String como mensagem
     */
    public void enviarMensagem(Mensagem msg)  {
        if(this.ficheiro != null){
            try {

                // Agora vamos obter as várias informações que temos de escrever no buffer - o tipo, o texto, e o ID
                int[] mensagem = msg.obterMensagem();
                int tipo = mensagem[1];
                int[] argumentos = {mensagem[2], mensagem[3]};
                int id = mensagem[0];
                id++;
                msg.setID(id);

                // As mensagens têm de ficar à espera, de forma ordenada, enquanto a mensagem previamente escrita ainda não tiver sido lida
                // usa-se um Thread.sleep até se ler 'r' no buffer. A primeira a sair corresponde à que incrementa de 1 ID da ùltima mensagem no buffer
                while (!canWrite || !(id == lastID + 1)) {
                    lock = canal.lock();
                    buffer.position(0);
                    if (buffer.getChar() == 'r')
                        canWrite = true;
                    lastID = buffer.getInt();
                    lock.release();

                    Thread.sleep(1);
                }
                lock = canal.lock();

                char c;
                buffer.position(0);
                buffer.putInt(msg.getID());
                lastID = msg.getID();

                for (int i = 0; i < argumentos.length; ++i) {
                    c = (char) argumentos[i];
                    buffer.putChar(c);
                }
                buffer.putChar('\0');
                lock.release();
                System.out.println("Mensagem Enviada -> " + msg.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    lock.release();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
