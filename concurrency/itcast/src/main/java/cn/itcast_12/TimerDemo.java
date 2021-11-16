package cn.itcast_12;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/*
 * 定时器：可以让我们在指定的时间做某件事情，还可以重复的做某件事情。
 * 依赖Timer和TimerTask这两个类：
 * Timer:定时
 * 		public Timer()
 * 		public void schedule(TimerTask task,long delay)
 * 		public void schedule(TimerTask task,long delay,long period)
 * 		public void cancel()
 * TimerTask:任务
 */
public class TimerDemo {
	public static void main(String[] args) throws InterruptedException {
		// 创建定时器对象
		Timer t = new Timer();
		// 3秒后执行爆炸任务
		// t.schedule(new MyTask(), 3000);
		//结束任务
		MyTask myTask = new MyTask(t);


		t.schedule(myTask, 0,1000);
		TimeUnit.SECONDS.sleep(3);
		myTask.cancel();
		t.cancel();
	}
}

// 做一个任务
class MyTask extends TimerTask {

	private Timer t;
	
	public MyTask(){}
	
	public MyTask(Timer t){
		this.t = t;
	}
	
	@Override
	public void run() {

		System.out.println("beng,爆炸了");
	}

}