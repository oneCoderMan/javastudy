package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;
import static com.java.study.algorithm.init.LinkedListTool.showLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/3/2 08:55
 * @Description
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class Lc25 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5};
        ListNode head = createLinkList(nums);
        showLinkList(head);
        Lc25 lc25 = new Lc25();
        ListNode newHead = lc25.reverseKGroup(head, 1);
        System.out.println("====");
        showLinkList(newHead);
    }

    /**
     *  dump -> 2 -> 1   3-> 4   5 -> null
     *  rawStart： 指向还没有处理的开头（5）
     *  dealHead: 指向要处理的开头 (3)
     *  dealTail: 指向要处理的结尾 (4)
     *  newTail: 指向已经处理的末尾 (1)
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k <= 0) {
            return head;
        }
        // 虚拟节点
        ListNode dump = new ListNode();

        ListNode rawStart = head;
        ListNode newTail = dump;
        ListNode dealHead = null;
        ListNode dealTail = null;
        while (rawStart != null) {
            // 开始遍历找k个
            dealHead = rawStart;
            dealTail = rawStart;
            boolean hasKNode = true;
            int i = 0;
            // 找k个元素
            for (; i < k - 1; i++) {
                if (dealTail.next == null) {
                    break;
                }
                dealTail = dealTail.next;
            }
            // 断开链接
            rawStart = dealTail.next;
            dealTail.next = null;
            // 不足K个
            if (i < k - 1) {
                hasKNode = false;
            }
            if (hasKNode) {
                // 翻转
                ListNode reverseNode = reverseLinkedNode(dealHead);
                newTail.next = reverseNode;
                newTail = dealHead;
            } else {
                newTail.next = dealHead;
                rawStart = null;
            }
        }
        return dump.next;
    }

    private ListNode reverseLinkedNode(ListNode dealHead) {
        if (dealHead == null) {
            return null;
        }
        // 头插法
        ListNode dump = new ListNode();
        ListNode currentHead = dealHead;
        while (currentHead != null) {
            ListNode next = dump.next;
            dump.next = currentHead;
            currentHead = currentHead.next;
            dump.next.next = next;
        }
        return dump.next;
    }

}
