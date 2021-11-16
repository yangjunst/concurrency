package com.roocon.thread.t7;

import java.util.concurrent.TimeUnit;

public class Demo2 {
	
	public volatile boolean run = false;
	
	public static void main(String[] args) {
		
		Demo2 d = new Demo2();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.run = true;
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!d.run) {
					// 不执行
				}
				System.err.println("线程2执行了...");
			}
		}).start();
		
		
	}

}
