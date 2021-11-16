package com.wangwenjun.concurrent.juc.executor;

import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

public class CompletableFutureExample
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        handleError();
    }

    private static void handleError()
    {
        CompletableFuture.<String>supplyAsync(() ->
        {
            throw new RuntimeException();
        }).handle((r, e) ->
        {
            if (e != null)
            {
                return "ERROR";
            } else
            {
                return r;
            }
        }).thenAccept(System.out::println);
    }

    private static void join()
    {
        CompletableFuture<String> f1
                = CompletableFuture.supplyAsync(() -> "Java");
        CompletableFuture<String> f2
                = CompletableFuture.supplyAsync(() -> "Parallel");
        CompletableFuture<String> f3
                = CompletableFuture.supplyAsync(() -> "Future");
        f1.join();
    }

    private static void anyOf() throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> f1
                = CompletableFuture.supplyAsync(() -> "Java");
        CompletableFuture<String> f2
                = CompletableFuture.supplyAsync(() -> "Parallel");
        CompletableFuture<String> f3
                = CompletableFuture.supplyAsync(() -> "Future");

        CompletableFuture<Void> future = CompletableFuture.anyOf(f1, f2, f3).thenRun(() ->
        {
            try
            {
                System.out.println(f1.isDone() + " and result:" + f1.get());
                System.out.println(f2.isDone() + " and result:" + f2.get());
                System.out.println(f3.isDone() + " and result:" + f3.get());
            } catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        });

        future.get();
    }

    private static void allOf()
            throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> f1
                = CompletableFuture.supplyAsync(() -> "Java");
        CompletableFuture<String> f2
                = CompletableFuture.supplyAsync(() -> "Parallel");
        CompletableFuture<String> f3
                = CompletableFuture.supplyAsync(() -> "Future");

        CompletableFuture<Void> future = CompletableFuture.allOf(f1, f2, f3).thenRun(() ->
        {
            try
            {
                System.out.println(f1.isDone() + " and result:" + f1.get());
                System.out.println(f2.isDone() + " and result:" + f2.get());
                System.out.println(f3.isDone() + " and result:" + f3.get());
            } catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        });

        future.get();

    }

    private static void thenCombine()
    {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Java")
                .thenCombine(CompletableFuture.supplyAsync(() -> " Scala"),
                        (s1, s2) -> s1 + s2);

        completableFuture.thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }

    private static void thenCompose() throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Java")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Scala"));

        completableFuture.thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }

    private static void thenRunAsync()
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> "Java", executor)
                .thenAcceptAsync(v ->
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("thenAccept:" + currentThread());
                    System.out.println(v);
                }).thenRunAsync(
                () ->
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("All of task completed. " + currentThread());
                },
                executor
        );
    }


    private static void thenRun()
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() ->
        {
            System.out.println("supplyAsync:" + currentThread());
            return "Java";
        }, executor).thenAcceptAsync(v ->
        {
            System.out.println("thenAccept:" + currentThread());
            System.out.println(v);
        }).thenRun(
                () -> System.out.println("All of task completed. " + currentThread())
        );
    }

    private static void thenAccept()
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() ->
        {
            System.out.println("supplyAsync:" + currentThread());
            return "Java";
        }, executor).thenAccept(v ->
        {
            System.out.println("thenAccept:" + currentThread());
            System.out.println(v);
        });
        executor.shutdown();
    }

    private static void thenAcceptAsync()
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() ->
        {
            System.out.println("supplyAsync:" + currentThread());
            return "Java";
        }, executor).thenAcceptAsync(v ->
        {
            System.out.println("thenAcceptAsync:" + currentThread());
            System.out.println(v);
        });
        executor.shutdown();
    }

    private static void thenApplyAsync() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() ->
                {
                    System.out.println("supplyAsync:" + currentThread());
                    return "Java";
                }, executor).thenApplyAsync(e ->
                {
                    System.out.println("thenApplyAsync:" + currentThread());
                    return e.length();
                });

        assert future.get() == 4;
    }

    private static void thenApply() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() ->
                {
                    System.out.println("supplyAsync:" + currentThread());
                    return "Java";
                }, executor).thenApply(e ->
                {
                    System.out.println("thenApplyAsync:" + currentThread());
                    return e.length();
                });
        assert future.get() == 4;
    }

    private static void runAsync()
    {
        CompletableFuture.runAsync(() ->
        {
            System.out.println("async task.");
        });

       /* CompletableFuture.runAsync(() ->
        {
            System.out.println("async task.");
        }, Executors.newCachedThreadPool());*/
    }

    private static void runSupplier() throws ExecutionException, InterruptedException
    {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 353);

        /*
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 353
                        , Executors.newCachedThreadPool());
         */
        assert future.get() == 353;
    }

    private static void completableFutureAsFutureCancel()
    {
        CompletableFuture<Double> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
                completableFuture.cancel(false);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        try
        {
            completableFuture.get();
        } catch (Exception e)
        {
            assert e instanceof CancellationException;
        }
    }

    private static void completableFutureAsFuture()
    {
        CompletableFuture<Double> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
                completableFuture.complete(1245.23D);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        assert completableFuture.getNow(0.0D) == 0.0;
        try
        {
            assert completableFuture.get() == 1245.23D;
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

}
