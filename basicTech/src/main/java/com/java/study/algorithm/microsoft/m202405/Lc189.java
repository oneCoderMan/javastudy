package com.java.study.algorithm.microsoft.m202405;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/5/30 23:16
 * @Description
 * https://leetcode.cn/problems/rotate-array/description/
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 这个题比较特殊
 */
public class Lc189 {
    public static void main(String[] args) {
        Lc189 lc189 = new Lc189();
        int[] nums = new int[] {-1,-100,3,99};
        lc189.rotate(nums, 2);
        System.out.println(JsonUtil.toJson(nums));

    }

    /**
     * 【-1,-100,3,99 】 k = 2的时候会循环，所以需要两层循环控制
     * @param nums
     * @param k
     * https://leetcode.cn/problems/rotate-array/solutions/242188/xuan-zhuan-shu-zu-yuan-di-huan-wei-xiang-xi-tu-jie
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k % n == 0) {
            return;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int curIndex = i;
            int targetIndex;
            int curValue = nums[curIndex];
            int nextValue;
            while (true) {
                targetIndex = (curIndex + k ) % n;
                nextValue = nums[targetIndex];
                nums[targetIndex] = curValue;
                count++;
                if (count == n) {
                    return;
                }
                // 更新
                curIndex = targetIndex;
                curValue = nextValue;
                if (curIndex == i) {
                    break;
                }
            }
        }

    }
}
