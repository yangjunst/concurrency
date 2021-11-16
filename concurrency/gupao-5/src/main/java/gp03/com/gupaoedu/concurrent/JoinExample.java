package gp03.com.gupaoedu.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class JoinExample extends Thread{
    private static int x=0;
    @Override
    public void run() {
        try {
            x=100;
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当run方法执行结束，给一个notify的信号
        // lock.notify_all(thread);
    }
    public static void main(String[] args) throws InterruptedException {
        JoinExample je=new JoinExample();
        je.start();
        //TODO ......doSomething();
        //wait()
        je.join(); //等待je线程运行结束|如果没有执行结束，会阻塞main线程（那个线程调用，就阻塞那个线程）
        if(x==100){//成立
            System.out.println("main线程执行结束");
        }
    }
}
