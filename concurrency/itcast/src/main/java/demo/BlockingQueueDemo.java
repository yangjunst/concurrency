package demo;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 18:56
 * Program Goal:
 *********************************************/
public class BlockingQueueDemo {
    public static void main(String[] args) {
//        testArrayBlockingQueue();
//        testLinkedBlockingQueue();
        testLinkedBlockingQueueDefaultParam();
    }
    public static void testLinkedBlockingQueueDefaultParam() {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        try {
            queue.put("11");
            System.out.println("put 11");
            queue.put("22");
            System.out.println("put 22");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void testLinkedBlockingQueue() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);
        try {
            queue.put(1);
            System.out.println("put 1");
            queue.put(2);
            System.out.println("put 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testArrayBlockingQueue() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        try {
            queue.put("1");
            System.out.println("put 1");
            queue.put("2");
            System.out.println("put 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
