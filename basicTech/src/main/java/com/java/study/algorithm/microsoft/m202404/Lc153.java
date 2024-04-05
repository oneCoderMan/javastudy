package com.java.study.algorithm.microsoft.m202404;

/**
 * @Author： yijun
 * @DATE: 2024/4/1 22:42
 * @Description
 *
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组
 * 数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 */
public class Lc153 {
    public static void main(String[] args) {
        Lc153 lc153 = new Lc153();

//        int[] nums = {3,4,5,1,2};
        int[] nums = {4,5,6,7,0,1,2};
        int min = lc153.findMin(nums);
        System.out.println(min);

    }

    /**
     * 二分成两步分
     * 如果有一部分可以判断出是有序的，直接拿到第一个元素
     * 另一部分没有顺序，就一直二分
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        return doFindMin(nums, 0, nums.length);
    }

    /**
     * 左开右闭区间
     * @return
     */
    private int doFindMin(int[] nums, int left, int right) {
        if ((right - left) == 1) {
            return nums[left];
        }
        if (right == left) {
            return Integer.MIN_VALUE;
        }
        // 说明这边有序，直接返回
        if (nums[right - 1] > nums[left]) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int minLeft = doFindMin(nums, left, mid);
        int minRight = doFindMin(nums, mid, right);
        return Math.min(minLeft, minRight);
    }
}
