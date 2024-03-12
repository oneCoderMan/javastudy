package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/7 08:14
 * @Description
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 给你一个字符串 s，找到 s 中最长的回文子串
 *
 *
 */
public class Lc05 {
    public static void main(String[] args) {
        String s = "a";
        Lc05 lc05 = new Lc05();
        String result = lc05.longestPalindrome(s);
        System.out.println(result);
    }

    /**
     * 动态规划
     * dp[i][j]：表示[i,j]这段字串是否是回文串
     * dp[i][j] 由子问题得来 dp[i+1][j-1]：约束（i <= j）
     * dp[i+1][j-1] = false => dp[i][j] = false
     * dp[i+1][j-1] = true && a[i] == a[j] => dp[i][j] = true
     *
     * 可知dp[i][j]由左下相邻的一格推出来，因此需要从左向右，从下往上遍历，且i <=j
     *
     * 初始化：dp[i][j]=true, i>=j
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        int n = s.length();
        if (n == 1) {
            return s;
        }
        boolean[][] dp = new boolean[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new boolean[n];
            for (int j = 0; j <= i; j++) {
                // 自己和自己是一个回文串
                dp[i][j] = true;
            }
        }
        String ans = s.substring(0, 1);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (dp[i + 1][j - 1] == false) {
                    dp[i][j] = false;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        if (j - i + 1 > ans.length()) {
                            ans = s.substring(i, j + 1);
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return ans;
    }
}
