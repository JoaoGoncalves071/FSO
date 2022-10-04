package tps;

public abstract class Mensagem {

    // variables
    public int num;
    public int type;
    public String text;

    // number
    public int getNum(){
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    // message type
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    // message text
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
