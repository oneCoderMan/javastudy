package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/22 22:51
 * @Description
 * https://leetcode.cn/problems/decode-ways/description/
 * A-Z -> 1-26
 * 所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）
 * 11106
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 */
public class Lc91 {
    public static void main(String[] args) {
        Lc91 lc91 = new Lc91();
        String str = "12";
        int re = lc91.numDecodings(str);
        System.out.println("==");
        System.out.println(re);
    }

    /**
     * 动态规划
     * dp[i]： i结尾的字符串里面可以分隔的有效映射数
     * dp[i] = dp[i-1] (if a[i]可以有效分隔) + dp[i-2] (if a[i-1][i]可以组成一个整体)
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        // 只要0开头，就分割不出来
        if (s.startsWith("0")) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int preTwo = 1;
        int preOne = calueTwoCountByChar(s.charAt(0), s.charAt(1));
        for (int i = 2; i < s.length(); i++) {
            int currentCount = 0;
            // a[i] 单独一组
            if (s.charAt(i) != '0') {
                currentCount = preOne;
            }
            // a[i-1] [i]是否可以单独一组
            int curNum = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (s.charAt(i - 1) != '0'  && curNum <= 26) {
                currentCount = currentCount + preTwo;
            }
            preTwo = preOne;
            preOne = currentCount;
        }
        return preOne;
    }

    private int calueTwoCountByChar(char a, char b) {
        int curNum = (a - '0') * 10 + (b - '0');
        if (b != '0') {
            if (curNum > 26) {
                return 1;
            }
            return 2;
        }
        if (curNum > 26) {
            return 0;
        }
        return 1;
    }


}
