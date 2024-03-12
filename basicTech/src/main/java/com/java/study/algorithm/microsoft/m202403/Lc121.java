package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/11 23:28
 * @Description
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 */
public class Lc121 {
    public static void main(String[] args) {
        int[] prices = new int[] {7,6,4,3,1};
        Lc121 lc121 = new Lc121();
        int i = lc121.maxProfit(prices);
        System.out.println(i);
    }

    /**
     * 动态规划：每天右两个状态，持有股票的最大现金，不持有股票的最大现金
     * dp[i][0]：第i天持有股票的最大现金
     * dp[i][1]: 第i天不持有股票的最大现金
     *
     * dp[i][0] = max{第i-1天就持有股票的最大现金，第i天买入股票}
     * dp[i][1] = max{第i-1天就不持有股票的最大现金，第i天卖出入股票}
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        int[][] dp = new int[len][];
        for (int i = 0; i < len; i++) {
            dp[i] = new int[2];
        }
        dp[0][0] = 0 - prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], 0 - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
        }
        if (dp[len-1][1] <= 0) {
            return 0;
        }
        return dp[len-1][1];
    }
}
