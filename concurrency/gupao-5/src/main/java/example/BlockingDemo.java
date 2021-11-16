package example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/5 15:04
 * Program Goal:
 * interrupt queue
 *********************************************/
public class BlockingDemo {
    static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    static volatile boolean finish = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                while (!finish || Thread.currentThread().isInterrupted()) {
                    queue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        finish = true;
        thread.interrupt();

    }
}
