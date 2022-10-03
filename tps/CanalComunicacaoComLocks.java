package tps;

import java.io.IOException;
import java.nio.channels.FileLock;

public class CanalComunicacaoComLocks extends CanalComunicacao {

    @Override
    public String receberMensagem() {
        FileLock l = null;

        try {
            l = this.canal.lock();
            return super.receberMensagem();
        }
        catch (IOException iEx) {
            throw new IllegalArgumentException("Erro ao obter o lock");
        }
        finally {
            if (l != null){
                try {
                    l.release();
                } catch (IOException iEx){
                    throw new IllegalArgumentException("Erro ao liberar o lock");
                }
            }
        }
    }

    public void enviarMensagem(String msg) {
        FileLock l = null;

        try {
            l = this.canal.lock();
            super.enviarMensagem(msg);
        }
        catch (IOException iEx) {
            throw new IllegalArgumentException("Erro ao obter o lock");
        }
        finally {
            if (l != null){
                try {
                    l.release();
                } catch (IOException iEx){
                    throw new IllegalArgumentException("Erro ao liberar o lock");
                }
            }
        }
    }

}
