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
    protected FileChannel canal;
    //buffer
    protected MappedByteBuffer buffer;
    // protected máxima em bytes do buffer
    final int BUFFER_MAX = 55;
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
    public boolean abrirCanal(String pathname) {
        // cria um ficheiro com o nome comunicacao.dat
        ficheiro = new File(pathname);
        // cria um canal de comunica��o de leitura e escrita
        try {
            canal = new RandomAccessFile(ficheiro, "rw").getChannel();
        } catch (FileNotFoundException e) {
            return false;
        }
        // mapeia para mem�ria o conte�do do ficheiro
        try {
            buffer = canal.map(FileChannel.MapMode.READ_WRITE, 0, BUFFER_MAX);
        } catch (IOException e) {
            return false;
        }

        //Iniciar o buffer circular
        buffer.position(0);
        //primeiro espa�o - n�mero de mensagens
        buffer.putShort((short) 0);
        //segundo espa�o - idPut
        buffer.putShort((short) 6);
        //terceiro espa�o - idGet
        buffer.putShort((short) 6);
        System.out.println("Buffer inicializado ");
        return true;

    }


    /*
     * Receber uma mensagem do Buffer
     */
    public String receberMensagem() {
        String msg = new String();
        char c;
        buffer.position(0);
        while ((c = buffer.getChar()) != '\0')
            msg += c;
        return msg;

    }

    /*
     * Enviae uma Mesagem para o Buffer
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
