package com.wangwenjun.demo.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/2 16:34
 *********************************************/
public class CharacterToCharDemo {

    public static<T> T[] show(IntFunction<T[]> function){
        return function.apply(1);
    }

    private static List<Character> container = new ArrayList<>();
    public static void change(int length) {
        for (int i = 0; i < length; i++) {
            container.add(i, (char) ('c' + i));
        }
    }

    public static void main(String[] args) {

        int[][] ints = show((e) -> new int[][]{{e}});

        System.out.println(ints.length);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
