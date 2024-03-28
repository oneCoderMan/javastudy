package com.java.study.algorithm.init;

/**
 * @Author： yijun
 * @DATE: 2024/3/28 21:17
 * @Description
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
