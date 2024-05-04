package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

/**
 * @Author： yijun
 * @DATE: 2024/5/2 10:27
 * @Description
 * https://leetcode.cn/problems/reorder-list/description/
 *
 */
public class Lc143 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5};
        ListNode head = LinkedListTool.createLinkList(nums);
        Lc143 lc143 = new Lc143();
        lc143.reorderList(head);
        LinkedListTool.showLinkList(head);
    }

    /**
     * 找到链表中间节点，然后反转中间的点
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 找到中间节点
        ListNode mid = splitLinkList(head);
        // 中间节点翻转
        mid = reverse(mid);
        // 合并两个节点
        ListNode result = combineTwoLinkList(head, mid);
        head = result;
    }

    private ListNode combineTwoLinkList(ListNode head, ListNode mid) {
        ListNode newDump = new ListNode();
        ListNode curIndex = newDump;
        ListNode index1 = head;
        ListNode index2 = mid;
        while (index1 != null && index2 != null) {
            curIndex.next = index1;
            index1 = index1.next;
            curIndex = curIndex.next;
            // 断开链
            curIndex.next = null;

            curIndex.next = index2;
            index2 = index2.next;
            curIndex = curIndex.next;
            // 断开链
            curIndex.next = null;
        }
        if (index1 != null) {
            curIndex.next = index1;
        }
        if (index2 != null) {
            curIndex.next = index2;
        }
        return newDump.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode current = head;
        ListNode dump = new ListNode();
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = dump.next;
            dump.next = current;
            current = next;
        }
        return dump.next;
    }

    private ListNode splitLinkList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPre = slow;
        while (true) {
            fast = fast.next;
            if (fast == null || fast.next == null) {
                slowPre = slow;
                slow = slow.next;
                break;
            }
            fast = fast.next;
            if (fast.next == null) {
                slowPre = slow;
                slow = slow.next;
                break;
            }
            slow = slow.next;
        }
        // 从slow处开始断开
        slowPre.next = null;
        return slow;
    }
}
