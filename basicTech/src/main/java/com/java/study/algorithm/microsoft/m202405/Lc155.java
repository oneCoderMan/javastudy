package com.java.study.algorithm.microsoft.m202405;

import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/5/29 22:37
 * @Description
 * https://leetcode.cn/problems/min-stack/description/
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * todo 熟悉一下不使用辅助栈的方法
 */
public class Lc155 {
    public static void main(String[] args) {
        Lc155 lc155 = new Lc155();
        lc155.test();

    }

    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.getMin());

    }

    class MinStack {
        private Stack<Long> curStack;
        private Stack<Long> minStack;

        public MinStack() {
            curStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            curStack.push(Long.valueOf(val));
            if (minStack.isEmpty()) {
                minStack.push(Long.valueOf(val));
                return;
            }
            if (val <= minStack.peek()) {
                minStack.push(Long.valueOf(val));
            }
        }

        public void pop() {
            Long pop = curStack.pop();
            if (pop.equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return curStack.peek().intValue();
        }

        public int getMin() {
            return minStack.peek().intValue();
        }
    }

}
