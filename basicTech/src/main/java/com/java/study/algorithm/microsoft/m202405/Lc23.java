package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

/**
 * @Author： yijun
 * @DATE: 2024/5/28 21:49
 * @Description
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Lc23 {
    public static void main(String[] args) {
        Lc23 lc23 = new Lc23();

        ListNode linkList1 = LinkedListTool.createLinkList(new int[] {1,4,5});
        ListNode linkList2 = LinkedListTool.createLinkList(new int[] {1,3,4});
        ListNode linkList3 = LinkedListTool.createLinkList(new int[] {2,6});

        ListNode[] lists = new ListNode[3];
        lists[0] = linkList1;
        lists[1] = linkList2;
        lists[2] = linkList3;
        ListNode listNode = lc23.mergeKLists(lists);
        LinkedListTool.showLinkList(listNode);

    }

    /**
     * 分治+两两合并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode result = doMergeList(lists, 0, lists.length);
        return result;
    }

    /**
     * 左开右闭 [left, right)
     * @return
     */
    private ListNode doMergeList(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return null;
        }
        if (right == left + 1) {
            return lists[left];
        }
        // 找到中间点
        int mid = left + (right - left) / 2;
        ListNode leftNode = doMergeList(lists, left, mid);
        ListNode rightNode = doMergeList(lists, mid, right);
        return mergeTwo(leftNode, rightNode);
    }

    private ListNode mergeTwo(ListNode leftNode, ListNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return null;
        }
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        ListNode dump = new ListNode();
        ListNode ansPoint = dump;
        ListNode l1 = leftNode;
        ListNode l2 = rightNode;
        ListNode max;
        while (l1 != null && l2 !=null) {
            if (l1.val > l2.val) {
                max = l2;
                l2 = l2.next;
            } else {
                max = l1;
                l1 = l1.next;
            }
            ansPoint.next = max;
            max.next = null;
            ansPoint = ansPoint.next;
        }
        if (l1 != null) {
            ansPoint.next = l1;
        }
        if (l2 != null) {
            ansPoint.next = l2;
        }
        return dump.next;
    }
}
