package tps;

import java.util.Objects;

public class Recetor {

    private final CanalComunicacao canal;

    public Recetor(String[] args) {
        canal = new CanalComunicacao();
        this.canal.abrirCanal(args[0]);
    }

    public static void main(String[] args) {
        Recetor r = new Recetor(args);
        System.out.println("RecetorLocks a funcionar...");
        r.run();

    }

    private void run() {
        for(;;) {
            //Obter a mensagem que foi enviada para o buffer
           Mensagem msgRecebido = this.canal.receberMensagem();
           if(Objects.nonNull(msgRecebido)) {
               System.out.println(msgRecebido.toString());
           }
        }
    }
}
