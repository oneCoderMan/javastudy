package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/3/5 22:48
 * @Description
 * https://leetcode.cn/problems/permutations/description/
 * 46. 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Lc46 {
    public static void main(String[] args) {
        Lc46 lc46 = new Lc46();
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> permute = lc46.permute(nums);
        System.out.println(JsonUtil.toJson(permute));

    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        int[] visited = new int[nums.length];
        Arrays.fill(visited, 0);
        backTrack(result, new ArrayList<>(), nums, visited);
        return result;
    }

    /**
     * 这是一棵树，使用回溯算法
     * 把树画出来就清晰了
     * 关键是每一层可以遍历的元素通过visited标识
     * @param result
     * @param currentPath
     * @param nums
     * @param visited
     */
    public void backTrack(List<List<Integer>> result,
                          List<Integer> currentPath,
                          int[] nums,
                          int[] visited) {
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            currentPath.add(nums[i]);
            visited[i] = 1;
            backTrack(result, currentPath, nums, visited);
            visited[i] = 0;
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
