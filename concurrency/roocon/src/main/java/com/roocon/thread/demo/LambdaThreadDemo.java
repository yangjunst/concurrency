package com.roocon.thread.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/21 11:56
 * Program Goal:
 *********************************************/
public class LambdaThreadDemo {
    public static void main(String[] args) {
        List<Date> list= Arrays.asList(new Date(),new Date(),new Date());
        list.parallelStream().map(d->d.toLocaleString()).forEach(System.out::println);
    }
}
