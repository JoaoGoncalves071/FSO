package tps;

public class MensagemParar extends Mensagem{
    private boolean valor;

    public MensagemParar(boolean valor, int tipo) {
        super(tipo);
        this.valor = valor;
    }

    public boolean getValor(){
        return this.valor;
    }

    public String toString(){
        return super.toString() + "Parar:< "+ this.valor +" >";
    }
}
