package example;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/16 13:41
 * Program Goal:
 *********************************************/
public class ExecutorsDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue queue=new LinkedBlockingQueue();
        ThreadPoolExecutor service=(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        service.prestartCoreThread();
        service.prestartAllCoreThreads();
        for(int i=0;i<2;i++){
            final int k=i;
            service.execute(()->{
                if(k==2){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println(service.getCorePoolSize());
        System.out.println(service.getActiveCount());

    }
}
