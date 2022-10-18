package tps;

import java.util.Random;

public class Emissor {
    private CanalComunicacao canal;
    private Random rnd;
    private Mensagem msg;



    public static void main(String[] args) {
        Emissor e = new Emissor( args );
        System.out.println("emissorLocks a funcionar");
        e.run();
    }

    public Emissor(String[] args) {
        this.rnd = new Random();
        this.canal = this.getCanal(args);

        this.canal.abrirCanal();

        //this.msg = args[1]; completar
    }

    private CanalComunicacao getCanal(String[] args) {
        CanalComunicacao channel = new CanalComunicacao();

        return channel;
    }

    public void run() {
        for(int i=0;; ++i) {
            this.canal.enviarMensagem(this.msg);
            try {
                Thread.sleep(250 + rnd.nextInt(750));
            }catch(InterruptedException e){
                System.out.println("Fui acordado...");
            }

        }
    }
}
