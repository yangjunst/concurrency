package example;


/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/29 18:10
 * Program Goal:
 *********************************************/
public class ObjectHeaderAndLockDemo {
    private Object o=new Object();
    private Object o1=new Object();
    public static void main(String[] args) {
        ObjectHeaderAndLockDemo demo=new ObjectHeaderAndLockDemo();
        synchronized (demo){}
    }
}
