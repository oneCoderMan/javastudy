package com.java.study.algorithm.microsoft.m202406;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

/**
 * @Author： yijun
 * @DATE: 2024/6/27 23:00
 * @Description
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class Lc82 {
    public static void main(String[] args) {
        Lc82 lc82 = new Lc82();
        int[] nums = new int[] {1,1};
        ListNode head = LinkedListTool.createLinkList(nums);
        ListNode resultHead = lc82.deleteDuplicates(head);
        LinkedListTool.showLinkList(resultHead);
    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dump = new ListNode();
        ListNode cur = dump;
        ListNode next1 = head;
        ListNode next2 = head.next;

        while (true) {
            if (next2 == null) {
                break;
            }
            // 如果这两个不重复，那么就可以移动了
            if (next1.val != next2.val) {
                cur.next = next1;
                cur = cur.next;
                next1 = next2;
                next2 = next2.next;
                continue;
            }
            // 这两个重复，需要找到 和这两个重复的所有点
            while (next2.next != null && next2.next.val == next2.val) {
                next2 = next2.next;
            }
            next1 = next2.next;
            if (next1 != null) {
                next2 = next1.next;
            } else {
                next2 = null;
            }

        }
        cur.next = next1;
        return dump.next;
    }
}
