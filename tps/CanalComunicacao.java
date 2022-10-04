package tps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;

public class CanalComunicacao {
    //ficheiro
    private File ficheiro;

    // canal que liga o conteúdo do ficheiro ao buffer
    private FileChannel canal;

    //buffer
    private MappedByteBuffer buffer;

    // dimensão máxima em bytes do buffer
    final int BUFFER_MAX = 30;

    // construtor onde se cria o canal
    public CanalComunicacao() {

        //Construtor
        ficheiro = null;
        canal = null;
        buffer = null;

    }


    /*
     * Método que abre canal de comunicacao
     */

    public boolean abrirCanal() {
        //cria um ficheiro com o nome comunicacao.dat
        ficheiro = new File("comunicacao.dat");

        //cria um canal de comunicação de leitura e escrita, "rw"
        try {
            canal = new RandomAccessFile(ficheiro, "rw").getChannel();
        } catch (FileNotFoundException e) {
            return false;
        }

        //mapeia para memória o conteúdo do ficheiro
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
    public void enviarMensagem(String msg) {
        char c;
        buffer.position(0);
        for (int i = 0; i < msg.length(); ++i) {
            c = msg.charAt(i);
            buffer.putChar(c);
        }
        buffer.putChar('\0');
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
