package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

/**
 * @Author： yijun
 * @DATE: 2024/5/30 22:44
 * @Description
 * https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class Lc92 {
    public static void main(String[] args) {
        Lc92 lc92 = new Lc92();
        ListNode linkList = LinkedListTool.createLinkList(new int[]{1,2,3,4,5});
        ListNode listNode = lc92.reverseBetween(linkList, 2, 4);
        LinkedListTool.showLinkList(listNode);
    }

    /**
     * 主要是需要确认四个点，虚拟头节点
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        if (left == right) {
            return head;
        }
        // 虚拟头
        ListNode dump = new ListNode();
        dump.next = head;
        ListNode preNode = dump;
        ListNode tailNode;
        ListNode leftNode = preNode;
        ListNode rightNode;
        for (int i = 0; i < left; i++) {
            preNode = leftNode;
            leftNode = leftNode.next;
        }
        rightNode = leftNode;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }
        tailNode = rightNode.next;
        rightNode.next = null;
        preNode.next = null;
        // 翻转 leftNode -> rightNode;
        ListNode reverse = reverse(leftNode);
        preNode.next = reverse;
        leftNode.next = tailNode;
        return dump.next;
    }

    private ListNode reverse(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode dump = new ListNode();
        ListNode p = node;
        while (p != null) {
            ListNode cur = p;
            p = p.next;
            cur.next = dump.next;
            dump.next = cur;
        }
        return dump.next;
    }

}
