package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/18 21:28
 * @Description
 * https://leetcode.cn/problems/first-missing-positive/description/
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 要求：时间复杂度O(n) 并且只使用常数级别额外空间的解决方案
 */
public class Lc41 {
    public static void main(String[] args) {
        Lc41 lc41 = new Lc41();
        int[] nums = new int[] {1,2};
        int re = lc41.firstMissingPositive(nums);
        System.out.println(re);
    }

    /**
     * 原地哈希
     * 思路：如果是一个符合条件的没有缺失正数，a[i] = i + 1
     * 进行遍历数组，将数字调整为 a[i] = i + 1的状态
     * 在遍历数组，不满足 a[i] = i + 1 的位置就是缺失的数
     *
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 1;
        }
        // 把i个位置放到数i+1
        for (int i = 0; i < nums.length; ) {
            // 处理第一个位置的数
            int curNum = nums[i];
            // 当前的数应该放到数组的curNum-1位置上
            if (curNum <= 0) {
                // 负数不需要处理
                i++;
                continue;
            }
            if (curNum  > nums.length) {
                // 超过边界的也不需要放
                i++;
                continue;
            }
            // 如果原位置已经是正确的数了就不需要在变了了
            if (nums[curNum - 1] == curNum) {
                i++;
                continue;
            }
            nums[i] = nums[curNum - 1];
            nums[curNum - 1] = curNum;
        }
        // 如果某一个位置没有对应的数，就返回
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 说明整个数组都满足了
        return nums.length + 1;
    }
}
