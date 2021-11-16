package com.wangwenjun.juc.collections.custom;
import java.lang.reflect.ParameterizedType;
/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/14 12:12
 * Program Goal:获取Class<K,V>类型
 *********************************************/
public class Generic<K,V>{
    public static void main(String[] args) {
        Generic<String,Integer> generic=new Generic<>("age",31);
        ParameterizedType pt = (ParameterizedType) generic.getClass().getGenericSuperclass();
        Class actualTypeArgument = (Class) (pt.getActualTypeArguments()[0]);
        System.out.println(actualTypeArgument);
        System.out.println("===============================================");
    }
    K k;
    V v;
    Inner<K,V> inner;

    @Override
    public String toString() {
        return "Generic{" +
                "inner=" + inner +
                '}';
    }

    public Generic(K k, V v) {
        this.k = k;
        this.v = v;
        inner=new Inner<>(k,v);
    }

    static class Inner<K,V>{
        K k;
        V v;
        public Inner(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }
    }
}
