package com.roocon.thread.demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/21 10:59
 * Program Goal:
 *********************************************/
public class TimerDemo {
    private static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("time task is running..." + new Date().toLocaleString());
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new MyTimerTask();
        timer.schedule(timerTask,0,1000);

    }
}
