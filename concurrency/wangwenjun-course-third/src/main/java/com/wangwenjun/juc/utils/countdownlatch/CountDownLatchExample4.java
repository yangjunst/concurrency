package com.wangwenjun.juc.utils.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/18
 * QQ交流群:601980517，463962286
 ***************************************/
public class CountDownLatchExample4 {

    private static Random random = new Random(System.currentTimeMillis());


    static class TaskBatch {

        private CountDownLatch countDownLatch;


        private TaskGroup taskGroup;

        private Event event;


        public TaskBatch(TaskGroup taskGroup, int size, Event event) {
            this.taskGroup = taskGroup;
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
                taskGroup.done(event);
            }

        }
    }

    static class TaskGroup {

        private CountDownLatch countDownLatch;

        public TaskGroup(int size) {
            this.countDownLatch = new CountDownLatch(size);
        }

        public void done(Event event) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("=====All of table done in event:" + event.id);
            }

        }
    }

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount;
        long targetCount;
        String sourceColumnSchema = "<table name='a'><column name='col1' type='varchar2'/></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    static class TrustSourceRecordCount implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetCount = table.sourceRecordCount;
            //System.out.println("The table " + table.tableName + " target record count capture done and update the data");
            taskBatch.done(table);
        }
    }


    static class TrustSourceColumns implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
            //System.out.println("The table " + table.tableName + " target columns capture done and update the data");
            taskBatch.done(table);
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000));
        }

        return list;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size());

            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(taskGroup, 2, event);
                  executorService.submit(new TrustSourceColumns(table, taskBatch));
                executorService.submit( new TrustSourceRecordCount(table, taskBatch));
            }
        }

    }


}
