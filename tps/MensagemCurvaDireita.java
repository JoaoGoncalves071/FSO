package tps;

public class MensagemCurvaDireita extends Mensagem {

    public int raio;
    public int grau;
    public int tipo; //= Mensagem.TypeCurvaDireita;

    public MensagemCurvaDireita(int raio, int grau) {
        super();
        this.tipo = TypeCurvaDireita;
        this.raio = raio;
        this.grau = grau;

    }

    public void setType(int tipo) {
        this.tipo = tipo;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public int getType() {
        return tipo;
    }

    public int getRaio() {
        return raio;
    }

    public int getGrau() {
        return grau;
    }

    @Override
    public int[] obterMensagem() {
        return new int[]{getIDMensagem(), getType(), getRaio(), getGrau()};
    }

}
