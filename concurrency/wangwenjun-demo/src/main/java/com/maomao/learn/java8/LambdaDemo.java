package com.maomao.learn.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/********************************************
 * 文件名称: LambdaDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/13 13:33
 *********************************************/

public class LambdaDemo {
    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            R r = f.apply(t);
            result.add(r);
        }
        return result;
    }

    private static void demo() {
        List<Integer> list = new ArrayList<>();
        for (int i = 300; i < 400; i++) {
            list.add(i);
        }
    }

    private static void demo2(List<Integer> list,IntPredicateInterface predicateInterface){
        for (Integer i:list){
            if(predicateInterface.test(i)){
                System.out.println(i);
            }
        }
    }


    interface IntPredicateInterface{
        boolean test(int t);
    }

    interface ThirdFunction<T,U,V,R> {
        R apply(T t,U u,V v);
    }

    private void display(){

    }

    public static void main(String[] args) {
        ThirdFunction<Integer,Integer,Integer,Color> f=(x,y,z)->new Color(x,y,z);
        f=Color::new;
    }
}
class Color{
    private int x;
    private int y;
    private int z;

    public Color(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
