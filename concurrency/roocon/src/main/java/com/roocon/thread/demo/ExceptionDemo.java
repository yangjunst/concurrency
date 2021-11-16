package com.roocon.thread.demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/8/27 14:30
 * Program Goal:
 *********************************************/
class ExceptionA extends RuntimeException{
    public ExceptionA(Throwable cause) {
        super(cause);
    }
}

class ExceptionB extends RuntimeException{
    public ExceptionB(Throwable cause) {
        super(cause);
    }
}class ExceptionC extends RuntimeException{
    public ExceptionC(Throwable cause) {
        super(cause);
    }
}
class ExceptionD extends RuntimeException{
    public ExceptionD(Throwable cause) {
        super(cause);
    }
}

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            throw new RuntimeException(new ExceptionA(new ExceptionB(new ExceptionC(new ExceptionD(new Exception())))));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
