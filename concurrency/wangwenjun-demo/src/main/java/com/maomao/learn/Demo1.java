package com.maomao.learn;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/5 15:23
 *********************************************/
public class Demo1 {
    private interface Inter{}
    private static class Clazz implements Inter{}
    public static void main(String[] args) {
        Clazz[] clazz = new Clazz[10];
        System.out.println(clazz instanceof Clazz[]);
    }
}
