package demo;

import java.util.concurrent.*;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/25 17:24
 * Program Goal:
 *********************************************/
public class FutureTaskDemo1 {
    private static final FutureTask<String> futureTask= new FutureTask<String>(() -> {
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            System.out.println("e--->"+e.getClass());
        }
        return "Done";
    });
    public static void test() throws InterruptedException {
        new Thread(futureTask).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main do something...");
        String s = null;
        try {
            s = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result--->" + s);

    }

    /**
     * {@link FutureTask#cancel(boolean)}//cancel(true) or cancel(false)
     * @throws InterruptedException
     */
    public static void testCancel() throws InterruptedException {
        new Thread(futureTask).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main do something...");
        String s = null;
        try {
            futureTask.cancel(false);
            s = futureTask.get();
        } catch (Exception e) {
            System.out.println("error--->"+e.getClass());
        }
        System.out.println("result--->" + s);
    }

    public static void testExecutor() throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(2);
       service.execute(futureTask);
        System.out.println("result---->"+futureTask.get());

    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
            testExecutor();
    }
}
