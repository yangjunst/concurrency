package demo;

import java.util.*;
import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/13 15:30
 * Program Goal:
 *********************************************/
public class ConcurrentContainerDemo {
    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList();
        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList();
        LinkedList linkedList=new LinkedList();
        ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();
        HashSet hashSet=new HashSet();
        CopyOnWriteArraySet copyOnWriteArraySet=new CopyOnWriteArraySet();
        HashMap hashMap=new HashMap();
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        TreeSet treeSet=new TreeSet();
        ConcurrentSkipListSet concurrentSkipListSet=new ConcurrentSkipListSet();
        TreeMap treeMap=new TreeMap();
        ConcurrentSkipListMap concurrentSkipListMap=new ConcurrentSkipListMap();
    }
}
