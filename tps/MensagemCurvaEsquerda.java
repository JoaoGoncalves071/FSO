package tps;

public class MensagemCurvaEsquerda extends Mensagem {


    public MensagemCurvaEsquerda(short raio,short angulo,short ID) {
        super(Mensagem.TypeCurvaEsquerda,raio,angulo,ID);

    }
    public short getRaio() {
        return this.getParam1();
    }
    public short getAngulo() {
        return this.getParam2();
    }

    @Override
    public String toString() {
        return "Curva Esquerda de Raio: "+ getRaio() + " e Angulo: " + getAngulo() + " id: " + Integer.toString(super.getID());
    }


}
