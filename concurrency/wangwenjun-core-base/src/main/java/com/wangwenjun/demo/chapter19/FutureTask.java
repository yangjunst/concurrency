package com.wangwenjun.demo.chapter19;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 11:29
 *********************************************/
public class FutureTask<T> implements Future<T> {
    private boolean done = false;
    private T result;

    @Override
    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notify();
        }
    }

    @Override
    public T get() {
        synchronized (this){
            while (!done){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.result;
        }
    }
}
