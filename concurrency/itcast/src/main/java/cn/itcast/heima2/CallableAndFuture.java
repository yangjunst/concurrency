package cn.itcast.heima2;

import java.util.Random;
import java.util.concurrent.*;


public class CallableAndFuture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
//		Future<String> future =
//			threadPool.submit(
//				new Callable<String>() {
//					public String call() throws Exception {
//						Thread.sleep(2000);
//						return "hello";
//					};
//				}
//		);
//		System.out.println("等待结果");
//		try {
//			System.out.println("拿到结果：" + future.get(1,TimeUnit.SECONDS));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//
//			System.out.println(e.getClass());
//		}

		ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(
						completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
