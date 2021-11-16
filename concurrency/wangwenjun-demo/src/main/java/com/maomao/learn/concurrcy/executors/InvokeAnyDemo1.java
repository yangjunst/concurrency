package com.maomao.learn.concurrcy.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/24 10:07
 *********************************************/
public class InvokeAnyDemo1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                int random = ThreadLocalRandom.current().nextInt(30);
                TimeUnit.SECONDS.sleep(random);
                System.out.println("result---->"+random+"--->"+Thread.currentThread());
                return random;
            });
        }
        try {
            Integer integer = service.invokeAny(callables,100,TimeUnit.SECONDS);
            System.out.println(integer);
        } catch (InterruptedException e) {
            System.out.println(e.getClass());
        } catch (ExecutionException e) {
            System.out.println(e.getClass());
        } catch (TimeoutException e) {
            System.out.println(e.getClass());
        }
        service.shutdown();
    }
}
