package com.java.study.algorithm.microsoft.m202406;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/6/1 10:55
 * @Description
 * https://leetcode.cn/problems/merge-sorted-array/description/
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，
 * 另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 */
public class Lc88 {
    public static void main(String[] args) {
        Lc88 lc88 = new Lc88();
        int[] nums1 = new int[] {1,2,3,0,0,0};
        int[] nums2 = new int[] {2,5,6};
        lc88.merge(nums1, 3, nums2, 3);
        System.out.println(JsonUtil.toJson(nums1));
    }

    /**
     * 到序双指针就可以了，把最大的数字放到nums的最后
     *
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // todo 判断参数有效
        int p1 = m - 1;
        int p2 = n - 1;
        int lastIndex = m + n -1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[lastIndex] = nums1[p1];
                p1--;
                lastIndex--;
            } else {
                nums1[lastIndex] = nums2[p2];
                p2--;
                lastIndex--;
            }
        }
        while (p2 >= 0) {
            nums1[lastIndex] = nums2[p2];
            p2--;
            lastIndex--;
        }
    }
}
