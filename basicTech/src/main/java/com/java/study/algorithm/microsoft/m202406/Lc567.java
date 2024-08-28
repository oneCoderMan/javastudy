package com.java.study.algorithm.microsoft.m202406;

import java.util.Arrays;

/**
 * @Author： yijun
 * @DATE: 2024/6/28 20:59
 * @Description https://leetcode.cn/problems/permutation-in-string/description/
 *
 */
public class Lc567 {
    public static void main(String[] args) {
        Lc567 lc567 = new Lc567();
        String s1 = "ab";
        String s2 = "eidbaooo";
        boolean b = lc567.checkInclusion(s1, s2);
        System.out.println(b);


    }

    /**
     * 固定大小滑动窗口 + 字典表
     *  给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
     *  如果是，返回 true ；否则，返回 false 。
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // 前提 s2.length > s1.length, 滑窗大小为s1.length
        if (isEmpty(s1)) {
            return true;
        }
        if (isEmpty(s2)) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        // 字典数组
        int[] dict1 = new int[26];
        int[] dict2 = new int[26];
        // 初始化
        for (int i = 0; i < s1.length(); i++) {
            dict1[s1.charAt(i) - 'a'] = dict1[s1.charAt(i) - 'a'] + 1;
            dict2[s2.charAt(i) - 'a'] = dict2[s2.charAt(i) - 'a'] + 1;
        }
        if (Arrays.equals(dict1, dict2)) {
            return true;
        }
        for (int j = s1.length(); j < s2.length(); j++) {
            // 需要新增的字符
            char newRight = s2.charAt(j);
            // 需要扔掉的字符
            char oldLeft = s2.charAt(j - s1.length());
            dict2[newRight - 'a'] = dict2[newRight - 'a'] + 1;
            dict2[oldLeft - 'a'] = dict2[oldLeft - 'a'] - 1;
            if (Arrays.equals(dict1, dict2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmpty(String s1) {
        if (s1 == null || "".equals(s1)) {
            return true;
        }
        return false;
    }
}
