package com.java.study.algorithm.init;

/**
 * @Author： yijun
 * @DATE: 2024/1/5 22:46
 * @Description
 */
public class LinkedListTool {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3};
        ListNode linkList = createLinkList(nums);
        showLinkList(linkList);
    }

    public static ListNode createLinkList(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        ListNode virtualNode = new ListNode();
        ListNode cur = virtualNode;
        for (int i = 0; i < nums.length; i++ ) {
            ListNode newNode = new ListNode(nums[i]);
            cur.next = newNode;
            cur = cur.next;
        }
        return virtualNode.next;
    }

    public static void showLinkList(ListNode node) {
        if (node == null) {
            System.out.println("null");
        }
        ListNode cur = node;
        while (cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
    }

}
