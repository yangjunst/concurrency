package com.wangwenjun.demo.chapter19;


import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 11:38
 *********************************************/
public class FutureTest {
    public static void main(String[] args) {
        FutureService service = new FutureServiceImpl();

        /*Future<String> future = service.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "Hello";
        });
        System.out.println("=================");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do other thing...");
        System.out.println("================");
        System.out.println("result:"+future.get());*/

        service.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "JAVA";
        }, e -> System.out.println(e.toLowerCase() + " length--->" + e.length()));
    }


}
