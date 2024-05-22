package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.Pair;
import com.java.study.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author： yijun
 * @DATE: 2024/5/4 10:51
 * @Description
 * https://leetcode.cn/problems/top-k-frequent-elements/description/
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class Lc347 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        Lc347 lc347 = new Lc347();
        int[] re = lc347.topKFrequent(nums, 2);
        System.out.println(JsonUtil.toJson(re));

    }

    /**
     * 哈希映射计数+k个元素的小顶堆
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 统计每个数的次数
        HashMap<Integer, Integer> numCountMaps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numCountMaps.containsKey(nums[i])) {
                numCountMaps.put(nums[i], numCountMaps.get(nums[i]) + 1);
            } else {
                numCountMaps.put(nums[i], 1);
            }
        }
        // 小顶堆排序
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(k,
                (a, b) -> a.getSecond() - b.getSecond());
        for (Map.Entry<Integer, Integer> entry : numCountMaps.entrySet()) {
            int count = entry.getValue();
            if (minHeap.size() < k) {
                minHeap.add(new Pair<>(entry.getKey(), entry.getValue()));
            } else {
                Pair<Integer, Integer> peek = minHeap.peek();
                if (count <= peek.getSecond()) {
                    continue;
                } else {
                    minHeap.poll();
                    minHeap.add(new Pair<>(entry.getKey(), entry.getValue()));
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getFirst();
        }
        return result;
    }
}
