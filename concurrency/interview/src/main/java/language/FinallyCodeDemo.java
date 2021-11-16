package language;

import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/4 14:09
 * Program Goal:
 *********************************************/
public class FinallyCodeDemo {

    public FinallyCodeDemo() {
        try {
            System.out.println("FinallyCodeDemo start instance...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("FinallyCodeDemo finish instance...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("FinallyCodeDemo finish sleep...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static FinallyCodeDemo test()  {
        try{
            return new FinallyCodeDemo();
        }finally {
            System.out.println("ok1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ok2");
            return null;
        }
    }

    public static FinallyCodeDemo test1()  {
        try{
            return null;
        }finally {
            System.out.println("ok1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ok2");
        }
    }

    public static void main(String[] args) {
        System.out.println(test1());

    }
}
