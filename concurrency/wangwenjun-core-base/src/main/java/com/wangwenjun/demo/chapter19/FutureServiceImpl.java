package com.wangwenjun.demo.chapter19;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 11:33
 *********************************************/
public class FutureServiceImpl implements FutureService {

    @Override
    public <T> Future<T> submit(Callable<T> c) {
        Future<T> future = new FutureTask<>();
        new Thread(() -> {
            System.out.println("task start...");
            T result = null;
            try {
                result = c.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("task finish...");
            future.done(result);
        }).start();
        return future;
    }

    @Override
    public <T> void submit(Callable<T> c1, Consumer<T> c2) {
        try {
            new Thread(()->{
                System.out.println("task start...");
                T result = null;
                try {
                    result = c1.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("task finish...");
                c2.accept(result);
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
