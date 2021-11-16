package example.object;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/12 16:46
 * Program Goal:
 *********************************************/
public class Test {
    public static void main(String[] args) {
        IProcessor processor=new DefaultProcess();
        ((DefaultProcess) processor).compute();
    }
}
