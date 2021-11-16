package com.maomao.learn.concurrcy.collection;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/********************************************
 * 文件名称: LinkedTransferQueue.java
 * 功能说明:
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/17 13:36
 *********************************************/
public class LinkedTransferQueueDemo {
    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        List<Callable<String>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<String>) () -> {
            String take = queue.take();
            System.out.println(take);
            return take;
        }).collect(Collectors.toList());
        ExecutorService executorService = Executors.newCachedThreadPool();
        collect.stream().forEach(executorService::submit);
       IntStream.range(10,15).boxed().map(String::valueOf).forEach(queue::tryTransfer);
    }
}
