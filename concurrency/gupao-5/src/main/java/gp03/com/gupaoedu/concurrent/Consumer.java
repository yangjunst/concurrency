package gp03.com.gupaoedu.concurrent;

import java.util.Queue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class Consumer implements Runnable{
    private Queue<String> bags;
    private int maxSize;

    public Consumer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while(true){
            synchronized (bags){
                if(bags.isEmpty()){
                    System.out.println("bags为空");
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String bag=bags.remove();
                System.out.println("消费者消费："+bag);
                bags.notify(); //这里只是唤醒Producer线程，但是Producer线程并不能马上执行。
            } //同步代码块执行结束， monitorexit指令执行完成
        }
    }
}
