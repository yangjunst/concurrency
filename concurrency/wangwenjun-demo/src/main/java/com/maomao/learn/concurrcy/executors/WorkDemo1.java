package com.maomao.learn.concurrcy.executors;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/28 11:42
 *********************************************/
@FunctionalInterface
interface Work<IN,OUT>{
    OUT get(IN input);
}
interface WorkService<IN,OUT>{
    OUT submit(Work<IN,OUT> work, IN input, Consumer<OUT> consumer);
}
public class WorkDemo1 implements WorkService<String,Integer> {

    @Override
    public Integer submit(Work<String, Integer> work, String input, Consumer<Integer> consumer) {
        Integer result= work.get(input);
        new Thread(()->{
            consumer.accept(result);
        }).start();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        WorkDemo1 demo=new WorkDemo1();
        Integer generic = demo.submit(e -> 1, "hello generic", e->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("over...");
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(generic);
    }
}
