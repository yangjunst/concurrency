package com.maomao.learn.java8;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/********************************************
 * 文件名称: Apple.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/12 18:49
 *********************************************/
public class Apple {
    public static void m(Runnable r){
     r.run();

    }
    static class C {

        public void call(Object o) {
        }
        public int display(){
            return 0;
        }
    }
    public static void main(String[] args) {
        C c=new C();
        m(c::display);
    }
    private String name;
    private String type;
    private int weight;

    public Apple(String name, String type, int weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
