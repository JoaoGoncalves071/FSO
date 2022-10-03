package Demos.teste;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import robot.RobotLegoEV3;

import javax.swing.JTextArea;

public class Tp1 {
	private RobotLegoEV3 robot;
	
	private String robotName;
	private int robotDistance;
	
	private int robotDegree;
	private int robotRadius;
	
	private boolean robotStateOn;

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldRadius;
	private JTextField textFieldDist;
	private JTextField textFieldDegree;
	private JButton ButtonUp;
	private JButton ButtonLeft;
	private JButton ButtonDown;
	private JButton ButtonStop;
	private JButton ButtonRight;
	
	/**
	 * Create the application.
	 */
	
	private void myInitialize() {
		this.frame.setVisible(true);
		this.robot= new RobotLegoEV3();
		this.robotName = "EV2";
		this.robotDistance = 20;
		this.robotDegree = 90;
		this.robotRadius = 0;
		this.robotStateOn = false;
		this.textFieldName.setText(this.robotName);
		this.textFieldName.setEnabled(false);
		this.textFieldDist.setEnabled(false);
		this.textFieldDegree.setEnabled(false);
		this.textFieldRadius.setEnabled(false);
		this.ButtonUp.setEnabled(false);
		this.ButtonDown.setEnabled(false);
		this.ButtonRight.setEnabled(false);
		this.ButtonLeft.setEnabled(false);
		this.ButtonStop.setEnabled(false);


		
		
	}
	
	
	private void myHandleName() {
		this.robotName = this.textFieldName.getText();
		System.out.printf("Novo nome = %s\n", this.robotName);
	}
	private void myHandleDegree() {
		this.robotDegree = Integer.parseInt(this.textFieldDegree.getText());
		System.out.printf("Novo Angulo = %d\n ", this.robotDegree);
		
	}
	private void myHandleRadius() {
		this.robotRadius = Integer.parseInt(this.textFieldRadius.getText());
		System.out.printf("novo raio de curva = %d\n", this.robotRadius );
	}
	private void myHandleDistance() {
		this.robotDistance = Integer.parseInt(this.textFieldDist.getText());
		System.out.printf("Nova distancia = %d\n", this.robotDistance);
	}
	private void CloseWindow() {
		this.robot.Parar(true);
		this.robot.CloseEV3();
		
	}
	
	private void myDegreeUP() {
		this.robot.Reta(this.robotDistance);	
		this.robot.Parar(false);

	}
	
	private void myMoveBack() {
		this.robot.Reta(this.robotDistance*-1);
		this.robot.Parar(false);
	}
	
	private void myStop() {
		this.robot.Parar(true);
	}
	
	private void myMoveLeft() {
		this.robot.CurvarEsquerda(this.robotRadius, this.robotDegree );
		this.robot.Parar(false);

	}
	
	private void myMoveRight() {
		this.robot.CurvarDireita(this.robotRadius, this.robotDegree);
		this.robot.Parar(false);

		
	}
	
	private void MyHandleOpen() {
		this.robotStateOn = this.robot.OpenEV3(this.robotName);
		this.textFieldName.setEnabled(true);
		this.textFieldRadius.setEnabled(true);
		this.textFieldDegree.setEnabled(true);
		this.textFieldDist.setEnabled(true);
		this.ButtonUp.setEnabled(true);
		this.ButtonDown.setEnabled(true);
		this.ButtonRight.setEnabled(true);
		this.ButtonLeft.setEnabled(true);
		this.ButtonStop.setEnabled(true);
		
	}
	private void MyHandleClose() {
		
		this.robot.CloseEV3();
		this.textFieldName.setEnabled(false);
		this.textFieldRadius.setEnabled(false);
		this.textFieldDegree.setEnabled(false);
		this.textFieldDist.setEnabled(false);
		this.ButtonUp.setEnabled(false);
		this.ButtonDown.setEnabled(false);
		this.ButtonRight.setEnabled(false);
		this.ButtonLeft.setEnabled(false);
		this.ButtonStop.setEnabled(false);
		
		this.robotStateOn = false;		
	}
	
	
	public Tp1() {
		initialize();
		myInitialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 460);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 10, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		textFieldName = new JTextField();
		textFieldName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myHandleName();
			}
		});
		textFieldName.setBounds(65, 7, 96, 19);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		frame.addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent e) {
                CloseWindow();
                System.exit(0);
            }
        });
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Raio");
		lblNewLabel_1.setBounds(10, 39, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		textFieldRadius = new JTextField();
		textFieldRadius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myHandleRadius();
			}
		});
		textFieldRadius.setBounds(65, 36, 96, 19);
		frame.getContentPane().add(textFieldRadius);
		textFieldRadius.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Dist");
		lblNewLabel_2.setBounds(10, 68, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textFieldDist = new JTextField();
		textFieldDist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myHandleDistance();
			}
		});
		textFieldDist.setBounds(65, 65, 96, 19);
		frame.getContentPane().add(textFieldDist);
		textFieldDist.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Angulo");
		lblNewLabel_3.setBounds(205, 68, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		textFieldDegree = new JTextField();
		textFieldDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myHandleDegree();
			}
		});
		textFieldDegree.setBounds(260, 65, 96, 19);
		frame.getContentPane().add(textFieldDegree);
		textFieldDegree.setColumns(10);
		
		
		
		
		
		
		
		
		
		ButtonUp = new JButton("Up");
		ButtonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDegreeUP();
			}

			
		});
		ButtonUp.setBounds(165, 139, 85, 21);
		frame.getContentPane().add(ButtonUp);
		
		ButtonDown = new JButton("Down");
		ButtonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myMoveBack();			
			}

			
		});
		ButtonDown.setBounds(165, 201, 85, 21);
		frame.getContentPane().add(ButtonDown);
		
		ButtonLeft = new JButton("Left");
		ButtonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myMoveLeft();
			}
		});
		ButtonLeft.setBounds(76, 170, 85, 21);
		frame.getContentPane().add(ButtonLeft);
		
		ButtonRight = new JButton("Right");
		ButtonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myMoveRight();
			}
		});
		ButtonRight.setBounds(254, 170, 85, 21);
		frame.getContentPane().add(ButtonRight);
		
		JButton ButtonOpen = new JButton("0/I");
		ButtonOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(robotStateOn == false) {
				MyHandleOpen();
				}else {
					MyHandleClose();
				}
				
			}
		});
		ButtonOpen.setBounds(260, 31, 96, 21);
		frame.getContentPane().add(ButtonOpen);
		
		ButtonStop = new JButton("Stop");
		
		ButtonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myStop();
			}
		});
		ButtonStop.setBounds(165, 170, 85, 21);
		frame.getContentPane().add(ButtonStop);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 243, 416, 170);
		frame.getContentPane().add(textArea);
		
		
	}
	
}
