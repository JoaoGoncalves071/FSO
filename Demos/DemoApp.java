package Demos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class DemoApp extends JFrame {
	//CU

	private JPanel contentPane;
	private JTextField txtRobotname;
	private JTextField txtDistance;
	private JTextField txtAngle;
	private JTextField txtRadius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoApp frame = new DemoApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DemoApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Robot Name ");
		lblNewLabel.setBounds(10, 21, 82, 21);
		contentPane.add(lblNewLabel);
		
		txtRobotname = new JTextField();
		txtRobotname.setBounds(102, 21, 86, 20);
		contentPane.add(txtRobotname);
		txtRobotname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Distance ");
		lblNewLabel_1.setBounds(10, 53, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDistance = new JTextField();
		txtDistance.setBounds(102, 50, 86, 20);
		contentPane.add(txtDistance);
		txtDistance.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Angle");
		lblNewLabel_2.setBounds(10, 78, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtAngle = new JTextField();
		txtAngle.setBounds(102, 75, 86, 20);
		contentPane.add(txtAngle);
		txtAngle.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Radius");
		lblNewLabel_3.setBounds(10, 103, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtRadius = new JTextField();
		txtRadius.setBounds(102, 100, 86, 20);
		contentPane.add(txtRadius);
		txtRadius.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Open");
		rdbtnNewRadioButton.setBounds(240, 20, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Stop After Movement ");
		chckbxNewCheckBox.setBounds(240, 49, 157, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Forward");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(141, 131, 123, 46);
		contentPane.add(btnNewButton);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.setBounds(141, 188, 123, 46);
		contentPane.add(btnStop);
		
		JButton btnNewButton_1_1 = new JButton("Backward");
		btnNewButton_1_1.setEnabled(false);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBounds(141, 245, 123, 46);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Left");
		btnNewButton_1_1_1.setEnabled(false);
		btnNewButton_1_1_1.setBounds(10, 188, 123, 46);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Right");
		btnNewButton_1_1_1_1.setEnabled(false);
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1_1_1.setBounds(274, 188, 123, 46);
		contentPane.add(btnNewButton_1_1_1_1);
		
		JTextArea logMessage = new JTextArea();
		logMessage.setBackground(new Color(255, 255, 255));
		logMessage.setEditable(false);
		logMessage.setBounds(10, 316, 387, 155);
		contentPane.add(logMessage);
		
		JLabel lblNewLabel_4 = new JLabel("Log Message");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 292, 72, 26);
		contentPane.add(lblNewLabel_4);
		
		JButton btnClearMessages = new JButton("Clear Messages");
		btnClearMessages.setBackground(new Color(255, 255, 255));
		btnClearMessages.setForeground(new Color(0, 0, 0));
		btnClearMessages.setBounds(10, 482, 387, 21);
		contentPane.add(btnClearMessages);

	}
}
