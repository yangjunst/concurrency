package com.maomao.learn.concurrcy.collection;

import java.util.concurrent.LinkedBlockingDeque;

/********************************************
 * 文件名称: LinkBlockingDequeDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/16 14:40
 *********************************************/
public class LinkBlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque deque=new LinkedBlockingDeque();
        deque.put(1);
        deque.take();
    }
}
