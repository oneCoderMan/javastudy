package com.java.study.javastudy.multiThread;

import com.github.phantomthief.concurrent.MoreFutures;
import com.github.phantomthief.concurrent.TryWaitFutureUncheckedException;
import com.java.study.javastudy.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.java.study.javastudy.Mock.MockPeople.mockPeople;
import static com.java.study.javastudy.multiThread.ThreadPool.TEST_EXECUTOR;

/**
 * 等待线程结果并且返回
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        List<Person> personList = queryPerson();
        System.out.println("end Main");
        System.out.println("all" + personList);
    }

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
