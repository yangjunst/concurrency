package demo;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/23 15:48
 * Program Goal:
 *********************************************/
public final class FinalClassDemo {
    public static void test1() {
    }

    public void test2() {
    }

    public static void main(String[] args) {
        Arrays.stream(FinalClassDemo.class.getDeclaredMethods())
                .forEach(e -> System.out.println(e.getName()+"-->"+Modifier.toString(e.getModifiers())));
    }
}
