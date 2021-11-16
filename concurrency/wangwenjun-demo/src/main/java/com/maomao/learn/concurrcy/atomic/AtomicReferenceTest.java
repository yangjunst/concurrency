package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/********************************************
 * 文件名称: AtomicReferenceTest.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/15 14:13
 *********************************************/
public class AtomicReferenceTest {

    static final AtomicReference<DebitCard> reference = new AtomicReference<>(new DebitCard("mao", 0));


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    final DebitCard dc = reference.get();
                    DebitCard newCard = new DebitCard("yang", dc.getAmount() + 10);
                    if (reference.compareAndSet(dc, newCard)) {
                        System.out.println(newCard);
                    }
                    ;
                    try {
                        TimeUnit.MILLISECONDS.sleep(111);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }

}
