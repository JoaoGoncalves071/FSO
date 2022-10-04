package tps;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class CanalComunicacaoComLocks extends CanalComunicacao {

    private File ficheiro;

    // canal que liga o conteúdo do ficheiro ao buffer
    private FileChannel canal;

    //buffer
    private MappedByteBuffer buffer;

    // dimensão máxima em bytes do buffer
    final int BUFFER_MAX = 30;

    @Override
    public String receberMensagem() {
        FileLock l = null;

        try {
            l = this.canal.lock();
            return super.receberMensagem();
        } catch (IOException iEx) {
            throw new IllegalArgumentException("Erro ao obter o lock");
        } finally {
            if (l != null) {
                try {
                    l.release();
                } catch (IOException iEx) {
                    throw new IllegalArgumentException("Erro ao liberar o lock");
                }
            }
        }
    }

    @Override
    public void enviarMensagem(String msg) {
        FileLock l = null;

        try {
            l = this.canal.lock();
            super.enviarMensagem(msg);
        } catch (IOException iEx) {
            throw new IllegalArgumentException("Erro ao obter o lock");
        } finally {
            if (l != null) {
                try {
                    l.release();
                } catch (IOException iEx) {
                    throw new IllegalArgumentException("Erro ao liberar o lock");
                }
            }
        }
    }

    // os métodos abrirCanal e fecharCanal são herdados da super classe

}
