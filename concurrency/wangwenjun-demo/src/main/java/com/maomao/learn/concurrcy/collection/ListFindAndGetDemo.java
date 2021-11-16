package com.maomao.learn.concurrcy.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************************
 * 文件名称: ListFindAndGetDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/2 12:24
 *********************************************/
public class ListFindAndGetDemo {
    public static void main(String[] args) {
        List<String> list= Arrays.asList("Hello","World","Scale","Java","Arithmetic","Java");
        String s = list.get(4);
        System.out.println("get result-->"+s);
        System.out.println(list.indexOf("Java"));
    }
}
