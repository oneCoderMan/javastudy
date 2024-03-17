package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author： yijun
 * @DATE: 2024/3/13 22:19
 * @Description
 */
public class Lc94 {
    public static void main(String[] args) {
        Lc94 lc94 = new Lc94();
        Integer[] nums = new Integer[] {1,null,2,3};
        TreeNode tree = TreeTool.createTree(nums);
        List<Integer> re = lc94.inorderTraversal(tree);
        System.out.println(JsonUtil.toJson(re));
    }

    /**
     * 非递归遍历
     * 初始：根节点入栈
     * 然后从当前的节点一直往左走，直到非空，将遇到的节点压栈
     * 然后从栈中弹出元素，加入集合中
     * 然后将右节点入栈，重复往左走
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curNode = root;
        List<Integer> re = new ArrayList<>();
        while (!stack.isEmpty()) {
            // 一直往左走
            while (curNode != null) {
                if (curNode.left == null) {
                    break;
                }
                stack.push(curNode.left);
                curNode = curNode.left;
            }
            // 该出栈了
            TreeNode dealNode = stack.pop();
            re.add(dealNode.val);
            if (dealNode.right != null) {
                stack.push(dealNode.right);
                curNode = dealNode.right;
            } else {
                curNode = null;
            }
        }
        return re;
    }
}
