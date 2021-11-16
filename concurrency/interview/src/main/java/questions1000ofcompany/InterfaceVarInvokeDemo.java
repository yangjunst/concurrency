package questions1000ofcompany;


/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/26 11:02
 * Program Goal:
 * 父接口在调用方法时,idea工具会智能的强转为指定的子接口或实现类
 *********************************************/
interface Execute{
    void execute();
}
interface ExecuteService extends Execute{
    void submit();
    void call();
}
public class InterfaceVarInvokeDemo implements ExecuteService {
    public static void main(String[] args) {
        Execute execute=new InterfaceVarInvokeDemo();
        execute.execute();
        ((InterfaceVarInvokeDemo) execute).call();
    }


    @Override
    public void execute() {

    }

    @Override
    public void submit() {

    }

    @Override
    public void call() {

    }
}
