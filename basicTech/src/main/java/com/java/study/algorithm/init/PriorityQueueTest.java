package com.java.study.algorithm.init;

import java.util.PriorityQueue;

/**
 * @Author： yijun
 * @DATE: 2024/4/5 21:22
 * @Description
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
//        testMinHeap();
        testMaxHeap();

    }


    /**
     * 小顶堆
     */
    public static void testMinHeap() {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        minQueue.add(3);
        minQueue.add(4);
        minQueue.add(2);
        minQueue.add(1);
        minQueue.add(7);
        System.out.println(minQueue.peek());
        Integer poll = minQueue.poll();
        System.out.println(minQueue.peek());
    }

    /**
     * 大顶堆
     */
    public static void testMaxHeap() {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> (b-a));
        maxQueue.add(3);
        maxQueue.add(4);
        maxQueue.add(2);
        maxQueue.add(1);
        maxQueue.add(7);
        System.out.println(maxQueue.peek());
        Integer poll = maxQueue.poll();
        System.out.println(maxQueue.peek());
    }
}
