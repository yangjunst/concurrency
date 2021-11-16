package com.wangwenjun.demo.chapter19;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/27 11:32
 *********************************************/
public interface FutureService {
    <T> Future<T> submit(Callable<T> c);

    <T> void submit(Callable<T> c1, Consumer<T> c2);
}
