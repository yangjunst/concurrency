package com.roocon.thread.demo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/8/23 10:02
 * Program Goal:
 * AtomicIntegerArray��int[]����Ԫ�ص�һ��������
 * �޸�AtomicIntegerArray��Ԫ�ص�ֵ��
 * ��Ӱ��int[]����Ԫ�ص�ֵ
 *********************************************/
public class AtomicIntegerArrayDemo {
    private static final int[] arr={1,3,4,6,7,8,9};
    private static final AtomicIntegerArray a=new AtomicIntegerArray(arr);
    public static void getAndAdd(int index,int n){
        a.getAndAdd(index,n);
    }

    public static void main(String[] args) {
        getAndAdd(1,10);
        System.out.println(Arrays.toString(arr));
        System.out.println(a.toString());
    }
}
