package com.java.study.algorithm.microsoft.m202403;

import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/3/18 21:54
 * @Description
 * https://leetcode.cn/problems/valid-parentheses/description/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 */
public class Lc20 {
    public static void main(String[] args) {
        Lc20 lc20 = new Lc20();
        String s = "()[]{[}";
        boolean valid = lc20.isValid(s);
        System.out.println(valid);
    }
    public boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        if (s.length() <= 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            Character peek = stack.peek();
            // 如果出现右括号直接返回
            if (peek == ')' || peek == ']' || peek == '}') {
                return false;
            }
            if (canMatch(peek, s.charAt(i))) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    private boolean canMatch(Character peek, Character currentChar) {
        if ('(' == peek && ')' == currentChar) {
            return true;
        } else if ('[' == peek && ']' == currentChar) {
            return true;
        } else if ('{' == peek && '}' == currentChar) {
            return true;
        }
        return false;
    }
}
