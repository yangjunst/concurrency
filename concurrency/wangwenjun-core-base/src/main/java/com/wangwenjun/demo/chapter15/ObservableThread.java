package com.wangwenjun.demo.chapter15;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/1 14:19
 *********************************************/
public class ObservableThread<T> extends Thread implements Observable {
    private class Yang{
        @Override
        public String toString() {
            return "Yang{}";
        }
    }

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;


    public ObservableThread(Task<T> task) {
        this(new TaskLifeCycle.EmptyLifeCycle<>(), task);
    }


    public ObservableThread(TaskLifeCycle<T> lifeCycle, Task<T> task) {
        super("工作线程");
        if (task == null) {
            throw new IllegalArgumentException("the task is required...");
        }
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    @Override
    public Cycle getCycle() {
        return null;
    }

    @Override
    public void run() {
        update(Cycle.STARTED, null, null);

        try {

            update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            update(Cycle.DONE, result, null);
        } catch (Exception e) {
            update(Cycle.ERROR, null, e);

        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifeCycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    lifeCycle.onFinish(currentThread(), result);
                case ERROR:
                    lifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if(cycle==Cycle.ERROR){
                System.out.println("cycle  error......");
                ex.printStackTrace();
            }
        }


    }
}
