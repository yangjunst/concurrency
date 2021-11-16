package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/********************************************
 * 文件名称: GetAndSetLock.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/10 12:49
 *********************************************/
public class GetAndSetLock {
    private static final AtomicBoolean atomic=new AtomicBoolean(false);
    private static ThreadLocal<Boolean> threadLocal=ThreadLocal.withInitial(()->false);
    void tryLock() throws GetLockException {
        System.out.println(Thread.currentThread().getName()+"---->"+threadLocal.get());
        boolean result=atomic.compareAndSet(false,true);
        if(result){
            threadLocal.set(true);
        }else{
            throw new GetLockException(Thread.currentThread().getName()+"--->get lock failed");
        }
    }

    void unlock(){
        if(threadLocal.get()){
            atomic.compareAndSet(true,false);
            threadLocal.set(false);
        }else {
            return;
        }


    }

    public static void main(String[] args) {
        GetAndSetLock lock=new GetAndSetLock();
        for(int i=0;i<5;i++){
            new Thread("t"+i){
                @Override
                public void run() {
                    try {
                        lock.tryLock();
                        System.out.println(Thread.currentThread().getName()+"-->get lock success and is running");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (GetLockException e) {
                        System.err.println(Thread.currentThread().getName()+"... is run");

                    }finally {
                        lock.unlock();
                    }
                }
            }.start();
        }
    }
}
