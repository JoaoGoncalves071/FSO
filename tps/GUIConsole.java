package tps;

import robot.RobotLegoEV3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class GUIConsole {

    private JFrame frame;

    private JTextField nameVal;
    private JTextField radiusVal;
    private JTextField angleVal;
    private JTextField distVal;

    private JButton forwardButton;
    private JButton stopButton;
    private JButton backwardButton;
    private JButton leftButton;
    private JButton rightButton;

    private JCheckBox debugCheckbox;
    private JCheckBox wanderCheckbox;
    private JCheckBox avoidCheckbox;

    private JTextArea consolePanel;

    private RobotLegoEV3 robot;
    private String robotName;
    private int robotDistance;
    private int robotDegree;
    private int robotRadius;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIConsole window = new GUIConsole();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void myInitialize() {
        //inicialização predefinida
        this.frame.setVisible(true);
        this.robot = new RobotLegoEV3();
        this.robotName = "EV2";
        this.robotDistance = 20;
        this.robotDegree = 90;
        this.robotRadius = 0;
        this.nameVal.setText(robotName);
    }

    private void SetupName() {
        //Atribuir Nome e consecutivas prints
        this.robotName = this.nameVal.getText();
        consolePrint("Name inserted = ", robotName);
        this.robot.OpenEV3(robotName);
    }

    private void SetupAngle() {
        //Atribuir angulo e consecutivas prints
        this.robotDegree = Integer.parseInt(angleVal.getText());
        consolePrint("Angle value = ", this.robotDegree);
    }

    private void SetupRadius() {
        //Atribuir raio e consecutivas prints
        this.robotRadius = Integer.parseInt(radiusVal.getText());
        consolePrint("Radius value = ", robotRadius);
    }

    private void SetupDistance() {
        //Atribuir distancia e consecutivas prints
        this.robotDistance = Integer.parseInt(distVal.getText());
        consolePrint("Distance value = ", robotDistance);
    }

    private void moveForward() {
        //mover robo para frente
        this.robot.Reta(robotDistance);
        this.robot.Parar(false);
    }

    private void moveBackwards() {
        //mover robo para trás
        this.robot.Reta(robotDistance * -1);
        this.robot.Parar(false);
    }

    private void Stop() {
        //Parar robo
        this.robot.Parar(true);
    }

    private void moveLeft() {
        //Mover para a esquerda
        this.robot.CurvarEsquerda(robotRadius, robotDegree);
        this.robot.Parar(false);
    }

    private void moveRight() {
        //mover para a direita
        this.robot.CurvarDireita(robotRadius, robotDegree);
        this.robot.Parar(false);
    }

    private void CloseWindow() {
        //Fechat instataneamente o robo e fechar a consola
        this.robot.Parar(true);
        consolePrint("Shutting Down...");
        this.robot.CloseEV3();
    }


    /*
     * Métodos Adjacentes
     */

    private void SetState(boolean state) {
        //Atribuir valor booleano ás varias caixas de textos e botões
        forwardButton.setEnabled(state);
        backwardButton.setEnabled(state);
        stopButton.setEnabled(state);
        leftButton.setEnabled(state);
        rightButton.setEnabled(state);
        wanderCheckbox.setEnabled(state);
        debugCheckbox.setEnabled(state);
        avoidCheckbox.setEnabled(state);
        radiusVal.setEnabled(state);
        angleVal.setEnabled(state);
        distVal.setEnabled(state);
        nameVal.setEnabled(state);
    }

    private void consolePrint(String info, Object args) {
        consolePanel.append(info + args.toString() + " \n");
        System.out.println(info + args.toString());
    }

    private void consolePrint(String info) {
        consolePanel.append(info + " \n");
        System.out.println(info);
    }


    /**
     * Create the application.
     */
    public GUIConsole() {
        initialize();
        myInitialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("GUI do SERVIDOR");
        frame.setBounds(100, 100, 498, 456);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                CloseWindow();
                System.exit(0);
            }
        });


        JLabel ConsoleLabel = new JLabel("Console");
        ConsoleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ConsoleLabel.setBounds(10, 286, 72, 14);
        frame.getContentPane().add(ConsoleLabel);

        JLabel lblNewLabel = new JLabel("Robot");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(142, 11, 66, 23);
        frame.getContentPane().add(lblNewLabel);

        nameVal = new JTextField();
        nameVal.setEnabled(false);
        nameVal.setBounds(184, 14, 86, 20);
        nameVal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupName();
            }
        });
        frame.getContentPane().add(nameVal);
        nameVal.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 315, 462, 91);
        frame.getContentPane().add(scrollPane);

        consolePanel = new JTextArea();
        consolePanel.setEditable(false);
        scrollPane.setViewportView(consolePanel);

        JLabel radiusLabel = new JLabel("Radius");
        radiusLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radiusLabel.setBounds(25, 53, 57, 23);
        frame.getContentPane().add(radiusLabel);

        radiusVal = new JTextField();
        radiusVal.setEnabled(false);
        radiusVal.setBounds(81, 56, 46, 20);
        radiusVal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupRadius();
            }
        });
        frame.getContentPane().add(radiusVal);
        radiusVal.setColumns(10);

        JLabel angleLabel = new JLabel("Angle");
        angleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        angleLabel.setBounds(184, 53, 46, 20);
        frame.getContentPane().add(angleLabel);

        angleVal = new JTextField();
        angleVal.setEnabled(false);
        angleVal.setBounds(226, 56, 46, 20);
        angleVal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupAngle();
            }
        });
        frame.getContentPane().add(angleVal);
        angleVal.setColumns(10);

        JLabel distLabel = new JLabel("Distance");
        distLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        distLabel.setBounds(319, 59, 66, 17);
        frame.getContentPane().add(distLabel);

        distVal = new JTextField();
        distVal.setEnabled(false);
        distVal.setBounds(386, 56, 46, 20);
        distVal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupDistance();
            }
        });
        frame.getContentPane().add(distVal);
        distVal.setColumns(10);

        forwardButton = new JButton("FORWARD");
        forwardButton.setEnabled(false);
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveForward();
                consolePrint("Going Forward...");
            }
        });
        forwardButton.setBounds(184, 104, 114, 47);
        frame.getContentPane().add(forwardButton);

        stopButton = new JButton("STOP");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Stop();
                consolePrint("Stopping...");
            }
        });
        stopButton.setBounds(184, 162, 114, 47);
        frame.getContentPane().add(stopButton);

        backwardButton = new JButton("BACKWARD");
        backwardButton.setEnabled(false);
        backwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveBackwards();
                consolePrint("Going Backwards...");
            }
        });
        backwardButton.setBounds(184, 220, 114, 47);
        frame.getContentPane().add(backwardButton);

        leftButton = new JButton("LEFT");
        leftButton.setEnabled(false);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveLeft();
                consolePrint("Going Left...");
            }
        });
        leftButton.setBounds(60, 162, 114, 47);
        frame.getContentPane().add(leftButton);

        rightButton = new JButton("RIGHT");
        rightButton.setEnabled(false);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveRight();
                consolePrint("Going Right...");
            }
        });
        rightButton.setBounds(308, 162, 114, 47);
        frame.getContentPane().add(rightButton);

        debugCheckbox = new JCheckBox("Debug");
        debugCheckbox.setEnabled(false);
        debugCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        debugCheckbox.setBounds(60, 244, 97, 23);
        frame.getContentPane().add(debugCheckbox);
        debugCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (debugCheckbox.isSelected()) {
                    consolePrint("Debugging...");
                } else {
                    consolePrint("Exiting Debug mode...");
                }
            }
        });

        wanderCheckbox = new JCheckBox("Wander");
        wanderCheckbox.setEnabled(false);
        wanderCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        wanderCheckbox.setBounds(342, 220, 80, 23);
        frame.getContentPane().add(wanderCheckbox);
        wanderCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (wanderCheckbox.isSelected()) {
                    consolePrint("Wandering...");
                } else {
                    consolePrint("Stopping Wander mode...");
                }
            }
        });

        avoidCheckbox = new JCheckBox("Avoid");
        avoidCheckbox.setEnabled(false);
        avoidCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        avoidCheckbox.setBounds(342, 246, 80, 23);
        frame.getContentPane().add(avoidCheckbox);
        avoidCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (avoidCheckbox.isSelected()) {
                    consolePrint("Avoiding...");
                } else {
                    consolePrint("Exiting Avoid mode...");
                }
            }
        });

        JRadioButton power = new JRadioButton("ON/OFF");
        power.setBounds(276, 13, 109, 23);
        frame.getContentPane().add(power);
        power.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (power.isSelected()) {
                    //Inicializa a consola e todos os seus botões
                    consolePrint("Console ONLINE");
                    SetState(true);
                } else {
                    //Fecha a conexão do Robot assim como os botões da Consola
                    robot.CloseEV3();
                    consolePrint("Robot OFFLINE");
                    SetState(false);
                    consolePrint("Console OFFLINE");

                }
            }
        });


    }


}
