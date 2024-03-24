package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/3/22 22:31
 * @Description
 * https://leetcode.cn/problems/diameter-of-binary-tree/description/
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
public class Lc543 {
    private int ans;
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,2};
        TreeNode tree = TreeTool.createTree(nums);
        Lc543 lc543 = new Lc543();
        int re = lc543.diameterOfBinaryTree(tree);
        System.out.println(re);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        calcuLenPostOrder(root);
        return ans;
    }

    /**
     * 返回当前节点能获取的长度
     * @param root
     * @return 当前节点向上提供的最长路径长度
     */
    private int calcuLenPostOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLen = calcuLenPostOrder(root.left);
        int rightLen = calcuLenPostOrder(root.right);
        // 当前层的最大长度
        ans = Math.max(ans, leftLen + rightLen);
        // 可以往上提供的最大长度
        return Math.max(leftLen, rightLen) + 1;
    }
}
