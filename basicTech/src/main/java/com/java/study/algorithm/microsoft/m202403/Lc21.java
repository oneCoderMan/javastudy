package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;
import static com.java.study.algorithm.init.LinkedListTool.showLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/3/17 16:41
 * @Description
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的
 */
public class Lc21 {
    public static void main(String[] args) {
        Lc21 lc21 = new Lc21();
        int[] list1 = new int[] {1,2,4};
        int[] list2 = new int[] {1,3,4};
        ListNode head1 = createLinkList(list1);
        ListNode head2 = createLinkList(list2);
        ListNode listNode = lc21.mergeTwoLists(head1, head2);
        showLinkList(listNode);
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        ListNode newHead = new ListNode();
        ListNode newTail;
        newTail = newHead;

        ListNode curNode;
        ListNode p = list1;
        ListNode q = list2;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                curNode = p;
                p = p.next;
            } else {
                curNode = q;
                q = q.next;
            }
            newTail.next = curNode;
            newTail = newTail.next;
            newTail.next = null;
        }
        if (p != null) {
            newTail.next = p;
        }
        if (q != null) {
            newTail.next = q;
        }
        return newHead.next;
    }
}
