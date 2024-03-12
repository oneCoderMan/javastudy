package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.ListNode;

import static com.java.study.algorithm.init.LinkedListTool.createLinkList;

/**
 * @Author： yijun
 * @DATE: 2024/3/11 23:08
 * @Description
 */
public class Lc141 {
    public static void main(String[] args) {
        Lc141 lc141 = new Lc141();
        int[] nums = new int[] {1,2,3,4,5};
        ListNode head = createLinkList(nums);
        boolean result = lc141.hasCycle(head);
        System.out.println(result);
    }
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fastPoint = head;
        ListNode slowPoint = head;
        while (fastPoint != null && slowPoint != null) {
            // 快指针走两步
            for (int i = 0; i < 2; i++) {
                if (fastPoint == null) {
                    return false;
                }
                fastPoint = fastPoint.next;
            }
            // 慢指针走一步
            slowPoint = slowPoint.next;
            if (slowPoint == null) {
                return false;
            }
            if (slowPoint == fastPoint) {
                return true;
            }
        }
        return false;
    }
}
