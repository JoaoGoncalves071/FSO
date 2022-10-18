package tps;

import robot.RobotLegoEV3;

public class Servidor {
    private String name;
    private int angle;
    private int radius;
    private int dist;

    private final RobotLegoEV3 robot = new RobotLegoEV3();

    //public void Servidor(){}

    //Name
    public void setName(String name){
        this.name = name;

    }
    public String getName(){
        return name;
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
        this.robot.Reta(getDist());
        this.robot.Parar(false);
    }
    public void moveBackwards() {
        //mover robo para trás
        this.robot.Reta(getDist() * -1);
        this.robot.Parar(false);
    }
    public void moveLeft() {
        //Mover para a esquerda
        this.robot.CurvarEsquerda(getRadius(), getAngle());
        this.robot.Parar(false);
    }

    public void moveRight() {
        //mover para a direita
        this.robot.CurvarDireita(getRadius(), getAngle());
        this.robot.Parar(false);
    }

    public void Stop() {
        //Parar robo
        this.robot.Parar(true);
    }

    //Open and close connection to robot
    public void Open() {
        this.robot.OpenEV3(getName());
    }
    public void Close() {
        this.robot.CloseEV3();
    }
}
