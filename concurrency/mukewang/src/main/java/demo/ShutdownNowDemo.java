package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/25 17:59
 * Program Goal:
 *********************************************/
public class ShutdownNowDemo {
    /**
     * 中断线程池
     *
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            try {
                while (true) {
                    System.out.println("ok");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getClass());
            }

        });
        Thread.sleep(5_000);
        service.shutdownNow();
    }

    /**
     * 无法中断
     */
    public static void test2() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            while (true) {
            }
        });
        Thread.sleep(3_000);
        System.err.println("-------------->start.......");
        service.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        test2();
    }
}
