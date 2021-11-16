package questions1000ofcompany;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/30 13:23
 * Program Goal:
 *********************************************/
public class LockRecordDemo {
    public synchronized void show() {

    }

    public void demo(String[] args) {
        synchronized (args){

        }
    }
    public static void main(String[] args) {
        System.out.println("ok");
    }
}
