package com.java.study.algorithm.microsoft.m202403;

import static com.java.study.algorithm.init.ArrayTool.strToArray;

/**
 * @Author： yijun
 * @DATE: 2024/3/28 22:15
 * @Description
 * https://leetcode.cn/problems/minimum-path-sum/description/
 * 给定一个包含非负整数的 m x n 网格 grid ，
 * 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 每次只能向下或者向右移动一步。
 */
public class Lc64 {
    public static void main(String[] args) {
        Lc64 lc64 = new Lc64();
        String inputString = "[[1,2,3],[4,5,6]]";
        int[][] grid = strToArray(inputString);
        int re = lc64.minPathSum(grid);
        System.out.println(re);
    }

    /**
     * 动态规划：dp[i][j] 到达i，j位置的最短路径和
     * dp[i][j] = min{dp[i][j-1], dp[i-1][j]} + num[i][j]
     * 优化，矩阵压缩，变成滚动数组
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][];
        for (int i = 0; i < m; i++) {
            dp[i] = new int[n];
        }
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j-1][0] + grid[j][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
