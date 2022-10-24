package tps;

public abstract class Mensagem {
    private int ID;

    private int tipo;
    public static final int TypeReta = 1;
    public static final int TypePara = TypeReta+1;
    public static final int TypeCurvaDireita = TypePara+1;
    public static final int TypeCurvaEsquerda = TypeCurvaDireita+1;


    public Mensagem(int tipo) {
        this.tipo = tipo;
        this.ID = ID;

    }

    public void setID(int novoID) {
        this.ID = novoID;
    }

    public int getID() {
        return ID;
    }


    public abstract int[] obterMensagem();

    public String toString() {
        return "id: " + obterMensagem()[0] + ", tipo: " + obterMensagem()[1] + ", valor: " + obterMensagem()[2] + ", " + obterMensagem()[3];

    }


    public int getType() {
        return this.tipo;
    }
}
