package com.java.study.algorithm.microsoft.m202406;

/**
 * @Author： yijun
 * @DATE: 2024/6/2 16:02
 * @Description
 * https://leetcode.cn/problems/unique-paths/description/
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
public class Lc62 {
    public static void main(String[] args) {
        Lc62 lc62 = new Lc62();
        int re = lc62.uniquePaths(3, 7);
        System.out.println(re);
    }

    /**
     * dp[i,j] 有i, j这个位置有的路径数
     * dp[i,j] = dp[i, j-1] + dp[i-1, j]
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 0;
        }
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
    }
}
