package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.ListNode;

/**
 * @Author： yijun
 * @DATE: 2024/3/27 21:35
 * @Description
 * https://leetcode.cn/problems/linked-list-cycle-ii/solutions/181070/xiang-xi-tu-jie-ken-ding-kan-de-ming-bai-by-xixili/
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class Lc142 {
    public static void main(String[] args) {
        Lc142 lc142 = new Lc142();
        // 3204
        ListNode head = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(4);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next=  n2;
        ListNode listNode = lc142.detectCycle(head);
        System.out.println(listNode == null ? "null" : listNode.val);


    }

    /**
     * head->---X----> entry -----Y------meet----
     *                 |                        |
     *                 |-------------Z----------|
     *   头在入环口的距离是X， 快慢指针相遇的点是meet，入环扣到相遇点是Y
     *   相遇点到入环扣距离是Z （Y+Z)是环的大小
     *   快指针走过的距离是 X + Y + n(Y+Z)
     *   慢指针走过的距离是 X + Y
     *  有 2（X + Y ） = X + Y + n(Y+Z)
     *  =>  X + Y  = n(Y+Z)
     *  => x =  n(Y+Z) - Y
     *  => x = (n-1)(Y+Z) + Z
     *  => 如果一个指针从head走，一个指针从相遇点走，速度一样，最终在entry相遇
     *
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next == head) {
            return head;
        }
        // 第一步，找快慢指针相遇的点
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                break;

            }
            fast = fast.next;
            if (slow == fast) {
                break;
            }
        }
        // 没有环
        if (fast == null) {
            return null;
        }
        // 有环 (slow 从当前节点出发，fast从head出发步进为一，相遇点就是环的入口，)
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
