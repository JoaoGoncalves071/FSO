package tps;

public abstract class Mensagem {
    public int num;
    public int type;
    public String text;

    public int getNum(){
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
