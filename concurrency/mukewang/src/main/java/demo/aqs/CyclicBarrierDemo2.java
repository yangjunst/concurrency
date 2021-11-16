package demo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 19:32
 * Program Goal:
 *********************************************/
@Slf4j
public class CyclicBarrierDemo2 {
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(500);
            executor.execute(() ->
                    race(threadNum));
        }
        executor.shutdown();
    }

    private static void race(int threadNum) {
        try {

            System.out.println(threadNum + "--> is ready");
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(threadNum + "--> " + e.getClass());
        }
        System.out.println(threadNum + "  --> continue");
    }
}
