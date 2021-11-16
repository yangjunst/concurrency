package demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 16:12
 * Program Goal:
 *********************************************/
public class NullableDemo {
    public static void main(String[] args) {
        test(null);
    }
    public static void test(Object object) {
        System.out.println("Object...."+object);
    }

    public static void test(String str) {
        System.out.println("String...."+str);
    }
    public static void test1() {
        String s1="a1";
        String s2="a"+1;
        System.out.println(s1==s2);
    }
}
