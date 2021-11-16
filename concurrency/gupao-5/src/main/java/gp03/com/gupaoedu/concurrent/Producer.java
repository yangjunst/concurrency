package gp03.com.gupaoedu.concurrent;

import java.util.Queue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class Producer implements Runnable {
    private Queue<String> bags;
    private int maxSize;

    public Producer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            i++;
            synchronized (bags){ //抢占锁
                if(bags.size()==maxSize){
                    System.out.println("bags 满了");
                    try {
                        //park(); ->JVM ->Native
                        bags.wait(); //满了，阻塞当前线程并且释放Producer抢到的锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者生产：bag"+i);
                bags.add("bag"+i); //生产bag
                bags.notify(); //表示当前已经生产了数据，提示消费者可以消费了
            } //同步代码快执行结束
        }
    }
}