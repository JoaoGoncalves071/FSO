package tps;

public class Recetor {

    private CanalComunicacao canal;

    public Recetor(String[] args) {
        canal = new CanalComunicacao();
        this.canal.abrirCanal();
    }

    public static void main(String[] args) {
        Recetor r = new Recetor( args );
        System.out.println("RecetorLocks a funcionar...");
        r.run();

    }

    private void run() {
        String lastValidMessage = "";

        for(;;) {
            String currentMessage = this.canal.receberMensagem();
            int aux = currentMessage.compareTo( lastValidMessage);

            if(aux!=0) {
                System.out.println( currentMessage);

                lastValidMessage = currentMessage;
            }


        }

    }
}
