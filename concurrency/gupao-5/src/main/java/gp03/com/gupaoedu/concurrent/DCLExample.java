package gp03.com.gupaoedu.concurrent;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class DCLExample {
    static volatile DCLExample instance;
    public static DCLExample getInstance(){
        if(instance==null){
            synchronized (DCLExample.class){
                if(instance==null){
                    instance=new DCLExample();
                }
            }
        }
        return instance;
    }
}
