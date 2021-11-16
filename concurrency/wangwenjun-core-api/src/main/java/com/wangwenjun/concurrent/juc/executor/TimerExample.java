package com.wangwenjun.concurrent.juc.executor;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerExample
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println(new Date());
            }
        }, 1000, 60 * 1000);
    }
}
