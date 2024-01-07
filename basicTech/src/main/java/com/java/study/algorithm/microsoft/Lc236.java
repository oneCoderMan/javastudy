package com.java.study.algorithm.microsoft;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/1/7 10:20
 * @Description
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 */
public class Lc236 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode tree = TreeTool.createTree(nums);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        Lc236 lc236 = new Lc236();
        TreeNode treeNode = lc236.lowestCommonAncestor(tree, p, q);
        System.out.println(treeNode.val);

    }

    /**
     *
     * @param root
     * @param p
     * @param q
     * @return 最近公共祖先或者p/q 后续遍历携带节点回来
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 找到一个节点
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // 说明当前的一个公共节点已经找到
        if (leftResult != null && rightResult != null) {
            return root;
        }
        // 返回单边结果
        if (leftResult != null) {
            return leftResult;
        }
        if (rightResult != null) {
            return rightResult;
        }
        return null;
    }
}
