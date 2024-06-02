package com.java.study.algorithm.microsoft.m202406;

import com.java.study.algorithm.init.LinkedListTool;
import com.java.study.algorithm.init.ListNode;
import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/6/2 15:47
 * @Description
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 *
 */
public class Lc160 {
    public static void main(String[] args) {
        ListNode ln1 = LinkedListTool.createLinkList(new int[]{1, 4, 8});
        ListNode ln2 = LinkedListTool.createLinkList(new int[]{1, 3, 8});
        Lc160 lc160 = new Lc160();
        ListNode intersectionNode = lc160.getIntersectionNode(ln1, ln2);
        System.out.println(intersectionNode == null);
        System.out.println(JsonUtil.toJson(intersectionNode));
    }

    /**
     * 双指针走
     * 指针1从 A开始走，走到末尾然后从B走
     * 指针2从 B开始走，走到然后从A走
     * 如果有相交了，相遇就是
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        boolean nullFist1 = true;
        boolean nullFist2 = true;

        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null && nullFist1) {
                p1 = headB;
                nullFist1 = false;
            }
            if (p2 == null && nullFist2) {
                p2 = headA;
                nullFist2 = false;
            }
        }
        return null;
    }
}
