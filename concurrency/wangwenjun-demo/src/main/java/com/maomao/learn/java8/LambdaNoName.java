package com.maomao.learn.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/********************************************
 * 文件名称: LambdaNoName.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/13 12:41
 *********************************************/
public class LambdaNoName {

    private static interface Lambda{
        boolean a()throws Exception;
    }

    static boolean b(Lambda v){
        try {
            return v.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        List<Apple> list=Arrays.asList(
                new Apple("red","big",32),
                new Apple("green","small",12),
                new Apple("pink","bigger",22),
                new Apple("white","smaller",42),
                new Apple("black","normal",15),
                new Apple("yellow","larger",42),
                new Apple("blue","normal",15)
        );
        Comparator<Apple> comparator1=(a,b)->a.getWeight()-b.getWeight();
        Comparator<Apple> comparator2=(a,b)->a.getName().compareTo(b.getName());
        list.sort(comparator1);
        list.sort(comparator2);
    }
}
