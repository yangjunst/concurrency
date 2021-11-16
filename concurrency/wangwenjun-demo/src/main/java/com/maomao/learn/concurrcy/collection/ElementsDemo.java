package com.maomao.learn.concurrcy.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/********************************************
 * 文件名称: ElementsDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/2 10:33
 *********************************************/
public class ElementsDemo {
    public static void display(String...  args) {
        if(args.length==0){
            System.out.println("[]");
            return;
        }
        StringBuilder builder=new StringBuilder();
        builder.append("[");
        for (String s:args){
            builder.append(s).append(",");
        }
        builder.replace(builder.length()-1,builder.length(),"]");
        System.out.println(builder.toString());
    }
    public static void main(String[] args) {
        List<String> list= Arrays.asList("Hello","World","Scale","Java","Thread");
        display(list.toArray(new String[list.size()]));
        display(new String[0]);
    }
}
