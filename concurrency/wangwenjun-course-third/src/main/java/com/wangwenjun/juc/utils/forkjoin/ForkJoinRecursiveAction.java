package com.wangwenjun.juc.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/13
 * QQ交流群:601980517，463962286
 ***************************************/
public class ForkJoinRecursiveAction {

    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final ForkJoinPool forkJoinPool = new ForkJoinPool(100);

        ForkJoinTask<Void> submit = forkJoinPool.submit(new CalculateRecursiveAction(0, 10000));

        submit.get();

//        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);

        Optional.of(SUM).ifPresent(System.out::println);

    }

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;

        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            System.out.println("---------->"+Thread.currentThread().getName());

            if ((end - start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, middle);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);
               leftAction.fork();
               rightAction.fork();
               invokeAll(leftAction,rightAction);
            }
        }
    }
}
