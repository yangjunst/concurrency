package example;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/10 11:23
 * Program Goal:
 * 测试:
 * {@link Object#wait(long)}
 * {@link Condition#await(long, TimeUnit)}
 * {@link Lock#tryLock(long, TimeUnit)}
 * {@link ExecutorService#awaitTermination(long, TimeUnit)}
 * {@link CountDownLatch#await(long, TimeUnit)}
 * {@link Semaphore#tryAcquire(long, TimeUnit)}
 * {@link CyclicBarrier#await(long, TimeUnit)
 * {@link Exchanger#wait(long, int)}
 * {@link BlockingQueue#offer(Object, long, TimeUnit)}
 * @link BlockingQueue#poll(long, TimeUnit)}
 * {@link Future#get(long, TimeUnit)}
 * 等异常超时情况
 *********************************************/
public class AllWithTimeThreadOperatorExample {
    private static final Object o = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final Condition c = lock.newCondition();
    private static final ExecutorService service = Executors.newFixedThreadPool(5);
    private static final CountDownLatch latch = new CountDownLatch(3);
    private static final Semaphore semaphore = new Semaphore(3);
    private static final CyclicBarrier barrier = new CyclicBarrier(3);
    private static final Exchanger<String> exchager = new Exchanger<>();
    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    /**
     * {@link Condition#await(long, TimeUnit)}
     * 超时后，释放锁，进入下一轮的锁抢占，占不到，锁阻塞
     *
     * @throws InterruptedException
     */
    public static void testAwaitTime() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                c.await(3, TimeUnit.SECONDS);
                System.out.println("ok");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(900);
                        System.out.println(t1.getName() + "---" + t1.getState());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }

    /**
     * {@link Lock#tryLock(long, TimeUnit)}
     * 超时后，方法返回false,直接进行后续程序的执行
     *
     * @throws InterruptedException
     */
    public static void testTryLockTime() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.tryLock(5, TimeUnit.SECONDS);
                System.out.println("ok");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(800);
                        System.out.println(t1.getName() + "---" + t1.getState());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);

        t1.start();

    }

    /**
     * {@link Object#wait(long)}
     * 超时后，释放锁，并且直接进入抢入锁阻塞中
     */
    public static void testWaitTime() throws InterruptedException {
        Thread t = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ok");
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            synchronized (o) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(t.getName() + "---" + t.getState());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


    /**
     * {@link ExecutorService#awaitTermination(long, TimeUnit)}
     * 超时后，继续走后面的程序逻辑
     */
    public static void testExecutorAwaitTime() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                try {
                    while (true) {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("finish....");

    }

    /**
     * {@link CountDownLatch#await(long, TimeUnit)}
     * 超时后，执行后续逻辑，count不变
     */
    public static void testCountDownLatchWaitTime() {
        new Thread(() -> {
            try {
                latch.await(5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "--->over,count-->" + latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                latch.await(4, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "--->over,count-->" + latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
        new Thread(() -> {
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + "--->over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();
    }

    /**
     * {@link Semaphore#tryAcquire(long, TimeUnit)}
     * 超时后，执行后续逻辑，permits数不变
     */
    public static void testSemaphoreWaitTime() {
        try {
            semaphore.tryAcquire(5, 3, TimeUnit.SECONDS);
            System.out.println("over");
            System.out.println(semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@link CyclicBarrier#await(long, TimeUnit)}
     * 超时后，throw TimeoutException,parties不变，
     * 其它等待的barrier throw BrokenBarrierException
     *
     * @throws InterruptedException
     */
    public static void testCyclicBarrierWaitTime() throws InterruptedException {
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);

        try {
            barrier.await(3, TimeUnit.SECONDS);
            System.out.println("over");
            System.out.println(barrier.getParties());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e.getClass() + "--parties-->" + barrier.getParties());
        }

    }


    /**
     * {@link Exchanger#wait(long, int)}
     * 超时后，throw TimeoutException,使得，另外的成对的pair one exchange一直阻塞
     */
    public static void testExchangerWaitTime() throws InterruptedException {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                exchager.exchange("t1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                exchager.exchange("t2", 1, TimeUnit.SECONDS);
                System.out.println("over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println(e.getClass() + "-->msg-->" + e.getMessage());
            }
        }, "t2").start();
    }


    /**
     * {@link BlockingQueue#offer(Object, long, TimeUnit)}
     * {@link BlockingQueue#poll(long, TimeUnit)}
     * 超时后，执行后续的操作。offer返回false,poll返回null
     */
    public static void testBlockingQueueWaitTime() throws InterruptedException {
        String s = queue.poll(3, TimeUnit.SECONDS);
        System.out.println("over--->" + s);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        boolean love = queue.offer("love", 2, TimeUnit.SECONDS);
        System.out.println("over--->" + love);
    }

    /**
     * {@link Future#get(long, TimeUnit)}
     * 超时后 throw TimeoutException
     */
    public static void testFutureGetWaitTime() {
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            TimeUnit.SECONDS.sleep(10);
            return "yangjun";
        });
        new Thread(futureTask).start();
        String s = null;
        try {
            s = futureTask.get(3,TimeUnit.SECONDS);
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println(e.getClass()+"--->"+e.getMessage());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        testFutureGetWaitTime();
    }
}
