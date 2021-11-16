package demo.aqs;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 19:32
 * Program Goal:
 *********************************************/
public class CyclicBarrierDemo {
    private static final CyclicBarrier barrier=new CyclicBarrier(2);
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            final int k=i;
            service.execute(()->{
                test(k);
            });
        }
        service.shutdown();
    }
    private static void test(final int i) {
        try {
            System.out.println("i----> "+i+" is aready...");
            TimeUnit.MILLISECONDS.sleep(1200);
            barrier.await(1,TimeUnit.SECONDS);
            System.out.println("i----> "+i+" is finished...");
            TimeUnit.MILLISECONDS.sleep(1200);

        } catch (Exception e) {
            System.out.println(e.getClass()+"-->"+i);
        }
    }
}
