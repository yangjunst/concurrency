package questions1000ofcompany;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 10:28
 * Program Goal:
 * {@link Thread#setDaemon(boolean)}
 * if run after {@link Thread#start()}
 * IllegalThreadStateException
 *********************************************/
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread thread=new Thread("t1");
        thread.start();
        thread.setDaemon(true);
    }
}
