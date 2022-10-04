package tps;

public abstract class Mensagem {
    private int ID;

    private int tipo;

    private static final int TypeReta = 1;
    private static final int TypeCurvaDireita=2;
    private static final int TypeCurvaEsquerda = 3;
    private static final int TypePara=4;

    public Mensagem(int tipo){
        this.tipo = tipo;
    }
    public int getIDMensagem(){
        return ID;
    }
    public int getType(){
        return tipo;
    }
    public void setID(int novoID){
        this.ID = novoID;
    }
    public String toString(){
        return "ID: "+ID+" Tipo: "+tipo+ " Mensagem: ";
    }



}
