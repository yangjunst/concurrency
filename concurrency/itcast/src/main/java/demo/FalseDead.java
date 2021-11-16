package demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/19 15:11
 * Program Goal:
 * 假死跟死锁应当有某种意义上的区别
 * 首先下例子是假死，并非严格意义上的死锁
 *********************************************/
public class FalseDead {
    private static final Object LOCK=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (LOCK){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
        new Thread(()->{
            synchronized (LOCK){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
