package com.maomao.learn.language;

import java.util.concurrent.ConcurrentHashMap;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/10 8:49
 *********************************************/
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("Hello", (k) -> k.length());
        System.out.println(map);
        map.computeIfPresent("Hello", (k, v) -> {
                    System.out.println("k->" + k + ",v->" + v);
                    return v + 2;
                }
        );
        map.compute("Hello",(k,v)->k.length()+10*v);
        System.out.println(map);

    }
}
