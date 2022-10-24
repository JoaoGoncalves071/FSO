package Demos;

import java.util.Random;

public class myRunnable extends Thread{
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
	
	public myRunnable(String text, int numTimes) {
		this.text = text;
		this.numTimes = numTimes;
	}
	
	public void run() {
		System.out.printf("[%s] a iniciar...\n", Thread.currentThread().getName());
		for(int i =0; i<this.numTimes; i++) {
			System.out.printf("%s %d vezes.\n", this.text, i);
			dormir(1000);		
		}
		System.out.printf("[%s] a terminar...\n", Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		System.out.printf("[%s] a iniciar...\n", Thread.currentThread().getName());
		Runnable r1 = new myRunnable("ola", 5);
		Runnable r2 = new myRunnable("mundo",6);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		System.out.printf("[%s] a terminar...\n", Thread.currentThread().getName());
	}

}
