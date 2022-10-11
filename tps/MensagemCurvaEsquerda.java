package tps;

public class MensagemCurvaEsquerda extends Mensagem {

    public int raio;
    public int grau;
    public int tipo;

    public MensagemCurvaEsquerda(int raio, int grau) {
        super();
        this.tipo = TypeCurvaEsquerda;
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
