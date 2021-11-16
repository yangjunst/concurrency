package com.maomao.learn.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/********************************************
 * 文件名称: AtomicLambda.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/12 15:23
 *********************************************/
public class AtomicLambda {

    private static void display(List<Apple> apples, DoubleBinaryOperator operator){
        for(Apple apple:apples){
            double v = operator.applyAsDouble(apple.getWeight(), apple.hashCode());
            if(v<=5){
                System.out.println(apple);
            }
        }
    }

    public static void main(String[] args) {
        List<Apple> apples= Arrays.asList(
                new Apple("red","big",32),
                new Apple("green","small",12),
                new Apple("pink","bigger",22),
                new Apple("white","smaller",42),
                new Apple("black","normal",15),
                new Apple("yellow","larger",42),
                new Apple("blue","normal",15)
        );
        Collections.sort(apples, Comparator.comparing(Apple::getName)
                .reversed()
                .thenComparing(Apple::getWeight)
        );
        display(apples,(a,b)->b%a);
    }
}
