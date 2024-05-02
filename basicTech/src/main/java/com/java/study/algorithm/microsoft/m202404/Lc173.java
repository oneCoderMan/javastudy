package com.java.study.algorithm.microsoft.m202404;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/4/7 21:45
 * @Description
 * https://leetcode.cn/problems/binary-search-tree-iterator/description/
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器
 *
 */
public class Lc173 {
    public static void main(String[] args) {
        Lc173 lc173 = new Lc173();
        lc173.testIterator();

    }

    public void testIterator() {
        Integer[] nums = new Integer[] {7, 3, 15, null, null, 9, 20};
        TreeNode root = TreeTool.createTree(nums);
        TreeTool.prePrintTree(root);
        List<Integer> re = inOrder(root);
        System.out.println();
        System.out.println(JsonUtil.toJson(re));

    }
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> re = new ArrayList<>();
        if (root == null) {
            return re;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 一直往左走
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 这个时候需要出栈
                TreeNode midNode = stack.pop();
                re.add(midNode.val);
                // 往右走
                cur = midNode.right;
            }
        }
        return re;
    }

    /**
     * 二叉树中序遍历的迭代器
     * 使用栈来解决，往左一直压栈，
     * 然后遇到空节点出栈，在获取到右节点
     */
    class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            // 一直往左走
            TreeNode cur = root;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        public int next() {
            // 取出一个元素
            if (stack.isEmpty()) {
                return 0;
            }
            TreeNode pop = stack.pop();
            if (pop.right != null) {
                // 继续向左
                TreeNode cur = pop.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            return pop.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
