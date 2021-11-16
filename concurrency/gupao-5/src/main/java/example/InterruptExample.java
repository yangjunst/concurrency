package example;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/28 14:01
 * Program Goal:
 *********************************************/
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable4());
        thread.start();
        TimeUnit.SECONDS.sleep(4);
        thread.interrupt();

    }

    /**
     * 没有waiting状态下单任务对象
     */
    private static class MyRunnable1 implements Runnable {
        @Override
        public void run() {
            System.out.println("start...");
            try {
                while (true) {
                }
            } catch (Exception e) {
                System.out.println(e.getClass());
            } finally {
                System.out.println("finally...");
            }
        }
    }

    /**
     * {@link Thread#interrupted()}
     */
    private static class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            System.out.println("start...");
            try {
                while (!Thread.interrupted()) {
                }
            } catch (Exception e) {
                System.out.println(e.getClass());
            } finally {
                System.out.println("finally..." + Thread.currentThread().isInterrupted());
            }
        }
    }

    /**
     * {@link Thread#isInterrupted()}
     */
    private static class MyRunnable3 implements Runnable {
        @Override
        public void run() {
            System.out.println("start...");
            try {
                while (!Thread.currentThread().isInterrupted()) {
                }
            } catch (Exception e) {
                System.out.println(e.getClass());
            } finally {
                System.out.println("finally..." + Thread.currentThread().isInterrupted());

            }
        }
    }

    /**
     * {@link Thread#isInterrupted()}
     * in catch again {@link Thread#interrupt()}
     */
    private static class MyRunnable4 implements Runnable {
        @Override
        public void run() {
            System.out.println("start...");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    System.out.println(e.getClass());
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.println("finally..." + Thread.currentThread().isInterrupted());
                }
            }
        }
    }
}