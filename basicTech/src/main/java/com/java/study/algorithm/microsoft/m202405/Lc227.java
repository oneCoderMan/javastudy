package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.Pair;

import java.util.Objects;
import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/5/4 09:43
 * @Description
 * https://leetcode.cn/problems/basic-calculator-ii/description/
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 *
 */
public class Lc227 {

    public static void main(String[] args) {
        Lc227 lc227 = new Lc227();
        int calculate = lc227.calculate("14-3-2");
        System.out.println(calculate);
    }

    /**
     * 双栈
     * 符号栈和数字栈
     * 如果当前是数字就入栈，如果是操作符op
     * if op的优先级 > 操作符栈顶优先级， op入栈
     * if op的优先级 <= 操作符栈顶优先级，依次弹出栈顶计算值并且入数字栈，直到 op的优先级 > 操作符栈顶优先级
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        Stack<Integer> numsStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        opStack.push("#");
        StringBuilder sb = new StringBuilder(s);
        sb.append("#");
        String finalStr = sb.toString();
        int currentIndex = 0;
        while (currentIndex < finalStr.length()) {
            Pair<String, Integer> curToken = getNextToken(finalStr, currentIndex);
            if (curToken == null) {
                break;
            }
            String first = curToken.getFirst();
            if (first.charAt(0) <= '9' && first.charAt(0) >= '0') {
                numsStack.push(Integer.valueOf(first));
                // 进数字栈
            } else {
                // 进操作符栈
                while (!(judgePriority(first, opStack.peek()) == 1) &&
                        !"#".equals(opStack.peek())) {
                    // 需要一直出栈
                    numsStack.push(doCalcu(opStack.pop(), numsStack.pop(), numsStack.pop()));
                }
                opStack.push(first);
            }
            currentIndex = curToken.getSecond();
        }
        System.out.println(numsStack);
        System.out.println(opStack);
        return numsStack.peek();
    }

    private Integer doCalcu(String pop, Integer nums2, Integer nums1) {
        if ("+".equals(pop)) {
            return nums2 + nums1;
        } else if ("-".equals(pop)) {
            return nums1 - nums2;
        } else if ("*".equals(pop)) {
            return nums1 * nums2;
        } else if ("/".equals(pop)) {
            return nums1 / nums2;
        }
        return 0;
    }


    private Pair<String, Integer> getNextToken(String s, int currentIndex) {
        if (currentIndex >= s.length()) {
            return null;
        }
        while (s.charAt(currentIndex) == ' ') {
            currentIndex++;
        }
        if (!(s.charAt(currentIndex) <= '9' && s.charAt(currentIndex) >= '0')) {
            Pair<String, Integer> pair = new Pair<>(String.valueOf(s.charAt(currentIndex)), currentIndex + 1);
            return pair;
        }
        // 解析出来数字
        int next = currentIndex;
        while (next < s.length() && s.charAt(next) <= '9' && s.charAt(next) >= '0') {
            next++;
        }
        Pair<String, Integer> pair = new Pair<>(s.substring(currentIndex, next), next);
        return pair;
    }

    /**
     * 1 curOp 优先级大于栈顶
     * 0 curOp 优先级小于等于栈顶
     * @param curOp
     * @param opTop
     * @return
     */
    private int judgePriority(String  curOp, String opTop) {
        if (Objects.equals(curOp, "+")) {
            if (Objects.equals(opTop, "#")) {
                return  1;
            }
            return 0;
        } else if (Objects.equals(curOp, "-")) {
            if (Objects.equals(opTop, "#")) {
                return  1;
            }
            return 0;
        } else if (Objects.equals(curOp, "*")) {
            if (Objects.equals(opTop, "/")) {
                return  0;
            }
            return 1;

        } else if (Objects.equals(curOp, "/")) {
            // 这里需要特别注意，除法是有顺序的
            if (Objects.equals(opTop, "*") || Objects.equals(opTop, "/")) {
                return  0;
            }
            return 1;
        }
        return 0;
    }
}
