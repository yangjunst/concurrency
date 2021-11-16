package com.wangwenjun.demo.chapter15;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/1 14:16
 *********************************************/
public interface TaskLifeCycle<T> {

    void onStart(Thread t);

    void onRunning(Thread t);

    void onFinish(Thread t, T result);

    void onError(Thread t,Exception e);

    class EmptyLifeCycle<T> implements TaskLifeCycle<T> {

        @Override
        public void onStart(Thread t) {

        }

        @Override
        public void onRunning(Thread t) {

        }

        @Override
        public void onFinish(Thread t, T result) {

        }

        @Override
        public void onError(Thread t, Exception e) {
            System.out.println("empty->"+t.getName()+","+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
