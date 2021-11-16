package example.object;

import java.util.concurrent.RecursiveAction;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/11/12 16:44
 * Program Goal:
 *********************************************/
public abstract class AbstractProcessor extends RecursiveAction implements IProcessor {
    @Override
    protected void compute() {
        show();
    }
}
