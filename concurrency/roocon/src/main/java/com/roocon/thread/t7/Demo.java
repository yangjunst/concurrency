package com.roocon.thread.t7;

import javax.rmi.CORBA.Tie;
import java.util.concurrent.TimeUnit;

/**
 * 保证可见性的前提
 * 
 * 多个线程拿到的是同一把锁，否则是保证不了的。
 * 
 * @author worker
 *
 */
public class Demo {

	public  int a = 1;

	public  int getA() {
		return a++;
	}

	public  void setA(int a) {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.a = a;
	}

	public static void main(String[] args) throws InterruptedException {

		Demo demo = new Demo();

		demo.a = 10;

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(demo.a);
			}
		}).start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finally value--->" + demo.a);

	}

}
