package com.java.study.algorithm.microsoft;

import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;
import static com.java.study.algorithm.init.LinkedListTool.showLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/1/5 22:41
 * @Description
 * https://leetcode.cn/problems/reverse-linked-list/
 * 你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Lc206 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5};
        ListNode head = createLinkList(nums);

        Lc206 lc206 = new Lc206();
        ListNode listNode = lc206.reverseList(head);
        showLinkList(listNode);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode vNode = new ListNode();
        // 当前要处理的节点
        ListNode cur = head;
        // 下一个节点
        ListNode next = cur.next;

        while (cur != null) {
            // 保持下一个节点
            next = cur.next;
            cur.next = vNode.next;
            vNode.next = cur;
            // 移动
            cur = next;
        }
        return vNode.next;
    }
}
