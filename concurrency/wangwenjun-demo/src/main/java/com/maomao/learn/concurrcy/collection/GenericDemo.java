package com.maomao.learn.concurrcy.collection;

import java.awt.print.PrinterGraphics;
import java.util.Date;

/********************************************
 * 文件名称: GenericDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/30 11:28
 *********************************************/
public class GenericDemo<T> {
    private final InnerGeneric<T> innerGeneric;

    public GenericDemo(InnerGeneric<T> innerGeneric) {
        this.innerGeneric = innerGeneric;
    }


    @Override
    public String toString() {
        return "GenericDemo{" +
                "innerGeneric=" + innerGeneric +
                '}';
    }

    private static class InnerGeneric<T> {
        private T t;

        public InnerGeneric(T t) {
            this.t = t;
        }


        @Override
        public String toString() {
            return "InnerGeneric{" +
                    "t=" + t +
                    '}';
        }
    }

    public static void main(String[] args) {
        InnerGeneric innerGeneric = new InnerGeneric(1);
        System.out.println(innerGeneric);
        GenericDemo<Integer> genericDemo = new GenericDemo<>(innerGeneric);
        System.out.println(genericDemo);
    }
}
