package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/21 19:25
 * Program Goal:
 *********************************************/
public class CollectionsSortDemo {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Collections.sort(list,(o1,o2)->o1.compareTo(o2));
    }
}
