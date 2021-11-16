package com.wangwenjun.concurrent.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }


    public void resetContext() {
         threadLocal.set(new Context());
    }

    private ActionContext(){

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.rangeClosed(1,10).forEach(i->{
            service.execute(()->{
                if(i%3==1){
                    getActionContext().getContext().setCardId(Thread.currentThread().getName()+"--->hello");
                }else{
                    getActionContext().getContext().setName(Thread.currentThread().getName()+"--->world");
                }
                System.out.println(getActionContext().getContext());
                getActionContext().resetContext();
            });
        });
        service.shutdown();

    }


}