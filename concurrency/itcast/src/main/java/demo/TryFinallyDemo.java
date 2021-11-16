package demo;


import java.lang.annotation.Annotation;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/25 1:44
 * Program Goal:
 *
 *********************************************/
@interface A {
    int[] a();
}

public class TryFinallyDemo {


    /**
     * @return finally
     */
    public static String test() {
        try {
            System.out.println("try ok...");
            return "try";
        } finally {
            System.out.println("finally ok...");
            return "finally";
        }
    }

    /**
     * @return 3
     */
    public static int test1() {
        try {
            int a = 1 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("catch...");
            return 2;
        } finally {
            System.out.println("finally...");
            return 3;
        }
    }

    public static void testError() throws Error {
        throw new Error("error...");
    }

    public static void testThrowable() throws Throwable {
        throw new Throwable("throwable...");

    }

    /**
     * before break
     * before finally...
     * 5
     */

    public static void testControlBreak() {
        for (int i = 0; i < 10; i++) {
            try {
                if (i == 5) {
                    System.out.println("before break");
                    break;
                }

            } finally {
                if (i == 5) {
                    System.out.println("before finally...");
                    System.out.println(i);
                }

            }
        }
    }

    public static void testControlContinue() {
        for (int i = 0; i < 10; i++,System.out.println("ok-->"+i)) {
            try {
                if (i == 9) {
                    System.out.println("before continue");
                    continue;
                }
            } finally {
                if (i == 9) {
                    System.out.println("before finally...");
                    System.out.println(i);
                }

            }
        }

    }

    public static void main(String[] args) {
        testControlContinue();
    }
}
