package gp03.com.gupaoedu.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ConditionExample {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        ConditionDemoWait cd=new ConditionDemoWait(lock,condition);
        ConditionDemeNotify cdn=new ConditionDemeNotify(lock,condition);

        new Thread(cd).start();
        new Thread(cdn).start();
    }
}
