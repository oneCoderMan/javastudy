package com.java.study.algorithm.microsoft.m202406;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/6/1 10:25
 * @Description
 * https://leetcode.cn/problems/lexicographical-numbers/description/
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 *
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */
public class Lc386 {
    public static void main(String[] args) {
        Lc386 lc386 = new Lc386();
        List<Integer> re = lc386.lexicalOrder(13);
        System.out.println(JsonUtil.toJson(re));

    }

    /**
     * 写递归，十叉数
     * todo 递归形式转迭代
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(i, result, n);
        }
        return result;
    }

    private void dfs(int cur, List<Integer> result, int n) {
        if (cur <= n) {
            result.add(cur);
        }
        if (cur > n) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            dfs(cur * 10 + i, result, n);
        }
    }
}
