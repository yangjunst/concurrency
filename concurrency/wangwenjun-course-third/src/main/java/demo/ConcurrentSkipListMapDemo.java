package demo;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/13 14:37
 * Program Goal:
 *********************************************/
public class ConcurrentSkipListMapDemo {
    /**
     * 使用 Comparator.comparing 进行排序
     */
    public static void main(String[] args) {
        new TreeMap<>(Comparator.comparing(AtomicInteger::get));
        new ConcurrentSkipListMap(Comparator.comparing(AtomicInteger::get));
    }

    private static void concurrentSkipListMapTest() {
        ConcurrentSkipListMap<Integer,String> map=new ConcurrentSkipListMap<>();
        map.put(31,"yangjun");
        map.put(9,"maomao");
        map.put(29,"yangjunst");
        map.put(32,"yj");
        System.out.println(map);
    }
}
