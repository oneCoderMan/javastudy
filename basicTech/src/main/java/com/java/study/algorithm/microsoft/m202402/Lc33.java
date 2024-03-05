package com.java.study.algorithm.microsoft.m202402;

/**
 * @Author： yijun
 * @DATE: 2024/2/20 21:48
 * @Description
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
 * 则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Lc33 {
    public static void main(String[] args) {
        Lc33 lc33 = new Lc33();
        int[] nums = new int[] {1};
        int search = lc33.search(nums, 0);
        System.out.println(search);

    }

    /**
     * [)区间
     * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [nums[l],nums[mid])，
     * 则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
     *
     * 如果 [mid, r] 是有序数组，且 target 的大小满足 (nums[mid+1],nums[r]]
     * 则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 直接就找到
            if (nums[mid] == target) {
                return mid;
            }
            // 单独判断一下一个元素的
            if (right - left == 1) {
                return -1;
            }
            // 判断左区间哪个有序
            if (nums[left] <= nums[mid - 1]) {
                if (nums[left] <= target && nums[mid - 1] >= target) {
                    // 左区间寻找
                    right = mid;
                } else {
                    // 右区间寻找
                    left = mid + 1;
                }

            } else if (nums[mid + 1] <= nums[right - 1]) {
                if (nums[mid + 1] <= target && nums[right - 1] >= target) {
                    // 右区间寻找
                    left = mid + 1;
                } else {
                    // 左区间寻找
                    right = mid;
                }
            }
        }
        return -1;
    }
}
