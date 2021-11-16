package questions1000ofcompany;

import java.util.Arrays;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 15:14
 * Program Goal:
 *********************************************/

public class ThreadStackTraceDemo {
    public static void test1() {
        test2();
    }
    public static void test2() {
        test3();
    }
    public static void test3() {
        test4();
    }
    public static void test4() {
        Arrays.stream(Thread.currentThread().getStackTrace()).filter(e->!e.isNativeMethod()).forEach(System.out::println);
    }

    public static void main(String[] args) {
        test1();
    }
}
