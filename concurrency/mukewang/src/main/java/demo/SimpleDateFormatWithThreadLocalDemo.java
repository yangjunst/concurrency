package demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 16:48
 * Program Goal:
 *********************************************/
public class SimpleDateFormatWithThreadLocalDemo {
    private static class DateFormatTool{
        private static final ThreadLocal<SimpleDateFormat> local=new ThreadLocal();
        public static SimpleDateFormat get(String pattern){
            SimpleDateFormat format=local.get();
            if(format==null){
                format=new SimpleDateFormat(pattern);
                local.set(format);
            }
            return format;
        }
    }
    private static final ThreadLocal<SimpleDateFormat> local=ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));
    private static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            service.execute(()->{
                try {
                    DateFormatTool.get("yyyy-MM-dd").parse("2021-10-24");
                } catch (ParseException e) {
                    System.out.println(Thread.currentThread().getName()+"---->"+e.getMessage());
                }
            });
        }
        service.shutdown();
    }
}
