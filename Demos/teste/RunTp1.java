package Demos.teste;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class RunTp1 {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tp1 window = new Tp1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
