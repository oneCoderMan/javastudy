package com.java.study.algorithm.microsoft.m202403;

import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/3/30 08:58
 * @Description
 * https://leetcode.cn/problems/basic-calculator/description/
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 */
public class Lc224 {
    public static void main(String[] args) {
        Lc224 lc224 = new Lc224();
        String s = "-2 + 1";
        int calculate = lc224.calculate(s);
        System.out.println(calculate);
    }

    /**
     * 一些用例：
     * -2 + 1 = -1
     * "1-(     -2)" = 3
     * 2- 1 + 1 = 2
     * 2-(1+1) = 0
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        // 两个栈，一个符号栈，一个数字栈
        Stack<Integer> numsStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        // 去除空格
        String realStr = s.replace(" ", "");
        if (realStr.charAt(0) == '-') {
            numsStack.push(0);
        }
        for (int i = 0;  i < realStr.length(); i++) {
            if (realStr.charAt(i) == '(') {
                opStack.push(realStr.charAt(i));
                continue;
            }
            if (realStr.charAt(i) == ')') {
                // 需要触发计算, 直到遇到 ( 结束
                doCalcu(numsStack, opStack);
                if (opStack.peek() == '(') {
                    opStack.pop();
                }
            }
            if (realStr.charAt(i) == '+' || realStr.charAt(i) == '-') {
                // (- (+ 这种情况补0
                if (i > 0 && realStr.charAt(i - 1) == '(') {
                    numsStack.push(0);
                }
                // 计算完成到（ 后在放进去
                doCalcu(numsStack, opStack);
                opStack.push(realStr.charAt(i));
            }

            // 碰上了数字
            if (realStr.charAt(i) <= '9' && realStr.charAt(i) >= '0') {
                int num = 0;
                int j = i;
                for (; j < realStr.length(); j++) {
                    if (!(realStr.charAt(j) <= '9' && realStr.charAt(j) >= '0')) {
                        break;
                    }
                    num = num * 10 + (realStr.charAt(j) - '0');

                }
                numsStack.push(num);
                // 这里需要回退一下，因为上面会进行指针的+1操作
                i = j - 1;
            }
        }
//        System.out.println(JsonUtil.toJson(numsStack));
//        System.out.println(JsonUtil.toJson(opStack));
        // 开始操作栈元素
        doCalcu(numsStack, opStack);
        return numsStack.pop();
    }

    private void doCalcu(Stack<Integer> numsStack, Stack<Character> opStack) {
        // 开始操作栈元素，或者遇到'('就结束
        while (!opStack.isEmpty()) {
            if (opStack.peek() == '(') {
                break;
            }
            if (numsStack.size() < 2) {
                break;
            }
            Character op = opStack.pop();
            Integer num2 = numsStack.pop();
            Integer num1 = numsStack.pop();
            if (op == '+') {
                numsStack.push(num1 + num2);
            } else if (op == '-') {
                numsStack.push(num1 - num2);
            }
        }
    }

}
