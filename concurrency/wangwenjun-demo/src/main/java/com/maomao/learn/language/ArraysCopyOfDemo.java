package com.maomao.learn.language;

import java.util.Arrays;

/********************************************
 * 文件名称: ArraysCopyOfDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/30 13:07
 *********************************************/
public class ArraysCopyOfDemo {
    private static class Obj{
        private String name;

        public Obj(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        Obj[] objs={new Obj("A"),new Obj("B"),new Obj("C"),new Obj("D")};
        Obj[] objs1 = Arrays.copyOf(objs, 2);
        Obj[] result=new Obj[2];

        boolean b = objs[0] == objs1[0];

        System.out.println(b);

        System.arraycopy(objs,0,result,0,2);
        b = objs[0] == result[0];
        System.out.println(b);
    }
}
