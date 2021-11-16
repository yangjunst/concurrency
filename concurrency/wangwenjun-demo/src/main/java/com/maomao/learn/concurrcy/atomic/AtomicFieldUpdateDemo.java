package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/********************************************
 * 文件名称: AtomicFieldUpdateDemo.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/15 15:04
 *********************************************/
public class AtomicFieldUpdateDemo {
    static class DebitCard {
         private volatile String account;
         private volatile int amount;

        public DebitCard(String account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        public String getAccount() {
            return account;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "DebitCard{" +
                    "account='" + account + '\'' +
                    ", amount=" + amount +
                    '}';
        }

        public static void test(){
            AtomicIntegerFieldUpdater<DebitCard> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(DebitCard.class, "amount");

            int i = atomicIntegerFieldUpdater.addAndGet(new DebitCard("", 1), 3);


            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        AtomicFieldUpdateDemo.DebitCard.test();

    }

}
