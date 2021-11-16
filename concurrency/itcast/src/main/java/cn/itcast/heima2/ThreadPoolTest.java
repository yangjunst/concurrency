package cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
//		//ExecutorService threadPool = Executors.newFixedThreadPool(3);
//		//ExecutorService threadPool = Executors.newCachedThreadPool();
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
//		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
//		for(int i=1;i<=10;i++){
//			final int task = i;
//			threadPool.execute(new Runnable(){
//				@Override
//				public void run() {
//					for(int j=1;j<=10;j++){
//						try {
//							Thread.sleep(2);
//						} catch (InterruptedException e) {
//							System.out.println(e.getMessage()+"--->"+Thread.currentThread().getName());
//						}
//						System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
//					}
//				}
//			});
//		}
//		System.out.println("all of 10 tasks have committed! ");
//		threadPool.shutdownNow();
//
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

		scheduledThreadPool.scheduleAtFixedRate(
				new Runnable(){
					@Override
				public void run() {
					System.out.println("bombing!");

				}},
				2,
				1,
				TimeUnit.SECONDS);
		TimeUnit.MILLISECONDS.sleep(3100);
		scheduledThreadPool.shutdown();
	}

}
