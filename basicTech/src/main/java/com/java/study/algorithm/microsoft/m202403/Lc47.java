package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/3/21 22:00
 * @Description
 * https://leetcode.cn/problems/permutations-ii/
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 */
public class Lc47 {
    public static void main(String[] args) {
        Lc47 lc47 = new Lc47();
        int[] nums = new int[] {1, 1, 2};
        List<List<Integer>> result = lc47.permuteUnique(nums);
        System.out.println(JsonUtil.toJson(result));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return new ArrayList<>();
        }
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            visited[i] = false;
        }
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), nums, visited);
        return result;
    }
    public void backTrack(List<List<Integer>> result, List<Integer> path,
                          int[] nums,
                          boolean[] visited) {
        // 递归结束
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 每一层树枝可以取的值
        Set<Integer> curLevelHasNode = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 访问过的数据不要了
            if (visited[i] == true) {
                continue;
            }
            // 当前层已经处理过的数据不要，去除重复
            if (curLevelHasNode.contains(nums[i])) {
                continue;
            }
            curLevelHasNode.add(nums[i]);
            visited[i] = true;
            path.add(nums[i]);
            backTrack(result, path, nums, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
