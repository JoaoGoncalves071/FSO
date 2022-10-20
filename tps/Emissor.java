package tps;

import java.util.Random;

public class Emissor {
    private final CanalComunicacao channel;
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
        this.channel = this.getCanal();
        this.channel.abrirCanal(args[0]);
        this.promptMSG = args[1];
    }

    private CanalComunicacao getCanal() {
        return new CanalComunicacao();
    }

    public void run() {

        for(int i=0;; ++i) {
            this.msg = new MensagemReta(i);
            this.channel.enviarMensagem(this.msg);
            try {
                Thread.sleep(450 + rnd.nextInt(750));
            }catch(InterruptedException e){
                System.out.println("Fui acordado...");
            }

        }
    }
}
