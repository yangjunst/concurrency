package com.maomao.learn.concurrcy.atomic;

/********************************************
 * 文件名称: DebitCard.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/15 14:12
 *********************************************/
public class DebitCard {
    public volatile String account ;
    public  volatile int amount ;

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
}
