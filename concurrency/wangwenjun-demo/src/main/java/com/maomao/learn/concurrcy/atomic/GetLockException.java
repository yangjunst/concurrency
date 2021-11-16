package com.maomao.learn.concurrcy.atomic;

import java.util.concurrent.Executor;

/********************************************
 * 文件名称: GetLockException.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/3/10 12:48
 *********************************************/
public class GetLockException extends Exception {
    public GetLockException() {
    }

    public GetLockException(String message) {
        super(message);
    }
}
