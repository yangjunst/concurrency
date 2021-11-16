package example;

import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * JavaDoc只对public与protected访问权限的资源进行编写文档。
 * private与包访问权限下单资源不再编档
 */
public class JavaDocDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(Modifier.toString(JavaDocDemo.class.getDeclaredConstructor().getModifiers()));
    }
    private int a;
    String b;
    protected Date date;
    public Comparable comparable;

    private int getA() {
        return a;
    }

    private void setA(int a) {
        this.a = a;
    }

     String getB() {
        return b;
    }

     void setB(String b) {
        this.b = b;
    }

    protected Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    public Comparable getComparable() {
        return comparable;
    }

    public void setComparable(Comparable comparable) {
        this.comparable = comparable;
    }

    public static String toString1() {
        return "";
    }
}
