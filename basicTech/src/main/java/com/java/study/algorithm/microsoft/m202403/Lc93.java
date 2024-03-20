package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author： yijun
 * @DATE: 2024/3/17 22:15
 * @Description
 * https://leetcode.cn/problems/restore-ip-addresses/description/
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
 *
 */
public class Lc93 {
    public static void main(String[] args) {
        String s = "101023";
        Lc93 lc93 = new Lc93();
        List<String> results = lc93.restoreIpAddresses(s);
        System.out.println(JsonUtil.toJson(results));
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || "".equals(s) || s.length() < 4) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        doRestoreIpByBackTracking(result, new ArrayList<>(), s, 0, 1);
        return result;
    }

    private void doRestoreIpByBackTracking(List<String> result,
                                           List<Integer> path,
                                           String s,
                                           int startIndex,
                                           int segNo) {
        // 最后一段
        if (segNo == 4) {
            // 不需要分割了，直接判断是否符合要求
            Integer currentNum = getValidNum(s, startIndex, s.length());
            if (currentNum == -1) {
                return;
            }
            path.add(currentNum);
            String ip = path.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("."));
            result.add(ip);
            path.remove(path.size() - 1);
            return;
        }
        // 从starIndex开始分段
        for (int i = startIndex; i < s.length(); i++) {
            Integer currentNum = getValidNum(s, startIndex, i + 1);
            if (currentNum == -1) {
                return;
            }
            path.add(currentNum);
            // 这个i + 1是关键
            doRestoreIpByBackTracking(result, path, s, i + 1, segNo + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 左开右闭区间
     * @return
     */
    private Integer getValidNum(String s, int startIndex, int endIndex) {
        if (s == null || "".equals(s)) {
            return -1;
        }
        if (startIndex >= s.length() || endIndex > s.length()) {
            return -1;
        }
        // 由前导0
        if (endIndex - startIndex > 1 && s.charAt(startIndex) == '0') {
            return -1;
        }
        int ans = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return -1;
            }
            ans = ans * 10 + (s.charAt(i) - '0');
            if (ans > 255) {
                return -1;
            }
        }
        return ans;
    }
}
