package com.maomao.learn.design;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/5/26 10:50
 *********************************************/
public interface InterfaceInstanceDemo1 {
    void display(String param);
    void show(String param);
        class InterfaceInstanceImpl implements InterfaceInstanceDemo1{

        @Override
        public void display(String param) {
            System.out.println("display..."+param);
        }

        @Override
        public void show(String param) {
            System.out.println("show..."+param);
        }
    }

    static InterfaceInstanceDemo1 getInstance(){
        return new InterfaceInstanceImpl();
    }

    static void main(String[] args) {
        InterfaceInstanceDemo1 instance = getInstance();

        instance.show("yang jun");
        instance.display("mao mao");
    }
}
