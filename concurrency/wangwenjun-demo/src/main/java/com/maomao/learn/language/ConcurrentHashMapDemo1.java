package com.maomao.learn.language;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/10 10:16
 *********************************************/
public class ConcurrentHashMapDemo1 {

    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Integer>> container
            = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            container.computeIfAbsent("hello"+i%3, k -> new ConcurrentLinkedQueue<>());
            container.get("hello"+i%3).add(i*15);
        }
        container.forEach((k,v)->{
            v.forEach(e-> System.out.print(e+","));
        });
    }
}
