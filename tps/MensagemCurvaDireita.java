package tps;

public class MensagemCurvaDireita extends Mensagem {

    public short raio;
    public short grau;
    public short tipo; //= Mensagem.TypeCurvaDireita;

    public MensagemCurvaDireita(short raio, short angulo, short ID) {
        super(Mensagem.TypeCurvaDireita, raio, angulo, ID);

    }public short getRaio() {
        return this.getParam1();
    }

    public short getAngulo() {
        return this.getParam2();
    }

    @Override
    public String toString() {
        return "Curva Direita com Raio: "+ getRaio() + " e Angulo: " + getAngulo() + " id: " + Integer.toString(super.getid());
    }

}
