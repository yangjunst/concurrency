package com.wangwenjun.concurrent.chapter18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Immutable {
    private final List<String> list;

    public Immutable(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
//        return Collections.unmodifiableList(this.list)
    }

    public static void main(String[] args) {
        List<String> old = Arrays.asList("a", "b", "c", "d");
        List<String> list = new ArrayList<>();
        list.addAll(old);
        Immutable table=new Immutable(list);
        list=table.getList();
        list.remove(1);
        System.out.println(list);
        System.out.println(old);
    }
}