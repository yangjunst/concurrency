package demo;

import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/23 16:41
 * Program Goal:
 *********************************************/
public class ModifyExceptionInfoDemo {
    public static void main(String[] args) {
        ImmutableMap<String,Integer> map=ImmutableMap.<String,Integer>builder().build();
        map.put("a",1);//java.lang.UnsupportedOperationException
        Map m1= Collections.unmodifiableMap(new HashMap<String,Integer>());
        m1.put("b",2);// java.lang.UnsupportedOperationException
    }
}
