package com.wangwenjun.demo.chapter15;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/1 14:34
 *********************************************/
public class ObservableThreadTaskTest {

    public static void main(String[] args) {
        TaskLifeCycle<String> lifeCycleTask = new TaskLifeCycle.EmptyLifeCycle<String>() {
            @Override
            public void onFinish(Thread t, String result) {
                System.out.println(t.getName() + "finished...," + result);
            }
        };
        Observable observable=new ObservableThread<>(lifeCycleTask,()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                int a=1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello task";
        });
        observable.start();
    }
}
