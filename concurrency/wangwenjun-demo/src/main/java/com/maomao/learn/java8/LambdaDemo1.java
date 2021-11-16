package com.maomao.learn.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/24 14:54
 *********************************************/
public class LambdaDemo1 {
    abstract  class Lambda {
        abstract void show();
        abstract void show1();
    }

    public  void demo(Consumer<Lambda> lambda) {
    }

    public static void main(String[] args) {
        List<Lambda> lambdas=new ArrayList<>();
        lambdas.stream().forEach(Lambda::show);
        LambdaDemo1 demo1=new LambdaDemo1();
        demo1.demo(Lambda::show1);
    }
}
