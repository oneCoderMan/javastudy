package com.java.study.multiThread;

import com.java.study.model.Person;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.java.study.Mock.MockPeople.mockPeople;

/**
 * CompletableFuture，它针对Future做了改进，可以传入回调对象，
 * 当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
 * 需要保证主线程存活
 */
@Log
public class CompletableFutureTest {
    public static void main(String[] args) {
        log.info("test");
        // singleTaskExecution();
//        serialExecution();
        concurrentExecution();
    }

    /**
     * 单个任务执行
     */
    public static void singleTaskExecution() {
//        CompletableFuture<Person> cfQuery = CompletableFuture.supplyAsync(() -> {
//            return doQueryPerson(1);
//        });
        // 可以指定使用哪一个线程池
        CompletableFuture<Person> cfQuery = CompletableFuture.supplyAsync(() -> {
            return doQueryPerson(1);}, ThreadPool.TEST_EXECUTOR);
        // 如果执行成功:
        cfQuery.thenAccept((result) -> {
            System.out.println("people: " + result);
        });
        // 如果执行异常:
        cfQuery.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
    }

    // 多个CompletableFuture串行执行
    public static void serialExecution() {
        // 第一个任务
        CompletableFuture<Person> cfQuery = CompletableFuture.supplyAsync(() -> {
            return doQueryPerson(1);
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<String> cfSchoolQuery = cfQuery.thenApplyAsync((person) -> {
            return doQuerySchool(person);
        });
        // 如果执行成功:
        cfSchoolQuery.thenAccept((result) -> {
            System.out.println("people school: " +  result);
        });
        // 如果执行异常:
        cfQuery.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 并行执行多个任务，等待结果返回
     */
    public static void concurrentExecution() {
        List<Person> re = new ArrayList<>();
        // 第一个任务
        CompletableFuture<Person> cfQuery1 = CompletableFuture.supplyAsync(() -> {
            return doQueryPerson(1);
        }, ThreadPool.TEST_EXECUTOR);
        cfQuery1.thenAccept((result) -> {
            re.add(result);
        });
        // 第一个任务
        CompletableFuture<Person> cfQuery2 = CompletableFuture.supplyAsync(() -> {
            return doQueryPerson(2);
        }, ThreadPool.TEST_EXECUTOR);
        cfQuery2.thenAccept((result) -> {
            re.add(result);
        });
        // 必须等待所有的future全部完成才可以
        // 加上join会在这里阻塞，等待结果返回
        CompletableFuture.allOf(cfQuery1, cfQuery1).join();
        System.out.println(re);
    }



    private static Person doQueryPerson(int id) {
        System.out.println("start to query people " + id);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Person> personList = mockPeople(id, 1);
        System.out.println("end to query people " + id);
        return personList.get(0);
    }
    private static String doQuerySchool(Person person) {
        System.out.println("start to query people's school" + person.getId());
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end to query people school " );
        return "HUST";
    }
}
