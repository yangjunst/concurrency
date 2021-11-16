package com.maomao.learn.java8;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/********************************************
 * 文件名称: TimeUnitDemi.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/18 13:51
 *********************************************/
public class TimeUnitDemo {
    public static void main(String[] args) {
        TimeUnit unit=TimeUnit.MINUTES;

        TimeUnit timeUnit=TimeUnit.HOURS;
        long convert =timeUnit.toMinutes(2);

        System.out.println(convert);

    }

    class DelayElement implements Delayed{
        private int e;

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            DelayElement element=(DelayElement) o;
            int i = this.e - element.e;
            return 0;
        }
    }
}
