package com.java.study.algorithm.microsoft.m202402;

/**
 * @Author： yijun
 * @DATE: 2024/2/19 22:13
 * @Description
 * https://leetcode.cn/problems/maximum-subarray/description/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 */
public class Lc53 {
    public static void main(String[] args) {
        Lc53 lc53 = new Lc53();
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(lc53.maxSubArray(nums));;
    }

    /**
     * dp[i] 表示i结尾的最大子数组和
     * dp[i] = max {dp[i-1] + a[i], a[i]}
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int a = 0;
        int b;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            b = Math.max(a + nums[i], nums[i]);
            max = Math.max(max, b);
            a = b;
        }
        return max;
    }
}
