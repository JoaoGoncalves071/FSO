package tps;

public class teste {

    public static void main(String[] args) {

        Mensagem reta = new MensagemReta(12);
        System.out.println(reta.toString());

        Mensagem parar = new MensagemParar(true);
        System.out.println(parar.toString());

        Mensagem curvaDireita = new MensagemCurvaDireita(20, 10);
        System.out.println(curvaDireita.toString());

        Mensagem curvaEsquerda = new MensagemCurvaEsquerda(50, 10);
        System.out.println(curvaEsquerda.toString());

    }

}
