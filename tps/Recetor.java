package tps;

import java.util.Objects;

public class Recetor {

    private final CanalComunicacaoMensagens canal;

    public Recetor(String[] args) {
        canal = new CanalComunicacaoMensagens();
        this.canal.abrirCanal(args[0]);
    }

    public static void main(String[] args) {
        Recetor r = new Recetor(args);
        System.out.println("RecetorLocks a funcionar...");
        r.run();

    }

    private void run() {
        for (; ; ) {

            Mensagem msgAtual = this.canal.receberMensagemM();
            if (Objects.nonNull(msgAtual)) {
                System.out.println(msgAtual.toString());

            }


        }

    }
}
