package example;

import java.lang.ref.*;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/11 11:05
 * Program Goal:
 *********************************************/
public class AllReferenceDemo {
    private static final class MyObject {
        String n;

        @Override
        public String toString() {
            return "MyObject{" +
                    "n='" + n + '\'' +
                    '}';
        }

        public MyObject() {
        }

        public MyObject(String n) {
            this.n = n;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize gc invoke...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        weakReferenceTest();

    }

    private static void phantomReferenceTest() throws InterruptedException {
        ReferenceQueue<MyObject> queue = new ReferenceQueue<>();
        PhantomReference<MyObject> p1 = new PhantomReference<>(new MyObject("a"), queue);
        PhantomReference<MyObject> p2 = new PhantomReference<>(new MyObject("b"), queue);
        PhantomReference<MyObject> p3 = new PhantomReference<>(new MyObject("c"), queue);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Reference<? extends MyObject> poll = queue.poll();
                    if (poll != null) {
                        System.out.println(poll);
                    }
                }
            }
        }.start();
        TimeUnit.SECONDS.sleep(1);
        System.gc();
//        while (true) {
//            byte[] bytes = new byte[1024 * 1024 * 5];
//        }
    }

    private static void weakReferenceTest() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    private static void softReferenceTest1() {
        SoftReference softReference = new SoftReference(new MyObject());
        System.out.println(softReference.get());
        try {
            byte[] bytes = new byte[9 * 1024 * 1024];
        } catch (Exception e) {
            System.out.println(e.getClass() + "-->" + e.getMessage());
        } finally {
            System.out.println(softReference.get());
        }
    }

    public static void referenceGcTest3() {
        referenceGcTest1();
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void referenceGcTest2() {
        MyObject o = new MyObject();
        System.out.println("gc before--->" + o);
        o = null;
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after--->" + o);
    }

    public static void referenceGcTest1() {
        MyObject o = new MyObject();
        System.out.println("gc before--->" + o);
        referenceGcTest1();
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after--->" + o);
    }
}
