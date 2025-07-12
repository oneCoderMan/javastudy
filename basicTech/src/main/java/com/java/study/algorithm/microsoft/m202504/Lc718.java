package com.java.study.algorithm.microsoft.m202504;

/**
 * @Author： yijun
 * @DATE: 2025/7/12 15:57
 * @Description
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class Lc718 {
    public static void main(String[] args) {
//        int[] nums = new int[] {0,1,1,1,1};
//        int[] num2 = new int[] {1,0,1,0,1};
        int[] nums = new int[] {1,2,3,2,1};
        int[] num2 = new int[] {3,2,1,4,7};
        Lc718 lc718 = new Lc718();
        int length = lc718.findLength(nums, num2);
        System.out.println(length);

    }

    /**
     * 动态规划
     * dp[i][j] 表示 i结尾，j结尾的 两个数组最长公共子数组长度
     * a[i] == b[j] => dp[i][j] = dp[i-1][j-1] + 1
     * a[i] != b[j] => dp[i][j] = 0;
     *  可以使用滚动数组优化
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0;
        }
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp = new int[n1][];
        for (int i = 0; i < n1; i ++) {
            dp[i] = new int[n2];
        }
        int maxResult = 0;
        for (int j = 0; j < n2; j++) {
            if (nums1[0] == nums2[j]) {
                dp[0][j] = 1;
                maxResult = Math.max(maxResult, dp[0][j]);
            } else {
                dp[0][j] = 0;
            }
        }
        for (int i = 0; i < n1; i++) {
            if(nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                maxResult = Math.max(maxResult, dp[i][0]);
            } else {
                dp[i][0] = 0;
            }
        }
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                maxResult = Math.max(maxResult, dp[i][j]);
            }
        }
        return maxResult;
    }
}
