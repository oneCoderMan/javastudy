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
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1,2,3};
        TreeNode tree = TreeTool.createTree(nums);
        Lc124 lc124 = new Lc124();
        int maxPathSum = lc124.maxPathSum(tree);
        System.out.println(maxPathSum);
    }

    public int maxPathSum(TreeNode root) {

        return 0;
    }
}
