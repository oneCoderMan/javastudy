package com.java.study.algorithm.microsoft.m202402;

import com.java.study.algorithm.init.TreeNode;
import com.java.study.algorithm.init.TreeTool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @Author： yijun
 * @DATE: 2024/2/18 23:33
 * @Description
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Lc297 {

    public static void main(String[] args) {
        Lc297 lc297 = new Lc297();
        Integer[] nums = new Integer[] {1,2,3,null,null,4,5};
        TreeNode root = TreeTool.createTree(nums);
        TreeTool.prePrintTree(root);
        System.out.println("\n===");
        String serializeTree = lc297.serialize(root);
        System.out.println(serializeTree);
        System.out.println("=====end");
        TreeNode deserialize = lc297.deserialize(serializeTree);
        TreeTool.prePrintTree(deserialize);

    }

    /**
     * 使用层次遍历，null的输出
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> nodeList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode == null) {
                nodeList.add("null");
            } else {
                nodeList.add(String.valueOf(currentNode.val));
                queue.offer(currentNode.left);
                queue.offer(currentNode.right);
            }
        }
        return nodeList.stream().collect(Collectors.joining(","));
    }

    // Decodes your encoded data to tree.

    /**
     * 层次遍历
     * 每次从数组里面拿到一个元素，（数组后面两个就是他的左右孩子）
     * 把它放到二叉树的一个位置
     * 并且把新加入的节点放入到队列里面
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        String[] nodeItems = data.split(",");
        if ("null".equals(nodeItems[0])) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodeItems[0]));
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodeItems.length) {
            TreeNode currentNode = queue.poll();
            if (i < nodeItems.length && !"null".equals(nodeItems[i])) {
                currentNode.left = new TreeNode(Integer.parseInt(nodeItems[i]));
                queue.offer(currentNode.left);
            }
            i++;
            if (i < nodeItems.length && !"null".equals(nodeItems[i])) {
                currentNode.right = new TreeNode(Integer.parseInt(nodeItems[i]));
                queue.offer(currentNode.right);
            }
            i++;
        }
        return root;
    }

}
