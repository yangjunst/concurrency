package demo;


import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 14:38
 * Program Goal:
 * 在多线程间通信，将常规的wait在前判断，后再写线程
 * 业务逻辑，后在notify的逻辑反向书写。其各种随机
 * 运行效果解说...
 *********************************************/
public class ConditionDemo {
    private static final Object LOCK = new Object();

    /**
     * WaitNotify是用于多线程间进行通信的
     * 在同一线程，是起不到唤醒作用的
     */

    public static void main(String[] args) throws InterruptedException {
//        notifyBeforeWaitInTheDifferentBlock();
//        notifyBeforeWaitInTheSameBlock();
//        notifyBeforeWaitTest1();

        notifyBeforeWaitTest2();
//
//  notifyAfterWait();
//        notifyBeforerWait();

    }

    public static void notifyBeforerWait() throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                LOCK.notify();
                System.out.println(Thread.currentThread().getName() + "--> notify done...");
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println(Thread.currentThread().getName() + "---->  wait start...");
                    LOCK.wait();
                    System.out.println(Thread.currentThread().getName() + "---> wait be notified finished....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public static void notifyAfterWait() throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println(Thread.currentThread().getName() + "----> wait start...");
                    LOCK.wait();
                    System.out.println(Thread.currentThread().getName() + "---> wait be notified finished....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (LOCK) {
                LOCK.notify();
                System.out.println(Thread.currentThread().getName() + "--> notify done...");
            }
        }).start();
    }

    public static void notifyBeforeWaitInTheDifferentBlock() throws InterruptedException {
        synchronized (LOCK) {
            LOCK.notify();
        }
        synchronized (LOCK) {
            System.out.println("notifyBeforeWaitInTheDifferentBlock wait before..." + Thread.currentThread().getName());
            LOCK.wait();
            System.out.println("wait after..." + Thread.currentThread().getName());
        }
    }

    public static void notifyBeforeWaitInTheSameBlock() throws InterruptedException {
        synchronized (LOCK) {
            LOCK.notify();
            System.out.println(" notifyBeforeWaitInTheSameBlock wait before..." + Thread.currentThread().getName());
            LOCK.wait();
            System.out.println("wait after..." + Thread.currentThread().getName());
        }
    }

    private static class Business1 {
        private static final Object lock = new Object();
        private boolean f = false;

        public void incr(int i) throws InterruptedException {
            synchronized (lock) {
                System.out.println("incr success... " + Thread.currentThread().getName() + " --->" + i);
                f = true;
                lock.notify();
            }
            synchronized (lock) {
                while (f) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait start...--->" + i);
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " wait finish...--->" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void decr(int i) throws InterruptedException {
            synchronized (lock) {
                System.out.println("decr success... " + Thread.currentThread().getName() + " ---->" + i);
                f = false;
                lock.notify();
            }

            synchronized (lock) {
                while (!f) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait start...---->" + i);
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " wait finish...--->" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Business2 {
        private static final Object lock = new Object();
        private boolean f = false;

        public void incr(int i) throws InterruptedException {
            synchronized (lock) {
                while (f) {
                    try {
                        System.out.println("" + Thread.currentThread().getName() + " wait start...--->" + i);
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " wait finish...--->" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("incr success... " + Thread.currentThread().getName() + " --->" + i);
                f = true;
                lock.notify();
            }
        }

        public void decr(int i) throws InterruptedException {
            synchronized (lock) {
                while (!f) {
                    try {
                        System.out.println("" + Thread.currentThread().getName() + " wait start...---->" + i);
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " wait finish...--->" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("decr success... " + Thread.currentThread().getName() + " ---->" + i);
                f = false;
                lock.notify();
            }
        }
    }

    /**
     * 语法上支持notify在wait之前，但业务语义上不合理
     */
    public static void notifyBeforeWaitTest1() {

        Business1 business = new Business1();
        new Thread("t1") {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    final int k = i;
                    try {
                        business.incr(k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    final int k = i;
                    try {
                        business.decr(k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

    }

    public static void notifyBeforeWaitTest2() {

        Business2 business = new Business2();
        new Thread("t1") {
            @Override
            public void run() {
                for (int i = 1; i <= 4; i++) {
                    final int k = i;
                    try {
                        business.incr(k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                for (int i = 1; i <= 4; i++) {
                    final int k = i;
                    try {
                        business.decr(k);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

    }
}
