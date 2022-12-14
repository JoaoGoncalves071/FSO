package tps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIConsole {

    private final Servidor servidor = new Servidor();

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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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
    }

    private void CloseWindow() {
        //Fechar instataneamente o robt e fechar a consola
        servidor.Stop();
        consolePrint("Shutting Down...");
        servidor.Close();
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
                servidor.setName(nameVal.getText());
                consolePrint("Name inserted = ", servidor.getName());
                servidor.Open();
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
                servidor.setRadius(Integer.parseInt(radiusVal.getText()));
                consolePrint("Radius value = ", servidor.getRadius());
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
                servidor.setAngle(Integer.parseInt(angleVal.getText()));
                consolePrint("Angle value = ", servidor.getAngle());
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
                servidor.setDist(Integer.parseInt(distVal.getText()));
                consolePrint("Distance value = ", servidor.getDist());
            }
        });
        frame.getContentPane().add(distVal);
        distVal.setColumns(10);

        forwardButton = new JButton("FORWARD");
        forwardButton.setEnabled(false);
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servidor.moveForward();
                consolePrint("Going Forward...");
            }
        });
        forwardButton.setBounds(184, 104, 114, 47);
        frame.getContentPane().add(forwardButton);

        stopButton = new JButton("STOP");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servidor.Stop();
                consolePrint("Stopping...");
            }
        });
        stopButton.setBounds(184, 162, 114, 47);
        frame.getContentPane().add(stopButton);

        backwardButton = new JButton("BACKWARD");
        backwardButton.setEnabled(false);
        backwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servidor.moveBackwards();
                consolePrint("Going Backwards...");
            }
        });
        backwardButton.setBounds(184, 220, 114, 47);
        frame.getContentPane().add(backwardButton);

        leftButton = new JButton("LEFT");
        leftButton.setEnabled(false);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servidor.moveLeft();
                consolePrint("Going Left...");
            }
        });
        leftButton.setBounds(60, 162, 114, 47);
        frame.getContentPane().add(leftButton);

        rightButton = new JButton("RIGHT");
        rightButton.setEnabled(false);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servidor.moveRight();
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
                    servidor.Close();
                    consolePrint("Robot OFFLINE");
                    SetState(false);
                    consolePrint("Console OFFLINE");

                }
            }
        });


    }


}
