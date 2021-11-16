package com.maomao.learn.concurrcy.collection;

/********************************************
 * 文件名称: GenericDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/30 11:28
 *********************************************/
public class GenericDemo2<T> {
    private final InnerGeneric<T> innerGeneric;

    public GenericDemo2(InnerGeneric<T> innerGeneric) {
        this.innerGeneric = innerGeneric;
    }


    @Override
    public String toString() {
        return "GenericDemo{" +
                "innerGeneric=" + innerGeneric +
                '}';
    }

    private static class InnerGeneric<E> {
        private E t;

        public InnerGeneric(E t) {
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
        GenericDemo2<StringBuilder> genericDemo = new GenericDemo2<StringBuilder>(innerGeneric);
        System.out.println(genericDemo);
    }
}
