package com.java.study.algorithm.microsoft.m202404;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

/**
 * @Author： yijun
 * @DATE: 2024/4/7 22:28
 * @Description
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class Lc105 {
    public static void main(String[] args) {
        Lc105 lc105 = new Lc105();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = lc105.buildTree(preorder, inorder);
        TreeTool.prePrintTree(node);

    }

    /**
     * 由前序遍历可以推导出根节点
     * 右中序遍历可以推导出左节点和右节点
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        return doBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }


    /**
     *
     * @param preOrder 前序遍历序列
     * @param preLeft 前序遍历的当前树开始位置，闭区间
     * @param preRight 前序遍历的当前树结束位置，闭区间
     * @param inorder 中序遍历序列
     * @param inLeft 中序遍历的当前树开始位置，闭区间
     * @param inRight 中序遍历的当前树结束位置，闭区间
     * @return
     */
    public TreeNode doBuildTree(int[] preOrder,
                                int preLeft,
                                int preRight,
                                int[] inorder,
                                int inLeft,
                                int inRight) {
        if (preRight < preLeft) {
            return null;
        }
        // 只有一个元素
        if (preLeft == preRight) {
            return new TreeNode(preOrder[preLeft]);
        }
        // 找到根节点
        TreeNode root = new TreeNode(preOrder[preLeft]);
        // 在中序遍历中找到根的位置
        // 这里可以用hash表优化
        int rootLocationInMid = inLeft;
        for (; rootLocationInMid <= inRight; rootLocationInMid++) {
            if (inorder[rootLocationInMid] == preOrder[preLeft]) {
                break;
            }
        }
        // 左子树的元素个数
        int numOfLeft = rootLocationInMid - inLeft;
        // 确定左右子树的范围
        root.left = doBuildTree(preOrder, preLeft + 1, preLeft + numOfLeft,
                inorder, inLeft, rootLocationInMid - 1);
        root.right = doBuildTree(preOrder, preLeft + numOfLeft + 1, preRight,
                inorder, rootLocationInMid + 1, inRight);
        return root;
    }
}
