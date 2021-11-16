package example;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/4 16:14
 * Program Goal:
 *********************************************/
class AQS {
    public class ConditionObject implements Condition {

        @Override
        public void await() throws InterruptedException {
            System.out.println("await....");
        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

            System.out.println("signal....");
        }

        @Override
        public void signalAll() {

        }
    }
}

public class CustomConditionDemo {
    class Sync extends AQS {
        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync();

    public Condition newCondition() {
        return sync.newCondition();
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }

        try{
            lock.lock();
            condition.await();
        }finally {
            lock.unlock();
        }

    }
}
