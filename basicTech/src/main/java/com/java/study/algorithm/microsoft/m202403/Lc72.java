package com.java.study.algorithm.microsoft.m202403;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/3/17 16:57
 * @Description
 * https://leetcode.cn/problems/edit-distance/description/
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数.
 * 对单词的三个操作：插入，删除，替换（一个字符）
 */
public class Lc72 {
    public static void main(String[] args) {
        Lc72 lc72 = new Lc72();
//        String word1 = "horse";
//        String word2 = "ros";
        String word1 = "";
        String word2 = "execution";
        int re = lc72.minDistance(word1, word2);
        System.out.println(re);
    }

    /**
     * 动态规划
     * dp[i][j]: word1中i结尾的字符串到word2中j结尾的字串串最短编辑距离
     * word1[i] == word2[j] dp[i][j] = dp[i-1][j-1] 不用编辑当前的，往前看一个
     * word1[i] ！= word2[j] dp[i][j] 下面取最小一个
     * 删除word1最后一个  dp[i-1][j] + 1
     * 删除word2最后一个  dp[i][j - 1] + 1
     * 替换word1和word2最后一个 dp[i-1][j-1] + 1
     *
     * @return
     */
    public int minDistance(String word1, String word2) {
        // 处理边界情况
        if (word1 == null  && word2 == null) {
            return 0;
        }
        if ("" == word1 && "" == word2) {
            return 0;
        }
        if (word1 == null || "".equals(word1)) {
            return word2.length();
        }
        if (word2 == null || "".equals(word2)) {
            return word1.length();
        }
        if (word1.equals(word2)) {
            return 0;
        }
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1][];
        for (int i = 0; i < n1; i++) {
            dp[i] = new int[n2];
        }
        // 初始化i==0
        Set<Character> hasIn = new HashSet<>();
        Character cur = word1.charAt(0);
        for (int j = 0; j < n2; j++) {
            hasIn.add(word2.charAt(j));
            if (hasIn.contains(cur)) {
                // 当前长度减1
                dp[0][j] = j;
            } else {
                //当前长度
                dp[0][j] = j + 1;
            }
        }
        // 初始化j==0
        hasIn = new HashSet<>();
        cur = word2.charAt(0);
        for (int i = 0; i < n1; i++) {
            hasIn.add(word1.charAt(i));
            if (hasIn.contains(cur)) {
                dp[i][0] = i;
            } else {
                dp[i][0] = i + 1;
            }
        }
        // 开始遍历其它的行列
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // word1插入一个
                    int a = dp[i][j-1] + 1;
                    // word2插入一个
                    int b = dp[i-1][j] + 1;
                    // 最后一个替换
                    int c = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(Math.min(a, b), c);
                }
            }
        }
        return dp[n1-1][n2-1];
    }
}
