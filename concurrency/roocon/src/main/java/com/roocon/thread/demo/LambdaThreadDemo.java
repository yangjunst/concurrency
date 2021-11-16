package com.roocon.thread.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 11:56
 * Program Goal:
 *********************************************/
public class LambdaThreadDemo {
    public static void main(String[] args) {
        List<Date> list= Arrays.asList(new Date(),new Date(),new Date());
        list.parallelStream().map(d->d.toLocaleString()).forEach(System.out::println);
    }
}
