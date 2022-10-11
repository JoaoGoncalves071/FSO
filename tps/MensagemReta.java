package tps;

public class MensagemReta extends Mensagem {
    private int distancia;
    private int tipo;

    public MensagemReta(int distancia) {
        super();
        this.tipo = TypeReta;
        this.distancia = distancia;
    }

    public void setType(int tipo) {
        this.tipo = tipo;
    }

    public int getType() {
        return tipo;
    }

    public void setdistancia(int distancia) {
        this.distancia = distancia;

    }

    public int getdistancia() {
        return distancia;

    }

    @Override
    public int[] obterMensagem() {
        return new int[]{getID(), getType(), getdistancia(), 0};
    }

}
