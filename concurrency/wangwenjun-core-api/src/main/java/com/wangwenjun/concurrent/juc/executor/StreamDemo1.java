package com.wangwenjun.concurrent.juc.executor;

import scala.util.parsing.combinator.testing.Str;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/26 9:33
 *********************************************/
public class StreamDemo1 {
    public static void main(String[] args) {
        int sum = IntStream.rangeClosed(1, 1000).sum();
        System.out.println(sum);
        int[] ints = IntStream.rangeClosed(1, 1000).toArray();
        for(int i:ints){
            System.out.print(i+",");
        }
        System.out.println();

        long sum1 = LongStream.rangeClosed(1, 100_0).sum();
        System.out.println(sum1);
        long[] longs = LongStream.rangeClosed(1, 100_0).toArray();
        for(long l:longs){
            System.out.print(l+",");
        }
    }
}
