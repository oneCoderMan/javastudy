package com.java.study.algorithm.microsoft.m202406;

/**
 * @Author： yijun
 * @DATE: 2024/6/8 09:59
 * @Description
 * https://leetcode.cn/problems/trapping-rain-water/description/
 */
public class Lc42 {
    public static void main(String[] args) {
        Lc42 lc42 = new Lc42();
        int[] nums = new int[] { 0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = lc42.trap(nums);
        System.out.println(trap);
    }

    /**
     * 按列计算面积
     *  计算出每一列向左向右可以有的最大高度，
     *  减去当前列的高度就是能装水的高度
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n = height.length;
        int[] maxRight = new int[n];
        // 计算每个元素右边的最大值
        calcuMaxRight(height, maxRight, n);
        // 计算每个元素左边的最大值
        int[] maxLeft = new int[n];
        calcuMaxLeft(height, maxLeft, n);
        // 计算雨水的最大面积
        int area = 0;
        for (int i = 0; i < n; i++) {
            int curHeight = Math.min(maxRight[i], maxLeft[i]) - height[i];
            if (curHeight > 0) {
                area += curHeight;
            }
        }
        return area;
    }

    private void calcuMaxLeft(int[] nums, int[] dp, int n) {
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1]);
        }
    }

    private void calcuMaxRight(int[] nums, int[] dp, int n) {
        dp[n-1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], nums[i+1]);
        }
    }

}
