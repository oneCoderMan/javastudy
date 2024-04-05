package com.java.study.algorithm.microsoft.m202404;

import java.util.HashMap;

/**
 * @Author： yijun
 * @DATE: 2024/4/4 09:21
 * @Description
 * https://leetcode.cn/problems/minimum-window-substring/
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
public class Lc76 {
    /**
     * 0 表示窗口里面的这个元素不缺
     * 负数 表示窗口里面的这个元素有剩余
     * 正数，表示窗口里面的这个元素有缺的个数
     */
    public static void main(String[] args) {
        Lc76 lc76 = new Lc76();
//        String re = lc76.minWindow("ADOBECODEBANC", "ABC");
        String re = lc76.minWindow("a", "aa");
        // s = "a", t = "a"
        System.out.println(re);
    }

    /**
     * 返回 s 中涵盖 t 所有字符的最小子串
     * @param s
     * @param t
     * @return
     *
     */
    public String minWindow(String s, String t) {
        if (s == null || "".equals(s)) {
            return null;
        }
        if (t == null || "".equals(t)) {
            return null;
        }
        if (t.length() > s.length()) {
            return "";
        }
        //  这个记录当前窗口里面对于目标串缺少的数据
        HashMap<Character, Integer> needCountMaps = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (needCountMaps.containsKey(t.charAt(i))) {
                needCountMaps.put(t.charAt(i), needCountMaps.get(t.charAt(i)) + 1);
            } else {
                needCountMaps.put(t.charAt(i), 1);
            }
        }
        // 这个记录当前窗口里面需要目标字符个数才能覆盖到目标
        int needCount = t.length();
        String minSubStr = null;
        int minSubStrLen = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        while (left < s.length() && right < s.length()) {
            // 右窗口一直伸展
            while (needCount != 0) {
                right++;
                if (right >= s.length()) {
                    return minSubStr;
                }
                // 右扩窗口的时候判断
                if (needCountMaps.containsKey(s.charAt(right))) {
                    needCountMaps.put(s.charAt(right), needCountMaps.get(s.charAt(right)) - 1);
                    if (needCountMaps.get(s.charAt(right)) >= 0) {
                        needCount--;
                    }

                }
            }
            // 已经找到一个容纳所有字符的字串了，从（left,right]区间
            if ((right - left) < minSubStrLen) {
                minSubStr = s.substring(left + 1, right + 1);
                minSubStrLen = minSubStr.length();
            }
            // 收缩左窗口，直到不满足要求
            while (needCount == 0) {
                left++;
                if (left >= s.length()) {
                    break;
                }
                if (needCountMaps.containsKey(s.charAt(left))) {
                    needCountMaps.put(s.charAt(left), needCountMaps.get(s.charAt(left)) + 1);
                    // 大于0的时候才需要加
                    if (needCountMaps.get(s.charAt(left)) > 0) {
                        needCount++;
                    }
                }
            }
            // 已经找到一个容纳所有字符的字串了，从[left,right]区间
            if ((right - left + 1) < minSubStrLen) {
                minSubStr = s.substring(left, right + 1);
                minSubStrLen = minSubStr.length();
            }

        }
        return minSubStr;
    }
}
