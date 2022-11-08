package tps;

public class MensagemParar extends Mensagem {



    public MensagemParar(short estado,short ID) {
        super(Mensagem.TypePara,estado,(short)0,ID);
    }

    public short getEstado() {
        return this.getParam1();
    }

    @Override
    public String toString() {
        return "Parado: "+ this.getParam1() + " id: " + Integer.toString(super.getid());
    }


}
