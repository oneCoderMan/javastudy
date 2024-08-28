package com.java.study.algorithm.microsoft.m202406;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/6/28 21:34
 * @Description
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 */
public class Lc104 {
    public static void main(String[] args) {
        Lc104 lc104 = new Lc104();
        Integer[] nums = {3,9,20,null,null,15,7};
        TreeNode tree = TreeTool.createTree(nums);
        int de = lc104.maxDepth(tree);
        System.out.println(de);

    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDept = maxDepth(root.right);
        return Math.max(leftDepth, rightDept) + 1;
    }
}
