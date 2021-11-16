package demo;

import java.util.Collections;
import java.util.Map;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 19:14
 * Program Goal:
 *********************************************/
public class SynchronizedMapDemo {
    public static void main(String[] args) {
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(null);
        System.out.println(objectObjectMap);

    }
}
