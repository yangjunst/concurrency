package com.maomao.learn.language;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/28 16:19
 *********************************************/
public class TracePrintDemo {
    public static void main1() {
        main2();
    }

    public static void main2() {
        main3();
    }

    public static void main3() {
        main4();
        try {
            int i=1/0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main4() {
        main5();
    }

    public static void main5() {
        main6();
    }

    public static void main6() {
        System.out.println("ok");
    }

    public static void main(String[] args) {
        main1();
    }
}
