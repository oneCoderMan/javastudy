package com.java.study.algorithm.microsoft;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/1/5 23:24
 * @Description
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Lc03 {
    public static void main(String[] args) {
        String str = "abcabcbb";
        Lc03 lc03 = new Lc03();
        int re = lc03.lengthOfLongestSubstring(str);
        System.out.println(re);
    }

    /**
     * 思路：滑动窗口
     * 窗口里面保持无重复字符
     * 窗口右扩：一直没有重复
     * 窗口左收缩：一直到没有重复
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int currentMaxValue = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        Set<Character> container = new HashSet<>();
        while (rightIndex < chars.length) {
            // 窗口右递增
            for(int j = rightIndex; j < chars.length; j++) {
                if (container.contains(chars[j])) {
                    // 出现重复的，该结束了
                    rightIndex = j;
                    break;
                }
                container.add(chars[j]);
                // 获取大小要放到这里
                currentMaxValue = Math.max(currentMaxValue, j - leftIndex + 1);
            }
            // 窗口左收缩，收缩到当前rightIndex字符的下一个
            for (int j = leftIndex; j < rightIndex; j++) {
                if (chars[j] == chars[rightIndex]) {
                    leftIndex = j;
                    break;
                }
                container.remove(chars[j]);
            }
            if (leftIndex >= chars.length) {
                break;
            }
            container.remove(chars[leftIndex]);
            leftIndex++;
        }
        return currentMaxValue;
    }
}
