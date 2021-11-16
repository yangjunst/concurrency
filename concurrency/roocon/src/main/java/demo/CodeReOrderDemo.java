package demo;

import java.util.concurrent.locks.Condition;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/1 10:33
 * Program Goal:
 *********************************************/
public class CodeReOrderDemo {
    private int a;
    private boolean flag;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        while (flag) {
            int b = a + 1;
            if (b == 1) {
                System.out.println("a--->" + a + " b--->" + b);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        CodeReOrderDemo demo = new CodeReOrderDemo();
        Thread t1 = new Thread(() -> {
            while (true) {
                demo.writer();
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                demo.reader();
            }
        });
        t1.start();
        t2.start();
    }
}
