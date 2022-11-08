package tps;

import java.util.Random;

public class Emissor {
    private final CanalComunicacaoMensagens canal;
    private final Random rnd;
    private Mensagem msg;

    private String promptMSG;



    public static void main(String[] args) {
        Emissor e = new Emissor(args);
        System.out.println("EmissorLocks a funcionar");
        e.run();
    }

    public Emissor(String[] args) {
        this.rnd = new Random();
        this.canal = this.getCanal();
        this.canal.abrirCanal(args[0]);
        //this.promptMSG = args[1];
    }

    private CanalComunicacaoMensagens getCanal() {
        CanalComunicacaoMensagens channel = new CanalComunicacaoMensagens();
        return channel;
    }

    public void run() {
        int pos = 0;
        for(int i=0;; ++i) {
            this.msg = new MensagemCurvaEsquerda((short) 30,(short) 20 ,(short) i);
            boolean res;
            do {
                res = this.canal.enviarMensagem(this.msg);
            }while(!res);
            //this.msg.setID(i);
            //his.canal.enviarMensagem(this.msg);
            try {
                Thread.sleep(450 + rnd.nextInt(750));
            }catch(InterruptedException e){
                System.out.println("Fui acordado...");
            }

        }
    }
}
