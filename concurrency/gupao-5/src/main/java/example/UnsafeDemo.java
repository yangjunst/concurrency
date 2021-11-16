package example;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/16 17:19
 * Program Goal:
 *********************************************/
public class UnsafeDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe= (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);




    }
}
