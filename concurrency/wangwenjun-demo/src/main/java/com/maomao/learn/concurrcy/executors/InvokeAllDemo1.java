package com.maomao.learn.concurrcy.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/24 10:28
 *********************************************/
public class InvokeAllDemo1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int j=i;
            callables.add(() -> {
                int random = ThreadLocalRandom.current().nextInt(10000);
                TimeUnit.MILLISECONDS.sleep(random);
                System.out.println("result---->"+random+"--->"+Thread.currentThread().getId()+"==>j="+j);
                return random;
            });
        }
        List<Future<Integer>> futures = null;
        try {
            futures = service.invokeAll(callables,100,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        futures.stream().forEach(f->{
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }
}
