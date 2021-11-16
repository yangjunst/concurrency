package demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/19 19:08
 * Program Goal:
 *********************************************/
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer=new Timer();
        TimerTask task1=new TimerTask() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        };

        timer.schedule(task1,0,1000);
        /**
         * Exception in thread "main" java.lang.IllegalStateException:
         * Task already scheduled or cancelled
         */
//        timer.schedule(task1,0,1000);
        TimerTask task2=new TimerTask() {
            @Override
            public void run() {
                System.out.println("---> ok");
            }
        };
        timer.schedule(task2,0,1000);
    }
}
