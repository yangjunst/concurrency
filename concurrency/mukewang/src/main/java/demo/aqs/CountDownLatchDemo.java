package demo.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 19:20
 * Program Goal:
 *********************************************/
public class CountDownLatchDemo {
    private static final CountDownLatch latch=new CountDownLatch(10);
    public static void main(String[] args) {
        ExecutorService service=Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            final int k=i;
            service.execute(()->{
                test(k);
            });
        }
        try {
            latch.await(10,TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished");
        service.shutdown();
    }
    private static void test(final int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
            System.out.println("i---->"+i);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
