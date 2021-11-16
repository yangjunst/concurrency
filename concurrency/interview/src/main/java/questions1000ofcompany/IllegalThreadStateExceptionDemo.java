package questions1000ofcompany;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/28 9:38
 * Program Goal:
 *********************************************/
public class IllegalThreadStateExceptionDemo {
    /**
     * java.lang.IllegalThreadStateException
     */
    public static void test1() {
        Thread t1=new Thread();
//        t1.start();
        t1.start();
    }

    /**
     * java.lang.IllegalThreadStateException
     */
    public static void test2() {
        Thread t=new Thread();
        t.start();
        t.setDaemon(true);

    }
    public static void main(String[] args) {
        test2();
    }
}
