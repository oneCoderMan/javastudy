package com.java.study.algorithm.microsoft.m202402;

/**
 * @Author： yijun
 * @DATE: 2024/2/21 22:20
 * @Description
 * https://leetcode.cn/problems/reverse-words-in-a-string/solutions/194450/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 */
public class Lc151 {
    public static void main(String[] args) {
        Lc151 lc151 = new Lc151();
        String theSkyIsBlue = lc151.reverseWords("t");
        System.out.println(theSkyIsBlue);
    }

    /**
     * 使用双指针法
     * 从后往前遍历
     * 【start，end】组成的已完成的一个单词
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder re = new StringBuilder();
        if (s == null || "".equals(s)) {
            return null;
        }
        // 指向当前
        int end = s.length() - 1;
        int start = end;
        while (end >= 0) {
            // end 指向最后一个单词的尾部
            while (end >= 0 && s.charAt(end) == ' ') {
                end--;
            }
            start = end;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            if (start == 0 && s.charAt(0) != ' ') {
                start--;
            }
            // 截取这个位置
            String substring = s.substring(start + 1, end + 1);
            re.append(substring).append(" ");
            // 开始下一个单词
            end = start;
        }
        return re.toString().trim();
    }
}
