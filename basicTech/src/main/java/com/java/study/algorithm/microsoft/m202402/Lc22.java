package com.java.study.algorithm.microsoft.m202402;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author： yijun
 * @DATE: 2024/2/25 21:05
 * @Description
 * https://leetcode.cn/problems/generate-parentheses/description/
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 */
public class Lc22 {
    public static void main(String[] args) {
        Lc22 lc22 = new Lc22();
        List<String> result = lc22.generateParenthesis(3);
        System.out.println(result.stream().collect(Collectors.joining(" , ")));
    }

    /**
     * 括号的生成文法： (A)B
     * 其中a，b分别是不同个数括号组成的对
     * i=n 时，有效括号 (A)B
     * 其中A是p个括号组成的集合，B是q个括号对组成的集合，p + q = n-1
     * 因此可以从0到n递推，p从0枚举到n-1，对应的q从n-1枚举到0
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return Arrays.asList("()");
        }
        Map<Integer, List<String>> nBraceMap = new HashMap<>();
        nBraceMap.put(0, new ArrayList<>());
        nBraceMap.put(1, Arrays.asList("()"));

        for (int i = 2; i <= n; i++) {
            List<String> curBrasResult = new ArrayList<>();
            // 枚举括号里面的
            for (int j = 0; j < i; j++) {
                List<String> preBrace = nBraceMap.get(j);
                List<String> suffixBrace = nBraceMap.get(i - 1 - j);
                List<String> resultItem = geneBraceInner(preBrace, suffixBrace);
                curBrasResult.addAll(resultItem);
            }
            nBraceMap.put(i, curBrasResult);
        }
        return nBraceMap.get(n);
    }

    private List<String> geneBraceInner(List<String> preBrace, List<String> suffixBrace) {
        List<String> result = new ArrayList<>();
        if (preBrace == null || preBrace.size() < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("()");
            for (String suffixStr : suffixBrace) {
                StringBuilder sbInner = new StringBuilder(sb);
                sbInner.append(suffixStr);
                result.add(sbInner.toString());
            }
            return result;
        }
        for (String preStr : preBrace) {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(preStr).append(")");
            if (suffixBrace != null && suffixBrace.size() > 0) {
                for (String suffixStr : suffixBrace) {
                    StringBuilder sbInner = new StringBuilder(sb);
                    sbInner.append(suffixStr);
                    result.add(sbInner.toString());
                }
            } else {
                result.add(sb.toString());
            }
        }
        return result;
    }
}
