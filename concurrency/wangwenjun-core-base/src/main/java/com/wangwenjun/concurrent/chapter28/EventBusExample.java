package com.wangwenjun.concurrent.chapter28;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/28
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class EventBusExample
{
    public static void main(String[] args)
    {
        Bus bus =new EventBus("TestBus");
//        Bus bus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        SimpleSubscriber2 sbscriber2 = new SimpleSubscriber2();
        bus.register(new SimpleSubscriber1());
        bus.register(sbscriber2);
        bus.post("Hello");
        System.out.println("------------");
        bus.post("Hi", "test");
        bus.close();



        bus.unregister(sbscriber2);
    }
}
