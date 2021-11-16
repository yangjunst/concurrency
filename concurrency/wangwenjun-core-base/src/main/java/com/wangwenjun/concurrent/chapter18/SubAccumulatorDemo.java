package com.wangwenjun.concurrent.chapter18;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/3 16:17
 *********************************************/
public class SubAccumulatorDemo extends IntegerAccumulator{
    private int init;
    public SubAccumulatorDemo(int init) {
        super(init);
        this.init=init;
    }

    @Override
    public IntegerAccumulator add(int i) {
        init+=i;
        return this;
    }

    @Override
    public int getValue() {
        return init;
    }
}
