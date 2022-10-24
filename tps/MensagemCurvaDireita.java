package tps;

public class MensagemCurvaDireita extends Mensagem {

    public int raio;
    public int grau;
    //public int tipo; //= Mensagem.TypeCurvaDireita;

    public MensagemCurvaDireita(int raio, int grau) {
        super(Mensagem.TypeCurvaDireita);
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
        return Mensagem.TypeCurvaDireita;
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
