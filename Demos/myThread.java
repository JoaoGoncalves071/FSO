package Demos;

import java.util.Random;

public class myThread extends Thread{
	private Random rnd = new Random();
	private String text;
	private int numTimes;
	private void dormir(int i) {
		try {
			Thread.sleep(250 + this.rnd.nextInt(i));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public myThread(String text, int numTimes) {
		super(text);
		this.text = text;
		this.numTimes = numTimes;
	}
	
	public void run() {
		System.out.printf("[%s] a iniciar...\n", this.getName());
		for(int i =0; i<this.numTimes; i++) {
			System.out.printf("%s %d vezes.\n", this.text, i);
			dormir(1000);		
		}
		System.out.printf("[%s] a terminar...\n", this.getName());
	}
	
	public static void main(String[] args) {
		System.out.printf("[%s] a iniciar...\n", Thread.currentThread().getName());
		Thread t1 = new myThread("ola", 5);
		Thread t2 = new myThread("mundo",6);
		t1.start();
		t2.start();
		System.out.printf("[%s] a terminar...\n", Thread.currentThread().getName());
	}

}
