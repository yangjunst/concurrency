package gp03.com.gupaoedu.concurrent;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class VolatileExample {

    public volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (!stop) { //load
                i++;//。。。。
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                /* System.out.println("");*/
            }
        });
        t1.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true; //store
        //storeload()
    }
}
