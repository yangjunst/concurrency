package com.maomao.learn.concurrcy.base;

/********************************************
 * 文件名称: RecursiveDemo1.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/30 12:48
 *********************************************/
public class RecursiveDemo1 {
    private static int enumerate(int i){
        for(int j=0;j<5;i++){
            i=enumerate(i++);
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(enumerate(0));
    }
}
