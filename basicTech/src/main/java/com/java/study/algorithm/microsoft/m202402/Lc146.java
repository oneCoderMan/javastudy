package com.java.study.algorithm.microsoft.m202402;

import java.util.HashMap;

/**
 * @Author： yijun
 * @DATE: 2024/2/5 22:05
 * @Description
 * https://leetcode.cn/problems/lru-cache/description/
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
 * 如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class Lc146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        LRUCache.LinkedNode head1 = lruCache.getHead();

        System.out.println(lruCache.get(2));
        LRUCache.LinkedNode head2 = lruCache.getHead();

        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        LRUCache.LinkedNode head = lruCache.getHead();
        System.out.println(lruCache.get(2));
       // System.out.println(lruCache.get(4));
        // obj.put(key,value);
        // ["LRUCache","put","put","get","put","get","get"]
        //[[2], [2,1], [1,1], [2], [4,1], [1],[2]]

    }
}

class LRUCache {
    private LinkedNode head;
    private LinkedNode tail;
    private HashMap<Integer, LinkedNode> maps;
    private Integer maxCount;

    public LinkedNode getHead() {
        return head;
    }


    public LRUCache(int capacity) {
        maps = new HashMap<>(capacity);
        maxCount = capacity;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 删除某一个节点
     * @param node
     */
    public void deleteNode(LinkedNode node) {
        if (node == null) {
            return;
        }
        if (node == head || node == tail) {
            return;
        }
        LinkedNode pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;
        node.next = null;
        node.pre = null;
    }

    /**
     * 添加到头
     * @param node
     */
    public void addToHead(LinkedNode node) {
        if (node == null) {
            return;
        }
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    /**
     * 移动到头
     * @param node
     */
    private void moveToHead(LinkedNode node) {
        if (node == null) {
            return;
        }
        if (node.pre == head) {
            return;
        }
        LinkedNode newNode = new LinkedNode();
        newNode.value = node.value;
        newNode.key = node.key;
        deleteNode(node);
        addToHead(newNode);
        maps.put(newNode.key, newNode);
    }

    public int get(int key) {
        LinkedNode curNode = maps.get(key);
        if (curNode == null) {
            return -1;
        }
        moveToHead(curNode);
        return curNode.value;
    }

    public void put(int key, int value) {
        LinkedNode curNode = maps.get(key);
        if (curNode == null) {
            // 有新节点变化的时候需要更新maps
            LinkedNode newNode = new LinkedNode();
            newNode.value = value;
            newNode.key = key;
            addToHead(newNode);
            maps.put(key, newNode);
            if (maps.size() > maxCount) {
                LinkedNode tailNode = tail.pre;
                deleteNode(tailNode);
                maps.remove(tailNode.key);
            }
            return;
        }
        // 元素存在， 更新数据并且移动到队首
        curNode.value = value;
        moveToHead(curNode);
    }

    class LinkedNode {
        public LinkedNode pre;
        public LinkedNode next;
        public Integer value;
        public Integer key;
    }
}
