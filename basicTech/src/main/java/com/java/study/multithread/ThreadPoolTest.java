package com.java.study.multithread;

import com.github.phantomthief.concurrent.MoreFutures;
import com.github.phantomthief.concurrent.TryWaitFutureUncheckedException;
import com.java.study.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.java.study.Mock.MockPeople.mockPeople;
import static com.java.study.multithread.ThreadPool.TEST_EXECUTOR;


/**
 * 等待线程结果并且返回
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        List<Person> personList = queryPerson();
        System.out.println("end Main2");
        System.out.println("all1" + personList);
        System.out.println("========");
        List<Person> personList2 = queryPerson2();
        System.out.println("end Main2");
        System.out.println("all2" + personList2);
    }

    /**
     * 提交作业方式1
     * 自己控制等待
     * @return
     */
    public static List<Person> queryPerson() {
        List<Person> re = new ArrayList<>();
        List<Future<List<Person>>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = i;
            Future<List<Person>> future =
                    TEST_EXECUTOR.submit(() -> doQuery(index));
            futures.add(future);
        }

        System.out.println("all the task has submitted....");
        //这句话很重要
        try {
            MoreFutures.tryWait(futures, 20, TimeUnit.MINUTES);
        } catch (TryWaitFutureUncheckedException e) {
            //ignore
        }
        futures.forEach(future -> {
            try {
                if (future.isDone() && !future.isCancelled()) {
                    List<Person> curList = future.get();
                    if (curList != null) {
                        re.addAll(curList);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("all the people has got");
        return re;
    }

    /**
     * 交给线程池控制
     * @return
     */
    public static List<Person> queryPerson2() {
        List<Person> re = new ArrayList<>();
        List<Callable<List<Person>>> queryTask = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = i;
            queryTask.add(() -> doQuery(index));
        }
        try {
            List<Future<List<Person>>> futuresResult = TEST_EXECUTOR.invokeAll(queryTask);
            for (int i = 0; i < futuresResult.size(); i++) {
                List<Person> people = futuresResult.get(i).get(1000, TimeUnit.MILLISECONDS);
                re.addAll(people);
            }
        } catch (Exception e) {
            // deal
        }
        return re;
    }


    private static List<Person> doQuery(int i) {
        System.out.println("start to query people " + i);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Person> personList = mockPeople(i, 2);
        System.out.println(personList);

        System.out.println("end to query people " + i);
        return personList;
    }
}
