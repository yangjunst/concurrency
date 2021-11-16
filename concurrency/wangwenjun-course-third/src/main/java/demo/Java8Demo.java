package demo;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/7 19:49
 * Program Goal:
 * .map((f->(Closeable)f::show))
 * 此处转换使得实例方法得以应用到lambda表达式中
 *********************************************/
class Example{
     String msg;

    public Example(String msg) {
        this.msg = msg;
    }

    public  void show(){
        System.out.println("Example#show...."+msg);
    }
    public void display(){
        System.out.println("Example#display....");
    }
    public void func(){
        System.out.println("Example#func....");
    }
}
public class Java8Demo {
    private static final List<Example> examples=new ArrayList<>();

    public static void main(String[] args) {
        examples.add(new Example("yangjun"));
        examples.add(new Example("yangjunst"));
        examples.add(new Example("maomao"));
        examples.stream().map((f->(Closeable)f::show)).forEach(e-> {
            try {
                e.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
