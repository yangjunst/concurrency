package gp03.com.gupaoedu.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ConditionDemoWait implements Runnable{
    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        System.out.println("begin - ConditionDemoWait");
        lock.lock();
        try{
            condition.await(); //让当前线程阻塞，Object.wait();
            System.out.println("end - ConditionDemoWait");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
