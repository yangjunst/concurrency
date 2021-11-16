package gp03.com.gupaoedu.concurrent;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SeqExample {
    private volatile static int x=0,y=0;
    private volatile static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException {
        int i=0;
        for(;;){
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread t1=new Thread(()->{
                a=1;
                x=b;

                //
                x=b;
                a=1;
            });
            Thread t2=new Thread(()->{
                b=1;
                y=a;
                //
                y=a;
                b=1;
            });
            /**
             * 可能的结果：
             *  1和1
             *  0和1
             *  1和0
             *  ----
             *  0和0
             */
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            String result="第"+i+"次("+x+","+y+")";
            if(x==0&&y==0){
                System.out.println(result);
                break;
            }else{

            }
        }
    }
}
