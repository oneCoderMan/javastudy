package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/3/23 10:07
 * @Description
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 */
public class Lc34 {
    public static void main(String[] args) {
        Lc34 lc34 = new Lc34();
        int[] nums = new int[] {5,7,7,8,8,10};
        int[] re = lc34.searchRange(nums, 7);
        System.out.println(JsonUtil.toJson(re));
    }

    /**
     * 两次二分法，分别找到最左和最右的值
     * 找最左的位置是需要判断
     *
     */
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = midSearchForLeft(nums, target);
        if (leftIndex == -1) {
            return new int[] {-1, -1};
        }
        int rightIndex = midSearchForRight(nums, target);
        return new int[] {leftIndex, rightIndex};
    }

    /**
     * 左闭右开区间
     */
    private int midSearchForLeft(int[] nums, int target) {
        if (nums == null || nums.length < 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 找到了一个
            if (nums[mid] == target) {
                // 最左也算找到
                if (mid == 0) {
                    return 0;
                }
                if (nums[mid] != nums[ mid - 1]) {
                    return mid;
                }
                // 往左移动，不能是mid-1，因为是右开区间
                right = mid;
            } else if (nums[mid] > target) {
                // 不能是mid-1，因为是右开区间
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int midSearchForRight(int[] nums, int target) {
        if (nums == null || nums.length < 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 找到了一个
            if (nums[mid] == target) {
                // 最右也算找到
                if (mid == nums.length - 1) {
                    return mid;
                }
                if (nums[mid] != nums[ mid + 1]) {
                    return mid;
                }
                // 往右移动
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

}
