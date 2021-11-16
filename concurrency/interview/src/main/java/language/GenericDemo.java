package language;


/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/8 12:30
 * Program Goal:
 * T extends Comparable  if init T value,default Comparable
 *********************************************/
interface Tool<T extends Comparable> {
    T get();
}

public class GenericDemo implements Tool{
    @Override
    public Comparable get() {
        return null;
    }
}
