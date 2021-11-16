package questions1000ofcompany;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 13:25
 * Program Goal:
 *********************************************/
public class ThreadStateDemo {
    public static void main(String[] args) {
        Thread t=new Thread();
        System.out.println(t.getState());
    }
}
