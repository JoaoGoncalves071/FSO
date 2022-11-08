package tps;

public abstract class Mensagem {

    public static final short TypeReta = 0;
    public static final short TypePara = TypeReta+1;
    public static final short TypeCurvaDireita = TypePara+1;
    public static final short TypeCurvaEsquerda = TypeCurvaDireita+1;

    private short mensagemParam1;
    private short mensagemParam2;
    private short mensagemTipo;
    private short ID;


    public Mensagem(short mensagemTipo, short mensagemParam1, short mensagemParam2,short ID) {
        this.mensagemTipo = mensagemTipo;
        this.mensagemParam1 = mensagemParam1;
        this.mensagemParam2 = mensagemParam2;
        this.ID = ID;
    }

    public short getTipo() {
        return mensagemTipo;
    }

    public short getParam1() {
        return mensagemParam1;
    }
    public short getParam2() {
        return mensagemParam2;
    }
    public short getid() {
        return ID;
    }

    public short setTipo(short tipo) {
        tipo = this.mensagemTipo;
        return tipo;
    }

    public short setParam1(short param1) {
        param1 = this.mensagemParam1;
        return param1;
    }

    public short setParam2(short param2) {
        param2 = this.mensagemParam2;
        return param2;
    }

    public short id(short ID) {
        ID = this.ID;
        return ID;
    }

    public String toString() {
        return "Mensagem do tipo: " + Integer.toString(mensagemTipo) + ",com Param1 =  " + Integer.toString(mensagemParam1)+
                "e Param2 = " + Integer.toString(mensagemParam2) + " e id = " + Integer.toString(ID);
    }

	/*public boolean compareTo(Mensagem msg) {
		if(this.mensagemTipo == msg.mensagemTipo && this.mensagemParam1 == msg.mensagemParam1 && this.mensagemParam2 == msg.mensagemParam2) {
			return true;
		}
		return false;
	}*/







}
