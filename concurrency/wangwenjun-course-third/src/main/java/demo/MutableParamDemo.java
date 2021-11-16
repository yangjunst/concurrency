package demo;

import java.util.Arrays;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/13 9:32
 * Program Goal:可变参数，方法调用不传参时，a为空数组，不为null
 *********************************************/
public class MutableParamDemo {
    public static void test(String... a) {
        System.out.println(Arrays.toString(a));//[]
    }
    public static void main(String[] args) {
        test();
    }
}
