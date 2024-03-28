package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.Pair;
import com.java.study.utils.JsonUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author： yijun
 * @DATE: 2024/3/24 21:26
 * @Description
 * https://leetcode.cn/problems/sliding-window-maximum/description/
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 */
public class Lc239 {
    public static void main(String[] args) {
        Lc239 lc239 = new Lc239();
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] re = lc239.maxSlidingWindow(nums, k);
        System.out.println(JsonUtil.toJson(re));
    }

    /**
     * 单调队列
     * 队首是当前窗口最大的元素，有可能成为最大元素的加入到队尾
     * 窗口向右滑动一个：
     * 窗口左侧元素失效，将队列中所有失效的元素弹出
     * 窗口右侧元素加入进来，把队尾比它大的元素都弹出
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 0) {
            return null;
        }
        int n = nums.length <= k ? 1 : nums.length - k + 1;
        int[] maxResultInK = new int[n];
        Deque<Pair<Integer, Integer>> increaseQueue = new LinkedList<>();
        increaseQueue.add(new Pair<>(0, nums[0]));
        // 初始化
        for (int i = 0; i < k && i < nums.length; i++) {
            addElement(increaseQueue, nums[i], i);
        }
        maxResultInK[0] = increaseQueue.peek().getSecond();
        if (nums.length <= k) {
            return maxResultInK;
        }
        for (int i = 1; i < n; i++) {
            // 元素 i-1出队列
            popElement(increaseQueue, i - 1, nums[i-1]);
            // 元素 i + k - 1出队列
            addElement(increaseQueue, nums[i + k -1], i + k - 1);
            maxResultInK[i] = increaseQueue.peek().getSecond();
        }
        return maxResultInK;
    }

    /**
     * 将队列里面过期的元素都删除
     * @param increaseQueue
     * @param i
     * @param num
     */
    private void popElement(Deque<Pair<Integer, Integer>> increaseQueue, int i, int num) {
        while (!increaseQueue.isEmpty()) {
            Pair<Integer, Integer> peek = increaseQueue.peek();
            if (peek.getFirst() <= i) {
                increaseQueue.pop();
            } else {
                break;
            }
        }
    }

    /**
     * 前面比它小的元素都要出队列， 加到队尾
     * @param increaseQueue first-index second-num[i]
     * @param num
     */
    private void addElement(Deque<Pair<Integer, Integer>> increaseQueue, int num, int index) {
        if (increaseQueue.isEmpty()) {
            increaseQueue.add(new Pair<>(index, num));
        }
        while (!increaseQueue.isEmpty()) {
            if (increaseQueue.getLast().getSecond() <= num) {
                increaseQueue.pollLast();
            } else {
                break;
            }
        }
        increaseQueue.add(new Pair<>(index, num));
    }
}
