package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.TimeUnit;

public class SemaphoreExample3
{
    public static void main(String[] args) throws InterruptedException
    {
//        final Semaphore semaphore = new Semaphore(1, true);
//        assert semaphore.tryAcquire() : "acquire permit successfully.";
//        assert !semaphore.tryAcquire() : "acquire permit failure.";

//        final Semaphore semaphore = new Semaphore(1, true);
//        new Thread(() ->
//        {
//            boolean gotPermit = semaphore.tryAcquire();
//            if (gotPermit)
//            {
//                try
//                {
//                    System.out.println(currentThread() + " get one permit.");
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                } finally
//                {
//                    semaphore.release();
//                }
//            }
//        }).start();
//
//        TimeUnit.SECONDS.sleep(1);
////        assert !semaphore.tryAcquire(3, TimeUnit.SECONDS) : "can't get the permit";
//        assert semaphore.tryAcquire(30, TimeUnit.SECONDS) : "get the permit";
//
//

//        final Semaphore semaphore = new Semaphore(5, true);
//        assert semaphore.tryAcquire(5) : "acquire permit successfully.";
//        assert !semaphore.tryAcquire() : "acquire permit failure.";

        /*final Semaphore semaphore = new Semaphore(5, true);
        assert semaphore.tryAcquire(10) : "acquire permit successfully.";*/

        /*final Semaphore semaphore = new Semaphore(1, true);
        semaphore.acquire();
        Thread t = new Thread(() ->
        {
            try
            {
                semaphore.acquire();
                System.out.println("The thread t acquired permit from semaphore.");
            } catch (InterruptedException e)
            {
                System.out.println("The thread t is interrupted");
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(10);
        semaphore.release();*/

//        final Semaphore semaphore = new Semaphore(1, true);
//        semaphore.acquire();
//        Thread thread = new Thread(semaphore::acquireUninterruptibly);
//        thread.start();
//        TimeUnit.SECONDS.sleep(10);
//        thread.interrupt();


//        final Semaphore semaphore = new Semaphore(1, true);
//        Thread t1 = new Thread(() ->
//        {
//            try
//            {
//                semaphore.acquire();
//                System.out.println("The thread t1 acquired permit from semaphore.");
//                TimeUnit.HOURS.sleep(1);
//            } catch (InterruptedException e)
//            {
//                System.out.println("The thread t1 is interrupted");
//            } finally
//            {
//                semaphore.release();
//            }
//        });
//        t1.start();
//
//        TimeUnit.SECONDS.sleep(1);
//        Thread t2 = new Thread(() ->
//        {
//            try
//            {
//                semaphore.acquire();
//            } catch (InterruptedException e)
//            {
//                System.out.println("The thread t2 is interrupted");
//                return;
//            }
//
//            try
//            {
//                System.out.println("The thread t2 acquired permit from semaphore.");
//            } finally
//            {
//                semaphore.release();
//            }
//        });
//        t2.start();
//
//        TimeUnit.SECONDS.sleep(2);
//        t2.interrupt();
//        semaphore.acquire();
//        System.out.println("The main thread acquired permit.");

        final MySemaphore semaphore = new MySemaphore(1, true);
        Thread t1 = new Thread(() ->
        {
            try
            {
                semaphore.acquire();
                System.out.println("The thread t1 acquired permit from semaphore.");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e)
            {
                System.out.println("The thread t1 is interrupted");
            } finally
            {
                semaphore.release();
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() ->
        {
            try
            {
                semaphore.acquire();
                System.out.println("The thread t2 acquired permit from semaphore.");
            } catch (InterruptedException e)
            {
                System.out.println("The thread t2 is interrupted");
            } finally
            {
                semaphore.release();
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();
        semaphore.acquire();
        System.out.println("The main thread acquired permit.");
    }
}