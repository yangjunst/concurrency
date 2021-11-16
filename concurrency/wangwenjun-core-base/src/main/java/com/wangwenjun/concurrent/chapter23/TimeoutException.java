package com.wangwenjun.concurrent.chapter23;

public class TimeoutException extends Exception
{
    public TimeoutException(String message)
    {
        super(message);
    }
}
