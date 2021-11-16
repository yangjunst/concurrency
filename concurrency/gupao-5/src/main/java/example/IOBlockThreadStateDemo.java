package example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.concurrent.TimeUnit;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/29 16:18
 * Program Goal:
 *********************************************/
public class IOBlockThreadStateDemo {
    private static final String filePath="F:\\JAVA全栈\\名师讲堂Java开发实战经典.zip";
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            final File file=new File(filePath);
            try (BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
                 BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("F:\\JAVA全栈\\copy.zip"))){
                byte[] buf=new byte[Integer.MAX_VALUE/10];
                int len=-1;
                System.out.println("read  start ...");
                while ((len=bis.read(buf))!=-1){
                    System.out.println("read end ...");
                    System.out.println("start write ...");
                    bos.write(buf,0,len);
                    System.out.println("wirte end ...");
                    System.out.println(len);
                    bos.flush();
                }
            }catch (Exception e){
                System.out.println(e.getClass());
            }
        },"io-thread");
        t.start();
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.getName()+"--->"+t.getState());
        }
    }
}
