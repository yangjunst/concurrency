package com.maomao.learn.concurrcy.base;
/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/6/28 10:38
 *********************************************/

public class ThreadLocalDemo1 {
    public static class Person {
        private String name="b";

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }

        Person() {
            System.out.println(Thread.currentThread().getName()+"-->"+this);
        }
    }

    public static void main(String[] args) {
        Person p=new Person();
        ThreadLocal<Person> local=ThreadLocal.withInitial(()->p);
        new Thread(()->local.get().setName("a")).start();
        new Thread(()-> System.out.println(local.get())).start();
        new Thread(()-> System.out.println(local.get())).start();
        new Thread(()-> System.out.println(local.get())).start();
        new Thread(()-> System.out.println(local.get())).start();

    }
}
