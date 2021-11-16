package demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 16:07
 * Program Goal:
 * {@link ThreadLocal#remove()}
 * 在里面没有值得时候，可以进行remove操作
 *********************************************/
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> local=new ThreadLocal<>();
        local.remove();
        local.remove();
        System.out.println(local.get());
    }
}
