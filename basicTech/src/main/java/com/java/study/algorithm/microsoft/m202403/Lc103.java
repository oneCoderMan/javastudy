package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author： yijun
 * @DATE: 2024/3/1 22:29
 * @Description
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 *
 * 103. 二叉树的锯齿形层次遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class Lc103 {
    public static void main(String[] args) {
        Lc103 lc103 = new Lc103();
        Integer[] nums = new Integer[] {3, 9,20,null,null,15,7};
        TreeNode tree = TreeTool.createTree(nums);
        List<List<Integer>> lists = lc103.zigzagLevelOrder(tree);
        System.out.println(JsonUtil.toJson(lists));
    }

    /**
     * 用一个队列，
     * 队列的个数就是元素的大小
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 初始化
        // 优化可以不用记录节点的层次，每层遍历的时候拿到queue.size就是当前层次的元素个数，直接取queue.size个元素就行
        queue.offer(root);
        Integer currenLevel = 1;

        List<List<Integer>> re = new ArrayList<>();
        boolean leftToRightFlag = true;
        while (!queue.isEmpty()) {
            // 把当前层的都取出来
            List<Integer> currentLevelList = new ArrayList<>();

            // 这个是当前层的元素个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevelList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (leftToRightFlag) {
                re.add(currentLevelList);
            } else {
                List<Integer> reverseList = new ArrayList<>();
                for (int i = currentLevelList.size() - 1; i>= 0; i--) {
                    reverseList.add(currentLevelList.get(i));
                }
                re.add(reverseList);
            }
            leftToRightFlag = !leftToRightFlag;
            currenLevel++;
        }
        return re;
    }
}

