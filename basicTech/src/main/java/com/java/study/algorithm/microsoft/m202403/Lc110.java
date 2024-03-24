package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/3/23 09:55
 * @Description
 * https://leetcode.cn/problems/balanced-binary-tree/description/
 * 给定一个二叉树，判断它是否是平衡二叉树
 * 是指该树所有节点的左右子树的深度相差不超过 1。
 *
 */
public class Lc110 {
    private boolean isBalance;

    public static void main(String[] args) {
        Lc110 lc110 = new Lc110();
        Integer[] nums = new Integer[] {1,2,2,3,3,null,null,4,4};
        TreeNode root = TreeTool.createTree(nums);
        boolean balanced = lc110.isBalanced(root);
        System.out.println(balanced);
    }

    /**
     * 后序遍历，判断每一个节点左右子树的深度
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        isBalance = true;
        getTheDepth(root);
        return isBalance;
    }
    public int getTheDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTheDepth(root.left);
        int rightDepth = getTheDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalance = false;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
