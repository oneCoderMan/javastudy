package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/3/20 21:59
 * @Description
 * https://leetcode.cn/problems/combination-sum/
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合
 * candidates 中的 同一个 数字可以 无限制重复被选取
 */
public class Lc39 {
    public static void main(String[] args) {
        Lc39 lc39 = new Lc39();
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        List<List<Integer>> re = lc39.combinationSum(candidates, target);
        System.out.println(JsonUtil.toJson(re));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backTrack(result, path, candidates, target, 0, 0);
        return result;
    }

    /**
     * 使用回溯，画一个图就出来
     * 每次从当前candidates的下标出发，因为可以重复，所以
     */
    private void backTrack(List<List<Integer>> result,
                           List<Integer> path,
                           int[] candidates,
                           int target,
                           int sum,
                           int startIndex) {
        // 减枝
        if (target < sum) {
            return;
        }
        if (target == sum) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            int curSum = sum + candidates[i];
            if (curSum > target) {
                continue;
            }
            path.add(candidates[i]);
            // 因为可以重复取值，所以startIndex还是i
            backTrack(result, path, candidates, target, curSum, i);
            path.remove(path.size() - 1);
        }
    }

}
