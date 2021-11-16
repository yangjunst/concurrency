package com.wangwenjun.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/2 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ForkJoinPoolTest {

    private static int[] data =null;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        data= IntStream.rangeClosed(1,1000).toArray();
        System.out.println("result=> " + calc());
//        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinTask<Integer> joinTask = forkJoinPool.submit(task);
//        joinTask.get();
//        System.out.println("AccumulatorRecursiveTask >>" + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        ForkJoinTask<Void> forkJoinTask = forkJoinPool.submit(action);
        forkJoinTask.get();
        System.out.println("AccumulatorRecursiveAction >>" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }


    private static int calc() {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }

}