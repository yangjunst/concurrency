package demo;

import com.google.common.collect.ImmutableMap;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/23 16:37
 * Program Goal:
 * ImmutableMap.<Integer, String>builder()
 * 泛型此处的位置所属含义
 *********************************************/
public class GenericDemo {
    public static void main(String[] args) {
        ImmutableMap<Integer, String> a = ImmutableMap.<Integer, String>builder()
                .put(1, "1").build();
    }
}
