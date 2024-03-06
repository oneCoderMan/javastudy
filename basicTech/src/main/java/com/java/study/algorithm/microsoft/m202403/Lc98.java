package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/3/6 22:24
 * @Description
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Lc98 {
    public static void main(String[] args) {

//        Integer[] nums = new Integer[] {5,1,4,null,null,3,6};
//        Integer[] nums = new Integer[] {2, 2,2};
        Integer[] nums = new Integer[] {2147483647,2147483647,2147483647};
        TreeNode tree = TreeTool.createTree(nums);
        Lc98 lc98 = new Lc98();
        boolean validBST1 = lc98.isValidBST(tree);
        boolean validBST2 = lc98.isValidBST2(tree);
        System.out.println(validBST1 + "  " + validBST2);
    }

    /**
     * 方法一：中序遍历出来的数组是有序的
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        List<Integer> ans = new ArrayList<>();
        midTraversal(root, ans);
        for (int i = 1; i < ans.size(); i++) {
            // 注意要小于等于，搜索树里不能有相同元素
            if (ans.get(i - 1) >= ans.get(i)) {
                return false;
            }
        }
        return true;
    }
    public void midTraversal(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        midTraversal(root.left, ans);
        ans.add(root.val);
        midTraversal(root.right, ans);
    }

    /**
     * 方法2：中序遍历的时候，确定每一个节点应该在的区间
     * init：(-inf, inf)
     * 左子树里面值的边界：（current.min，root.val)
     * 右子树里面值的边界， (root.val, current.max)
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return false;
        }
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validHelper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        // 先判断中间节点是否满足
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // 判断左树
        boolean left = validHelper(root.left, min, root.val);
        if (left == false) {
            return false;
        }
        // 判断右树
        boolean right = validHelper(root.right, root.val, max);
        if (right == false) {
            return false;
        }
        return true;
    }




}
