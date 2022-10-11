package tps;

public class MensagemParar extends Mensagem {
    private boolean valor;
    private int tipo;


    public MensagemParar(boolean valor) {
        super();
        this.tipo = TypePara;
        this.valor = valor;
    }

    public static int BoolToInt(boolean valor) {
        return valor ? 1 : 0;
    }

    public void setType(int tipo) {
        this.tipo = tipo;
    }

    public int getType() {
        return tipo;
    }

    public void setValor(boolean valor) {
        this.valor = valor;

    }

    public int getValor() {
        return BoolToInt(this.valor);

    }

    @Override
    public int[] obterMensagem() {
        return new int[]{getID(), getType(), getValor(), 0};
    }


}
