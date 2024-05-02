package com.java.study.algorithm.microsoft.m202404;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/4/5 22:48
 * @Description
 * https://leetcode.cn/problems/permutations-ii/description/
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 */
public class Lc148 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 6,4, 9, 7};
        ListNode linkList = createLinkList(nums);
        Lc148 lc148 = new Lc148();
        ListNode sortHead = lc148.sortList(linkList);
        LinkedListTool.showLinkList(sortHead);


    }

    /**
     * 归并排序 自顶向下
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 只有一个节点的情况
        if (head == null || head.next == null) {
            return head;
        }
        // 先二分 （链表的二分可以用快慢指针）
        ListNode mid = findMidNode(head);
        if (mid == null) {
            return head;
        }
        ListNode next = mid.next;
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(next);
        ListNode sortedHead = doMerge(left, right);
        return sortedHead;
    }

    private ListNode doMerge(ListNode left, ListNode right) {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode dump = new ListNode();
        ListNode tail = dump;
        ListNode node1 = left;
        ListNode node2 = right;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                tail.next = node1;
                node1 = node1.next;
                tail.next.next = null;
                tail = tail.next;
            } else {
                tail.next = node2;
                node2 = node2.next;
                tail.next.next = null;
                tail = tail.next;
            }
        }
        if (node1 != null) {
            tail.next = node1;
        }
        if (node2 != null) {
            tail.next = node2;
        }
        return dump.next;
    }

    /**
     * 找到链表的中间节点的时候可以使用快慢指针
     * 快指针从head开始走，每次走两步，到末尾结束
     * 慢指针从slow走，每次走1一步
     * @param head
     * @return
     */
    private ListNode findMidNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = null;
        ListNode fast = head;
        while (fast.next != null) {
            if (slow == null) {
                slow = head;
            } else {
                slow = slow.next;
            }
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
        }
        return slow;
    }
}
