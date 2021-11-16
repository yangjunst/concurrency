package com.maomao.learn.language;

import java.util.Arrays;
import java.util.LinkedHashMap;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/6 17:16
 *********************************************/
public class LinkedHashMapDemo1 {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 15; i++) {
            map.put(String.valueOf(i), String.valueOf(i * 10));
        }
        map.put("10", "1111");
        System.out.println(map);
        Object[] objects = map.keySet().stream().filter(k -> k.length() == 1).toArray();
        Arrays.stream(objects).forEach(k->map.remove(k));
        System.out.println(map);

    }
}
