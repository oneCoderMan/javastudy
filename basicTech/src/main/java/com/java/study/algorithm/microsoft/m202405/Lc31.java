package com.java.study.algorithm.microsoft.m202405;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/5/5 11:04
 * @Description
 * https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class Lc31 {
    public static void main(String[] args) {
        Lc31 lc31 = new Lc31();
        int[] nums = new int[] {2, 1, 3};
        lc31.nextPermutation(nums);
        System.out.println(JsonUtil.toJson(nums));

//        int[] nums2 = new int[] {1,1,5};
//        lc31.nextPermutation(nums);
//        System.out.println(JsonUtil.toJson(nums));
    }

    /**
     * 从右到左找到一个升序序列 a1, a2, ...ak-1,  ak, ... an
     * 其中ak 到 an 是降序的
     *  从 ak 到 an中选一个刚好比ak-1大的数 的替换到 ak-1,
     *  然后重排序ak 到 an
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int k = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;
            }
        }
        if (k < 0) {
            traverse(nums, 0, nums.length - 1);
            return;
        }
        int temp = nums[k];
        // 找到一个刚好比temp大的数
        for (int i = nums.length - 1; i > k; i--) {
            if (nums[i] > nums[k]) {
                nums[k] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        // 翻转 k+1 - n
        traverse(nums, k + 1, nums.length - 1);
    }

    private void traverse(int[] nums, int l, int r) {
        int left = l;
        int right = r;
        int temp;
        while (left < right) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
