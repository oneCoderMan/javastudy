package com.java.study.algorithm.microsoft.m202405;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/5/3 22:59
 * @Description
 * https://leetcode.cn/problems/move-zeroes/description/
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class Lc283 {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 0, 0, 2,6};
        Lc283 lc283 = new Lc283();
        lc283.moveZeroes(nums);
        System.out.println(JsonUtil.toJson(nums));
    }
    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int finishIndex = 0;
        int cur = 0;
        while (cur < nums.length) {
            if (nums[cur] != 0) {
                nums[finishIndex] = nums[cur];
                finishIndex++;
            }
            cur++;
        }
        for (; finishIndex < nums.length; finishIndex++) {
            nums[finishIndex] = 0;
        }
    }

//    public void moveZeroes(int[] nums) {
//        if (nums == null) {
//            return;
//        }
//        int zeroIndex = -1;
//        int cur = 0;
//        while (cur < nums.length) {
//            // 找到当前的0的位置
//            for (int i = cur; i < nums.length; i++) {
//                if (nums[i] == 0) {
//                    zeroIndex = i;
//                    break;
//                }
//            }
//            if (zeroIndex >= nums.length - 1 || zeroIndex < 0) {
//                break;
//            }
//            // 找到当前的第一个非0
//            for (cur = zeroIndex; cur < nums.length; cur++) {
//                if (nums[cur] != 0) {
//                    break;
//                }
//            }
//            if (cur >= nums.length) {
//                break;
//            }
//            nums[zeroIndex] = nums[cur];
//            nums[cur] = 0;
//            cur = zeroIndex + 1;
//        }
//
//    }
}
