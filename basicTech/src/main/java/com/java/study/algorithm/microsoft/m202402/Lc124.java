package com.java.study.algorithm.microsoft.m202402;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/2/4 10:45
 * @Description
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中
 * 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 */
public class Lc124 {
    private int maxPath;
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,2,3};
        TreeNode tree = TreeTool.createTree(nums);
        Lc124 lc124 = new Lc124();
        int maxPathSum = lc124.maxPathSum(tree);
        System.out.println(maxPathSum);
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxPath = root.val;
        maxValueFromSub(root);
        return maxPath;
    }

    /**
     * 返回每个节点向上能提供的一个最大贡献值，
     * 需要后序遍历
     * @param root
     * @return
     */
    public int maxValueFromSub(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Integer leftMaxValue = maxValueFromSub(root.left);
        Integer rightMaxValue = maxValueFromSub(root.right);
        Integer v1 = root.val;
        Integer v2 = root.val + leftMaxValue;
        Integer v3 = root.val + rightMaxValue;
        Integer v4 = root.val + rightMaxValue + leftMaxValue;

        // 贡献值比较节点本身，节点与左子树，节点与右子树之间取最大
        Integer canMaxUpTo = Math.max(Math.max(v2, v3), v1);
        // 最长路径需要考虑本节点与左右子树的一个路径
        maxPath = Math.max(Math.max(maxPath, canMaxUpTo), v4);
        return Math.max(Math.max(v2, v3), v1);
    }
}
