package demo;

import java.util.concurrent.CountDownLatch;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 14:30
 * Program Goal:
 *********************************************/
public class CountDownLatchCountDownToZeroAgainDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        System.out.println(latch.getCount());
        latch.countDown();
        latch.countDown();
        latch.countDown();
        System.out.println(latch.getCount());
        latch.countDown();
        System.out.println(latch.getCount());
        latch.countDown();
        latch.countDown();
        latch.countDown();
        System.out.println(latch.getCount());
    }
}
