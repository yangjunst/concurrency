package com.maomao.learn.java8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/********************************************
 * 文件名称: Lambda.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/12 13:01
 *********************************************/
public class Lambda {

    private static void predicate(List<String> list, Predicate<String> predicate)throws Exception {
        for (String s : list) {
            if (predicate.test(s)) {
                System.out.println("success-->" + s);
            }
        }
    }

    private static void consumer(List<String> list, Consumer<String> consumer) {
        for (String s : list) {
            consumer.accept(s);
        }
    }

    private static void function(List<String> list, Function<String, Integer> function) {
        for (String s : list) {
            System.out.println(function.apply(s));
        }
    }

    private static void supplier(List<String> list, Supplier<Integer> supplier) {
        for (String s : list) {
            System.out.println(supplier.get());
        }
    }

    private static <T> void display(List<T> list,Predicate<T> predicate){
        for(T t:list){
            if(predicate.test(t)){
                System.out.println(t);
            }
        }

    }



    public static void main(String[] args) {
        Predicate<Apple> p=(a)->a.getName().equals("");
    }
}
@FunctionalInterface
interface Demo{
    void read(String t);
}

@FunctionalInterface
interface Test {
    boolean read() throws IOException;
}
