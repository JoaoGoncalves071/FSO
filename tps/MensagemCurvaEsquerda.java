package tps;

public class MensagemCurvaEsquerda extends Mensagem {

    public int raio;
    public int grau;

    public MensagemCurvaEsquerda(int raio, int grau) {
        super(Mensagem.TypeCurvaEsquerda);
        this.raio = raio;
        this.grau = grau;

    }

    /*public void setType(int tipo) {
        this.tipo = tipo;
    }*/

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    /*public int getType() {
        return Mensagem.TypeCurvaEsquerda;
    }*/

    public int getRaio() {
        return raio;
    }

    public int getAngulo() {
        return grau;
    }

    @Override
    public int[] obterMensagem() {
        return new int[]{getID(), getType(), getRaio(), getAngulo()};
    }

}
