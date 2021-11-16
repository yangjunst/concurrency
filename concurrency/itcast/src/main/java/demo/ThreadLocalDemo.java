package demo;


import java.util.Date;
import java.util.concurrent.TimeUnit;
/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/20 16:16
 * Program Goal:
 * {@link ThreadLocal#initialValue()}
 * set的值什么时候可以被清空
 *********************************************/
public class ThreadLocalDemo {
    static class Data {
        private static int counter;
        private final int id = counter++;

        Data() {
            System.out.println(Thread.currentThread().getName() + "-->" + id);
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    '}';
        }
    }

    private static ThreadLocal local = ThreadLocal.withInitial(()->{
        System.out.println(Thread.currentThread().getName()+"--invoke...");
        return new Date();});

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(local.get());
        local.set(null);
        local.set(new Date());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(local.get());

    }
}
