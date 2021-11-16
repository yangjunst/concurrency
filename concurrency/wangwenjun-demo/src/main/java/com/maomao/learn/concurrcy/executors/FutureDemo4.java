package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/23 15:57
 *********************************************/
public class FutureDemo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        AtomicReference<String> reference=new AtomicReference<>();
        Future<AtomicReference> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                reference.set("yangjunst");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },reference);

        System.out.println("result:"+future.get().getClass());
        System.out.println(reference);

        service.shutdown();

    }
}
