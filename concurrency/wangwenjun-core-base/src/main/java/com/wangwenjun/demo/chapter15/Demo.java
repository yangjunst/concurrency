package com.wangwenjun.demo.chapter15;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/1 13:20
 *********************************************/
class Clazz {
    public String getUserName() {
        return "";
    }

    public String display(String name, Integer age) {
        return name + "," + age;
    }
}

interface Inter {
    String getUserName();

    String display(String name, Integer age);

    Object show();
}

public class Demo extends Clazz implements Inter {

    public static void main(String[] args) {
        Throwable hello = new RuntimeException(new ArithmeticException("hello"));
        hello.printStackTrace();
    }

    @Override
    public Object show() {
        return null;
    }
}
