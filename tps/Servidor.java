package tps;

import robot.RobotLegoEV3;

import java.time.Clock;
import java.util.ArrayList;

public class Servidor implements Runnable{
    private String name;
    private int angle;
    private int radius;
    private int dist;

    private CanalComunicacao cc = new CanalComunicacao();
    private RobotLegoEV3 robot = new RobotLegoEV3();

    private boolean robotOpen;

    private ArrayList<Mensagem> msgsRecebidas = new ArrayList<>();
    private ArrayList<Mensagem> msgsExe = new ArrayList<>();

    private boolean isExe;


    //clock de espera das mensagens
    private Clock clk = Clock.systemDefaultZone();
    private long waitTime, beginWait;

    /*
     * As variáveis dentro do método queremos que voltem ao seu valor "inicial"
     * sempre que for chamado o construtor
     */
    public Servidor(CanalComunicacao cc, boolean isExe) {
        this.cc = new CanalComunicacao();
        this.robot = new RobotLegoEV3();
        this.robotOpen = false;
        this.isExe = false;
        this.waitTime = 0;
        this.beginWait = 0;
    }

    //Name
    public void setName(String name){
        this.name = name;

    }
    public String getName(){
        return name;
    }

    //Open and close connection to robot
    public void Open() {
        robot.OpenEV3(getName());
        this.robotOpen = true;
    }
    public void Close() {
        robot.CloseEV3();
        this.robotOpen = false;
    }

    //Angle
    public void setAngle(int angle){
        this.angle = angle;
        System.out.println(this.angle);
    }
    public int getAngle(){
        return angle;
    }

    //Radius
    public void setRadius(int radius){
        this.radius = radius;
        System.out.println(this.radius);
    }
    public int getRadius(){
        return radius;
    }

    //Distance
    public void setDist(int dist){
        this.dist = dist;
        System.out.println(this.dist);
    }
    public int getDist(){
        return dist;
    }


    //Movement
    public void moveForward() {
        //mover robot para frente
        robot.Reta(getDist());
        robot.Parar(false);
        MensagemReta msg = new MensagemReta((short) getDist());
        cc.enviarMensagem(msg);
    }
    public void moveBackwards() {
        //mover robo para trás
        robot.Reta(getDist() * -1);
        robot.Parar(false);
        MensagemReta msg = new MensagemReta((short) getDist() * -1);
        cc.enviarMensagem(msg);
    }
    public void moveLeft() {
        //Mover para a esquerda
        robot.CurvarEsquerda(getRadius(), getAngle());
        robot.Parar(false);
        MensagemCurvaEsquerda msg = new MensagemCurvaEsquerda((short) getRadius(), (short) getAngle());
        cc.enviarMensagem(msg);
    }

    public void moveRight() {
        //mover para a direita
        robot.CurvarDireita(getRadius(), getAngle());
        robot.Parar(false);
        MensagemCurvaDireita msg = new MensagemCurvaDireita((short) getRadius(), (short) getAngle());
        cc.enviarMensagem(msg);
    }

    public void Stop() {
        //Parar robot
        robot.Parar(true);
        MensagemParar msg = new MensagemParar(true);
        cc.enviarMensagem(msg);
    }

    @Override
    public void run() {
        for (;;){
            try{
                Mensagem msgRecebida = cc.receberMensagem();

                if (msgRecebida != null && cc.isCanalOpen()){
                    // TODO mensagem do tipo Acaba
                    msgsRecebidas.add(msgRecebida);
                }

                // se o servidor estiver em execução,
                // houver mensagens a executar e houver ligação com o robot
                if (!isExe && !msgsExe.isEmpty() && robotOpen){
                    // vai buscar a 1ª mensagem da fila
                    Mensagem nextMensagem = msgsExe.get(0);
                    switch (nextMensagem.getType()){

                        case Mensagem.TypeReta:
                            MensagemReta msgReta = (MensagemReta) nextMensagem;
                            this.robot.Reta(msgReta.getdistancia());
                            isExe = true;
                            waitTime = (long) (Math.ceil(msgReta.getdistancia() / 30.0f) * 1000.0f);
                            beginWait = clk.millis();
                            break;

                        case Mensagem.TypeCurvaDireita:
                            MensagemCurvaDireita msgDireita = (MensagemCurvaDireita) nextMensagem;
                            this.robot.CurvarDireita(msgDireita.getRaio(), msgDireita.getAngulo());
                            isExe = true;
                            waitTime = (long) ((((Math.toRadians(msgDireita.getAngulo()) * (msgDireita.getRaio() + 4.5f)) / 30.0f) * 1000.0f) + 620.0);
                            beginWait = clk.millis();
                            break;

                        case Mensagem.TypeCurvaEsquerda:
                            MensagemCurvaEsquerda msgEsquerda = (MensagemCurvaEsquerda) nextMensagem;
                            this.robot.CurvarEsquerda(msgEsquerda.getRaio(), msgEsquerda.getAngulo());
                            isExe = true;
                            waitTime = (long) (((Math.toRadians(msgEsquerda.getAngulo()) * (msgEsquerda.getRaio() + 4.0f)) / 30.0f) * 1000.0f);
                            beginWait = clk.millis();
                            break;

                        case Mensagem.TypePara:
                            MensagemParar msgPara = (MensagemParar) nextMensagem;
                            this.robot.Parar(msgPara.getValor());
                            isExe = true;
                            waitTime = (long) 500.0f;
                            beginWait = clk.millis();
                            break;

                    }
                  // No caso de tentar executar uma mensagem durante o tempo de execução de uma anterior
                } else if (isExe){
                    // Se o tempo de espera ultrapassar o tempo atual pode executar próxima instrução
                    if (clk.millis() - beginWait >= waitTime) {
                        isExe = false;
                        msgsExe.remove(0);
                        waitTime = 0;
                        beginWait = 0;
                    }

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
