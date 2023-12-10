package com.java.study.algorithm.init;

import com.java.study.utils.JsonUtil;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2023/12/10 10:39
 * @Description
 */
public class StackTest {
    public static void main(String[] args) {
        // testStack();

        testLinkedListAsStack();

    }

    public static void testStack() {
        Stack<Integer> stack = new Stack();
        // 元素入栈
        stack.push(1);
        stack.push(3);
        System.out.println(stack);
        // 取栈顶元素，不移除
        System.out.println(stack.peek());
        System.out.println(stack);
        // 取栈顶元素，并且移除
        System.out.println(stack.pop());
        System.out.println(stack);

    }

    public static void testLinkedListAsStack() {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        if (stack.isEmpty()) {
            System.out.println("stack is not empty");
        }
        // 取栈顶元素，不移除
        Integer peek = stack.peek();
        System.out.println(peek);
        System.out.println(JsonUtil.toJson(stack));

        // 取栈顶元素，并且移除
        Integer pop = stack.pop();
        System.out.println(pop);
        System.out.println(JsonUtil.toJson(stack));

    }
}
