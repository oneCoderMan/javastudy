package com.java.study.algorithm.microsoft.m202402;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author： yijun
 * @DATE: 2024/2/22 22:46
 * @Description
 * https://leetcode.cn/problems/subarray-sum-equals-k/description/
 * 和为K的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 .
 * 子数组是数组中元素的连续非空序列。
 */
public class Lc560 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        Lc560 lc560 = new Lc560();
        int i = lc560.subarraySum(nums, 3);
        System.out.println(i);
        // 前缀和解法
        int i1 = lc560.subarraySum2(nums, 3);
        System.out.println(i1);


    }

    /**
     * 暴力解法，枚举
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和+HashMap
     * @param nums
     * @param k
     * @return
     * pre[i]=  sum(num[0] + ... + sum[i])
     * [i,j]的和是pre[j] - pre[i-1] = k
     * pre[i-1] = pre[j] - K
     * 当遍历到j的时候查看pre[j] - K有多少个
     * HashMap ： key是一个数值，代表一个前序和的值，如pre[i], value是这个前序和的个数,
     */
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        Map<Integer, Integer> preSumCountMaps = new HashMap<>();
        // 默认pre[0-1] = 1，这个需要注意
        preSumCountMaps.put(0, 1);
        // 计算前序和
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer needOtherNumSum = sum - k;
            if (preSumCountMaps.containsKey(needOtherNumSum)) {
                count += preSumCountMaps.get(needOtherNumSum);
            }
            // 把这个和加入进去
            if (preSumCountMaps.containsKey(sum)) {
                preSumCountMaps.put(sum, preSumCountMaps.get(sum) + 1);
            } else {
                preSumCountMaps.put(sum, 1);
            }
        }
        return count;

    }
}
