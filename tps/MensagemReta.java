package tps;

public class MensagemReta extends Mensagem {
    private short distancia;
    private short tipo;

    public MensagemReta(short distancia,short ID) {
        super(Mensagem.TypeReta,distancia,(short)0, ID);
        this.setParam1(distancia);
    }

    public short getDistancia() {
        return this.getParam1();
    }

    @Override
    public String toString() {
        return "Reta: "+ this.getParam1() + " id: "+ Integer.toString(super.getid());
    }




}
