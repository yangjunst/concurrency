package demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/19 16:09
 * Program Goal:
 *********************************************/
public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup group=new ThreadGroup("group");
        ThreadGroup g=new ThreadGroup(group,"1111");
        System.out.println(g.getParent().getParent().getParent().getParent());


    }
}
