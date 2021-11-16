package com.maomao.learn.design.demo;

import java.util.HashMap;
import java.util.Map;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 10:06
 *********************************************/
public class GenericDemo1 {
    public static <T,R> Map<T,R> main() {
        return new HashMap<T,R>();
    }
    public static void main(String[] args) {
        Map<String, Object> main = main();


    }
}
