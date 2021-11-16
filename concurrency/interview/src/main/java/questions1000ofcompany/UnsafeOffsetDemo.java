package questions1000ofcompany;

import java.util.concurrent.atomic.AtomicInteger;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/30 5:50
 * Program Goal:
 *********************************************/
public class UnsafeOffsetDemo {
    public static void main(String[] args) {
        AtomicInteger i=new AtomicInteger(1);
        i.incrementAndGet();
    }
}
