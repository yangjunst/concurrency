package gp03.com.gupaoedu.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ConditionDemeNotify implements Runnable{
    private Lock lock;
    private Condition condition;

    public ConditionDemeNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        System.out.println("begin - ConditionDemeNotify");
        lock.lock(); //synchronized(lock)
        try{
            condition.signal(); //让当前线程唤醒  Object.notify(); //因为任何对象都会有monitor
            System.out.println("end - ConditionDemeNotify");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
