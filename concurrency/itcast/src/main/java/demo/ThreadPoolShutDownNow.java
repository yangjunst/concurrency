package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/20 19:11
 * Program Goal:
 * {@link ExecutorService#shutdownNow()}
 * 对当前线程池中的线程及线程任务的影响原理
 *********************************************/
public class ThreadPoolShutDownNow {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for(int i=1;i<=10;i++){
            final int t = i;
            threadPool.execute(()->{
                    for(int j=1;j<=10;j++){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage()+"--->"+Thread.currentThread().getName());
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + t);
                    }
                }
            );
        }
        System.out.println("all of 10 tasks have committed! ");
        threadPool.shutdownNow();
    }
}
