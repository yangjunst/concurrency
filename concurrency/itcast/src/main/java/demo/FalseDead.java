package demo;

/********************************************
 * ������Ա: ѩ������
 * ��ְʱ��: 2016/05/16
 * ����ʱ��: 2021/10/19 15:11
 * Program Goal:
 * ����������Ӧ����ĳ�������ϵ�����
 * �����������Ǽ����������ϸ������ϵ�����
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
