package demo;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 13:57
 * Program Goal:
 *********************************************/
public class ExecutorServiceAwaitNanosDemo {
    private static final Random r=new Random(47);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            int k=i;
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(k);
            });
        }
        service.shutdown();
        service.awaitTermination(1,TimeUnit.MINUTES);
        System.out.println("all thread terminated...");
//        ThreadGroup parent = Thread.currentThread().getThreadGroup().getParent();
//        Thread[] threads=new Thread[parent.activeCount()];
//        parent.enumerate(threads);
//        Arrays.stream(threads).forEach(System.out::println);
    }
}
