package gp05.example.count;

import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public abstract class BaseHealthChecker implements Runnable{

    private String serviceName; //服务名称

    private boolean serviceUp;
    private CountDownLatch countDownLatch;

    public BaseHealthChecker(String serviceName, CountDownLatch countDownLatch) {
        this.serviceName = serviceName;
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp=true;
        }catch (Exception e){
            serviceUp=false;
        }finally {
            countDownLatch.countDown();
        }
    }

    /**
     * 检查服务的健康情况
     */
    public abstract void verifyService() throws Exception;

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }
}
