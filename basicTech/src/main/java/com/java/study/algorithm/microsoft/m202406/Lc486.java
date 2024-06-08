package com.java.study.algorithm.microsoft.m202406;

import java.util.Arrays;

/**
 * @Author： yijun
 * @DATE: 2024/6/4 21:57
 * @Description
 * https://leetcode.cn/problems/predict-the-winner/description/
 *
 */
public class Lc486 {
    public static void main(String[] args) {
        Lc486 lc486 = new Lc486();
        int[] nums = new int[]{1,5,233,7};
        boolean re = lc486.predictTheWinner(nums);
        System.out.println(re);
    }
    public boolean predictTheWinner(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int n = nums.length;
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[n];
            Arrays.fill(dp[i], -1);
        }
        // 获取先手的最高得分
        int maxScore = getOptimalScore(nums, 0, n - 1, dp);
        return maxScore >= sum(nums, 0, n - 1) - maxScore;
    }

    /**
     * 动态规划
     * dp[i][j] 当前局面 nums[i] 到 nums[j] 能够获取到的最大分数
     * dp[i][j]  = sum(i, j) - min(dp(L+1, R), dp(L, R-1))。
     * sum(L, R) 表示 nums[L:R] 的累加和，min() 表示让对手进入最高分最低的那个局面
     *
     * @param nums
     * @param left
     * @param right
     * @param dp
     * @return
     */
    private int getOptimalScore(int[] nums, int left, int right, int[][] dp) {
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        if (left == right) {
            dp[left][right] = nums[left];
            return dp[left][right];
        }
        // 此时的最优得分是对手选了一个最小的分数
        // 对手有两个局面 [left+1, right] [left, right-1]
        int otherMinScore = Math.min(getOptimalScore(nums, left + 1, right, dp),
                getOptimalScore(nums, left, right - 1, dp));
        // 每一轮回合总分固定
        return sum(nums, left, right) - otherMinScore;
    }

    private int sum(int[] nums, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            ans += nums[i];
        }
        return ans;
    }

}
