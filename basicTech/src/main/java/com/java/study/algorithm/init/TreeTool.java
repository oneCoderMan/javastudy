package com.java.study.algorithm.init;

import java.util.ArrayDeque;

/**
 * @Author： yijun
 * @DATE: 2024/1/7 10:23
 * @Description
 */
public class TreeTool {
    public static TreeNode createTree(Integer[] arr) {
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<TreeNode> pre = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {

            ArrayDeque<TreeNode> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                TreeNode node = pre.removeFirst();
                TreeNode left=null;
                TreeNode right=null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index<arr.length&&arr[index]!=null){
                    left=new TreeNode(arr[index]);
                    cur.addLast(left);
                }
                if (++index<arr.length&&arr[index]!=null){
                    right=new TreeNode(arr[index]);
                    cur.addLast(right);
                }
                node.left=left;
                node.right=right;
            }
            pre=cur;
        }


        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {3,5,1,6,2,0,8,null,null,7,4};
//        Integer[] arr={5,4};
        TreeNode tree = createTree(arr);
        prePrintTree(tree);

    }

    private static void prePrintTree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        prePrintTree(node.left);
        prePrintTree(node.right);
    }
}
