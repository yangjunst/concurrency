package demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/22 21:48
 * Program Goal:
 * synchronized不具有重写继承性
 *********************************************/
 class SyncExtendsDemo {
    public synchronized void test() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---->" + i);
        }
    }
}
class Sub extends SyncExtendsDemo {
     public  void test() {
    for (int i = 0; i < 10; i++) {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---->" + i);
    }
}
    public static void main(String[] args) throws InterruptedException {
        Sub sub = new Sub();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                sub.test();
            }).start();
        }
    }

}