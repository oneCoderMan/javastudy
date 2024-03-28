package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.Node;

import java.util.HashMap;

/**
 * @Author： yijun
 * @DATE: 2024/3/28 21:16
 * @Description
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝
 */
public class Lc138 {
    public HashMap<Node, Node> cacheNode = new HashMap<>();
    public static void main(String[] args) {
        Lc138 lc138 = new Lc138();

    }
    public Node copyRandomList(Node head) {
        cacheNode = new HashMap<>();
        return doCopyRandomList(head);
    }

    /**
     * 方法一：递归拷贝 + 缓存
     * @param head
     * @return
     */
    public Node doCopyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cacheNode.containsKey(head)) {
            // 完成当前节点的拷贝
            Node newNode = new Node(head.val);
            cacheNode.put(head, newNode);
            newNode.next = doCopyRandomList(head.next);
            newNode.random = doCopyRandomList(head.random);

        }
        return cacheNode.get(head);
    }

}
