package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/3/5 21:44
 * @Description
 * https://leetcode.cn/problems/3sum/description/
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。

 * 你返回所有和为 0 且不重复的三元组。
 */
public class Lc15 {
    public static void main(String[] args) {
        Lc15 lc15 = new Lc15();
        int[] nums = new int[] {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = lc15.threeSum(nums);
        System.out.println(JsonUtil.toJson(lists));
    }

    /**
     * 排序+双指针
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 需要满足三个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 剪枝优化
            if (nums[i] > 0) {
                break;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[j]);
                    item.add(nums[k]);
                    result.add(item);
                    // 找到一个的时候开始去重（去重的关键）
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}
