package com.java.study.algorithm.microsoft.m202404;

/**
 * @Author： yijun
 * @DATE: 2024/4/6 09:34
 * @Description
 * https://leetcode.cn/problems/sqrtx/description/
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 */
public class Lc69 {
    public static void main(String[] args) {
        Lc69 lc69 = new Lc69();
        int[] nums = new int[] {1, 2, 3, 4, 5, 8, 9, 16, 20, 63,64,65};
        for (int i = 0; i< nums.length; i++) {
            int re = lc69.mySqrt(nums[i]);
            System.out.println(nums[i] + "d===" + re);
        }
        int re = lc69.mySqrt(16);
        //System.out.println(nums[i] + "d===" + re);

    }

    /**
     * 二分法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <=  0) {
            return 0;
        }
        if (x == 1) {
            return x;
        }
        int left = 0;
        int right = x;
        int ans = 0;
        // [] 左闭有闭区间
        while (true) {
            if (left > right) {
                break;
            }
            int mid = left + (right - left) / 2;
            if ((long) mid * mid == x) {
                ans = mid;
                break;
            } else if ((long) mid * mid > x) {
                right = mid - 1;
            } else {
                // mid有可能是这个数
                ans = mid;
                left = mid + 1;
            }

        }
        return ans;
    }
}
