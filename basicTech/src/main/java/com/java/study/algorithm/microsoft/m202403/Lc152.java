package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/24 09:35
 * @Description
 * https://leetcode.cn/problems/maximum-product-subarray/
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组
 * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 */
public class Lc152 {
    public static void main(String[] args) {
        Lc152 lc152 = new Lc152();
        int[] nums = new int[] {2,3,-2,4};
        int re = lc152.maxProduct(nums);
        System.out.println(re);
    }

    /**
     * 得考虑情况：-2,3,-4 最大是24，可能是有负数
     * 需要维护乘积最大和最小两个状态
     * 这是和最大连续子数组和的区别
     * dp[0][i] i结尾的乘积最大的值
     * dp[1][i] i结尾的乘积最小的值
     *
     * dp[0][i] = max{dp[0][i-1] * nums[i], nums[i], dp[1][i-1] * nums[i]}
     * dp[1][i] = min{dp[0][i-1] * nums[i], nums[i], dp[1][i-1] * nums[i]}
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int preMax = nums[0];
        int preMin = nums[0];
        int ans = preMax;
        int curMax;
        int curMin;
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(preMax * nums[i], nums[i]);
            curMax = Math.max(curMax, preMin * nums[i]);
            ans = Math.max(ans, curMax);
            curMin = Math.min(preMax * nums[i], nums[i]);
            curMin = Math.min(curMin, preMin * nums[i]);
            preMin = curMin;
            preMax = curMax;
        }
        return ans;
    }
}
