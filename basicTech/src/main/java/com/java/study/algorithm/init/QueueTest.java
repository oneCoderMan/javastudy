package com.java.study.algorithm.init;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author： yijun
 * @DATE: 2023/12/10 10:56
 * @Description
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        // 进入队列
        queue.offer(1);
        queue.offer(4);
        // 获取队列头的元素，不移除
        Integer peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue);
        // 获取队列头的元素，移除
        Integer poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue);

    }
}
