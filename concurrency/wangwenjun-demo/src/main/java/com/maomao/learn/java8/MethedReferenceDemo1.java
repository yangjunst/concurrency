package com.maomao.learn.java8;

import java.util.Random;
import java.util.function.Supplier;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/4 14:14
 *********************************************/
public class MethedReferenceDemo1 {
    public static int supplier(Supplier<Integer> supplier) {
        return supplier.get();
    }
    private static class Example{
        public  Integer generator() {
            return new Random().nextInt(100);
        }
    }
    public static void main(String[] args) {
        Example example=new Example();
        for(int i=0;i<20;i++){
            int supplier = supplier(example::generator);
            System.out.println(supplier);
        }
    }
}
