package com.wangwenjun.concurrent.juc.executor;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureCallbackExample
{

    private static void futureCallback()
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService decoratorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> listenableFuture = decoratorService.submit(() ->
        {
            TimeUnit.SECONDS.sleep(10);
            return "I am the result";
        });

        Futures.addCallback(listenableFuture, new FutureCallback<String>()
        {
            @Override
            public void onSuccess(@Nullable String result)
            {
                System.out.println("The Task completed and result:" + result);
                decoratorService.shutdown();
            }

            @Override
            public void onFailure(Throwable t)
            {
                t.printStackTrace();
            }
        }, decoratorService);
    }

    private static void listenableFuture()
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService decoratorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> listenableFuture = decoratorService.submit(() ->
        {
            TimeUnit.SECONDS.sleep(10);
            return "I am the result";
        });

        listenableFuture.addListener(() ->
        {
            System.out.println("The task completed.");
            try
            {
                System.out.println("The task result:" + listenableFuture.get());
                decoratorService.shutdown();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                System.out.println("The task failed");
            }
        }, decoratorService);
    }

    public static void main(String[] args) throws InterruptedException
    {
        futureCallback();
    }
}
