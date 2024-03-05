package com.java.study.algorithm.microsoft.m202402;

/**
 * @Author： yijun
 * @DATE: 2024/2/26 21:57
 * @Description
 */
public class Lc4 {
    public static void main(String[] args) {
        Lc4 lc4 = new Lc4();
        int[] nums1 = new int[] {2, 3};
        int[] nums2 = new int[] {};

        int test = lc4.findKminNumber(nums1, 0, nums1.length, nums2, 0, nums2.length, 2);
        System.out.println(test);

        //double medianSortedArrays = lc4.findMedianSortedArrays(nums1, nums2);
        // System.out.println(medianSortedArrays);
    }

    /**
     * 先实现一个找第k小数的办法:
     * 假设我们取两个数组k/2位置上的数(这里暂时不考虑上溢)
     * 如果nums1[k/2]>=nums2[k/2]，这意味着：
     * nums2数组的左半边都不需要考虑了，因为肯定会比第k小的数要来得小。
     * 所以我们可以切掉nums2数组的一半，如此递归，每次都能切走一半
     *
     * 我们可以令nums1始终是长的那个数组，
     *  @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }
        if (nums1 == null) {
            nums1 = nums2;
        }
        if (nums2 == null) {
            nums2 = nums1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 < len2) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        if (((len1 + len2) % 2) == 0 ) {
            int k1minNumber = findKminNumber(nums1, 0, nums1.length, nums2, 0, nums2.length, (len1 + len2) / 2);
            int k2minNumber = findKminNumber(nums1, 0, nums1.length, nums2, 0, nums2.length, (len1 + len2) / 2 + 1);
            System.out.println(k1minNumber + " " + k2minNumber);
            return (k1minNumber + k2minNumber) * 1.0 / 2.0;
        } else {
            return 1.0 * findKminNumber(nums1, 0, nums1.length, nums2, 0, nums2.length, (len1 + len2) / 2 + 1);
        }
    }

    /**
     *
     * @param nums1 有效区间是[l1, r1), 长度是r1-l1
     * @param nums2 有效区间是[l2, r2), 长度是r2-l2
     * 需要满足r1-l1 >= r2-l2
     * 在考虑边界情况
     * @return
     */
    public int findKminNumber(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k) {
        int num1Len = r1 - l1;
        int num2Len = r2 - l2;
        if (num1Len < num2Len) {
            throw new RuntimeException("error...");
        }
        if (num2Len <= 0) {
            return nums1[l1 + k - 1];
        }
        if (k < 0) {
            return 0;
        }
        // 边界情况
        if (k == 1) {
            if (num2Len > 0) {
                return Math.min(nums1[l1], nums2[l2]);
            }
            return nums1[l1];
        }
        int t = k / 2;
        // 处理数组越界的情况
        int t2 = Math.min(num2Len, t);
        // 选择淘汰其中的一半
        int mid1 = nums1[l1 + t - 1];
        int mid2 = nums2[l2 + t2 - 1];
        // nums2的左半部分直接淘汰
        if (mid1 >= mid2) {
            return findKminNumber(nums1, l1, r1, nums2, l2 + t2, r2, k - t2);
        }
        // num1的左半部分需要淘汰，需要重新比较数组大小
        int lenOfNum1 = num1Len - t;
        if (lenOfNum1 >= num2Len) {
            return findKminNumber(nums1, l1 + t, r1, nums2, l2, r2, k - t);
        } else {
            return findKminNumber(nums2, l2, r2, nums1, l1 + t, r1, k - t);
        }
    }
}
