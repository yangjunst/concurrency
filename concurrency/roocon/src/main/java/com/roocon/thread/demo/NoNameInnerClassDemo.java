package com.roocon.thread.demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/21 10:33
 * Program Goal:
 *********************************************/
class OuterClass {
    protected void show() {
        System.out.println("OuterClass Show");
    }

    public void love() {
        System.out.println("OuterClass Love");
    }

    void hate() {
        System.out.println("OuterClass Hate");
    }
}

public class NoNameInnerClassDemo {
    public static void main(String[] args) {
        OuterClass outerClass =  new OuterClass() {
            @Override
            public void love() {
                System.out.println("sub love");
            }

            @Override
            protected void show() {
                System.out.println("sub show");
            }
        };
        outerClass.hate();
    }
}
