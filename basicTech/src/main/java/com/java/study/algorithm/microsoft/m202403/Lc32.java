package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/12 22:41
 * @Description
 * https://leetcode.cn/problems/longest-valid-parentheses/description/
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Lc32 {
    public static void main(String[] args) {
        Lc32 lc32 = new Lc32();
        String s = ")()())";
        int re = lc32.longestValidParentheses(s);
        System.out.println(re);
    }

    /**
     * dp[i] i结尾的最长有效括号长度
     * s[i] = '('  => dp[i] = 0
     * s[i] = ')' && s[i-1] = '(  => dp[i] = dp[i-2] + 2
     * s[i] = ')' && s[i-1] = ')' && s[i-dp[i-1]-1] == '( => dp[i] = dp[i-dp[i-1]-2] + dp[i-1] + 2
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        int ans = 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[len];
        dp[0] = 0;
        if (chars[0] == '(' && chars[1] == ')' ) {
            dp[1] = 2;
            ans = 2;
        } else {
            dp[1] = 0;
        }
        for (int i = 2; i < len; i++) {
            dp[i] = 0;
            // 当前的无法形成完成括号
            if (chars[i] == '(') {
                dp[i] = 0;
                continue;
            }
            // chars[i] == ')' chars[i - 1] == '(' 情况
            if (chars[i - 1] == '(') {
                dp[i] = dp[i-2] + 2;
                ans = Math.max(ans, dp[i]);
                continue;
            }
            // chars[i] == ')' chars[i - 1] == ')' 情况
            if (dp[i - 1] == 0) {
                // 说明此时无法往前面推了
                continue;
            }
            // 看看可以找到一个(和最后一个括号匹配，总共需要判断三个部分的拼接
            int preIndex = i - dp[i-1] - 1;
            if (preIndex < 0) {
                continue;
            }
            if (chars[preIndex] == '(') {
                if (preIndex > 1) {
                    dp[i] = dp[preIndex - 1] + dp[i-1] + 2;
                } else {
                    dp[i] = dp[i-1] + 2;
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

}
