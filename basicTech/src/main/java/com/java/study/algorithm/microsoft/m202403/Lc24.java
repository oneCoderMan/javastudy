package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/3/30 22:59
 * @Description
 * https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 *  给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点
 */
public class Lc24 {
    public static void main(String[] args) {
        Lc24 lc24 = new Lc24();
        int[] nums = new int[] {1,2,3,4,5};
        ListNode head = createLinkList(nums);
        LinkedListTool.showLinkList(head);
        ListNode re = lc24.swapPairs(head);
        System.out.println("===");
        LinkedListTool.showLinkList(re);
    }
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dump = new ListNode();
        ListNode newTail = dump;
        ListNode oldHead = head;

        ListNode start;
        ListNode end;
        while (true) {
            if (oldHead == null) {
                break;
            }
            start = oldHead;
            // 不可以移动两步
            if (oldHead.next == null) {
                break;
            }
            end = oldHead.next;
            // 保存原来的链
            oldHead = end.next;

            // 翻转start end
            end.next = start;
            start.next = null;
            // 链接数据
            newTail.next = end;
            newTail = start;
        }
        newTail.next = oldHead;
        return dump.next;
    }

}
