package demo;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/22 15:49
 * Program Goal:
 * {@link String#intern()}
 *********************************************/
public class SynchronizedDemo {

    public static void main(String[] args) {
        String s1="1"+"";
        String s2="1"+"";
        System.out.println(s1==s2);

    }
    public static void demo() {
        for(int i=0;i<5;i++){
            String s=new String("test");
            new Thread(()->{
              test(s.intern());
            },"t"+i).start();
        }
    }
    /**
     * 互斥操作
     */
    public static void test(Object o) {
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+"--->"+o);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
