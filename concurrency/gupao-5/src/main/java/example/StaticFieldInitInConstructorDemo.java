package example;

import java.util.concurrent.CountDownLatch;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/6 16:31
 * Program Goal:
 *********************************************/
public class StaticFieldInitInConstructorDemo {
    private static CountDownLatch countDownLatch;

    StaticFieldInitInConstructorDemo(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    public static void main(String[] args) {

        StaticFieldInitInConstructorDemo constructorDemo=new StaticFieldInitInConstructorDemo(new CountDownLatch(1));
    }

}
