package gp03.com.gupaoedu.concurrent;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> strings=new LinkedList<>();
        Producer producer=new Producer(strings,10);
        Consumer consumer=new Consumer(strings,10);
        new Thread(producer).start();
        Thread.sleep(100);
        new Thread(consumer).start();
    }
}
