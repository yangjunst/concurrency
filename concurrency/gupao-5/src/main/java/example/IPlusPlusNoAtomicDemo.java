package example;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/29 17:03
 * Program Goal:
 *********************************************/
public class IPlusPlusNoAtomicDemo {
    static int a=1;
    int x=1;
    public static void test() {
        a++;
        int b=a;
    }

    public  void demo() {
        a++;
        int b=a;
    }

    public  void show() {
        x++;
        int y=a;
    }
    public static void main(String[] args) {
        int i=0;
        i++;
        int b=i;
    }
}
