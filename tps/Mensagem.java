package tps;

public abstract class Mensagem {
    private int ID;

    public static final int TypeReta = 1;
    public static final int TypePara = 2;
    public static final int TypeCurvaDireita = 3;
    public static final int TypeCurvaEsquerda = 4;


    public Mensagem() {
        this.ID = ID;
    }

    public void setID(int novoID) {
        this.ID = novoID;
    }

    public int getIDMensagem() {
        return ID;
    }


    public abstract int[] obterMensagem();

    public String toString() {
        return "id: " + obterMensagem()[0] + ", tipo: " + obterMensagem()[1] + ", valor: " + obterMensagem()[2] + ", " + obterMensagem()[3];

    }


}
