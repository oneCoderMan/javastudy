package com.java.study.algorithm.microsoft;

import java.util.PriorityQueue;

/**
 * @Author： yijun
 * @DATE: 2023/12/18 23:08
 * @Description 数组中的第K个最大元素
 * 你需要找的是数组排序后的第 k 个最大的元素，时间复杂度为O(n)
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 */
public class Lc215 {
    public static void main(String[] args) {
        Lc215 lc215 = new Lc215();
        int[] nums = new int[]{3,2,1,5,6,4};
        int re = lc215.findKthLargest(nums, 2);
        System.out.println(re);
    }

    /**
     * 使用小顶堆解决问题
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        // 需要指定容量
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            // 堆还没有满，直接加入
            if (pq.size() < k ) {
                pq.offer(nums[i]);
                continue;
            }
            int peekElement = pq.peek();
            // 更大的数要加入进来，比堆顶元素大的就要加入进来，比堆顶小的元素不管
            if (peekElement < nums[i]) {
                // 堆顶元素出来
                pq.poll();
                // 加入新的元素
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }

}
