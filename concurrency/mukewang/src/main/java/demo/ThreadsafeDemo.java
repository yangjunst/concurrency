package demo;

/********************************************
 * 开发人员: 雪域青竹
 * 入职时间: 2016/05/16
 * 开发时间: 2021/10/24 16:23
 * Program Goal:
 * {@link StringBuffer} and {@link StringBuilder}
 * 当其是成员变量的时候，在多线程环境下，需要考虑线程安全性问题，此时使用StringBuffer好些
 * 当其实局部变量的时候，尽管在多线程环境下，不会出现线程安全性问题，使用StringBuilder好些
 * *********************************************/
public class ThreadsafeDemo {
    private static class Tool {
        private int count;

        public /*synchronized 此处有无，对局部调用有性能影响否*/ void add() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void test() {
        Tool tool = new Tool();
        for (int i = 0; i < 10; i++) {
            tool.add();
        }
        System.out.println(Thread.currentThread().getName() + "---->" + tool.getCount());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                test();
            }).start();
        }
    }
}
