package example;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/4 12:32
 * Program Goal:
 * transient修饰变量，使之不能成为对象持久化的一部分
 *********************************************/
public class DeSerializedDemo implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DeSerializedDemo demo=new DeSerializedDemo("b",new Date(),"Comparable");
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(demo);
        oos.flush();
        ByteArrayInputStream bios=new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bios);
        DeSerializedDemo deSerializedDemo= (DeSerializedDemo) ois.readObject();
        printObject(demo);
        printObject(deSerializedDemo);
        System.out.println(demo.getClass()==deSerializedDemo.getClass());
    }

    private static void printObject(DeSerializedDemo demo) {
        System.out.println(demo.getClass().getName());
        Arrays.stream(demo.getClass().getDeclaredFields()).forEach(e-> System.out.print(e.getName()+","));
        System.out.println(demo);
        System.out.println("==============");
    }

    private static int a;
    transient String b;
    Date date;
    public Comparable comparable;

    @Override
    public String toString() {
        return "DeSerializedDemo{" +
                "a='" + a + '\'' +
                "b='" + b + '\'' +
                ", date=" + date +
                ", comparable=" + comparable +
                '}';
    }

    public DeSerializedDemo(String b, Date date, Comparable comparable) {
        a=1;
        this.b = b;
        this.date = date;
        this.comparable = comparable;
    }
}
