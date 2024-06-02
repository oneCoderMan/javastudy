package com.java.study.algorithm.microsoft.m202406;

import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author： yijun
 * @DATE: 2024/6/2 15:02
 * @Description
 * https://leetcode.cn/problems/shuffle-an-array/description/
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 *
 * 实现 Solution class:
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 */
public class Lc384 {
    public static void main(String[] args) {
        Lc384 lc384 = new Lc384();
        lc384.test();

    }

    public void test() {
        int[] nums = new int[] {1,2,3};
        Solution solution = new Solution(nums);
        solution.reset();
        int[] shuffle = solution.shuffle2();
        System.out.println(JsonUtil.toJson(shuffle));

    }
    class Solution {
        private int[] rawNums;
        private int[] shuffle;

        public Solution(int[] nums) {
            rawNums = nums;
            shuffle = new int[nums.length];
        }

        public int[] reset() {
            for (int i = 0; i < rawNums.length; i++) {
                shuffle[i] = rawNums[i];
            }
            return shuffle;
        }

        /**
         * 暴力算法，随机获取到一个元素设置，这是等概率的
         * @return
         */
        public int[] shuffle() {
            // 优化，改进，原地移动数组，移除 waiting 的第 k 个元素时，
            // 将第 k 个元素与数组的最后 1 个元素交换，然后移除交换后数组的最后 1 个元素，
            // 这样我们只需要 O(1) 时间复杂度即可完成移除第 k个元素的操作。
            // 此时，被移除的交换后数组的最后 1 个元素即为我们根据随机下标获取的元素。
            //或者 我们从前往后尝试填充 [0,n−1][0, n - 1][0,n−1] 该填入什么数时，通过随机当前下标与（剩余的）哪个下标进行值交换来实现。
            List<Integer> waiting = new ArrayList<>();
            for (int i = 0; i < rawNums.length; i++) {
                waiting.add(rawNums[i]);
            }
            Random random = new Random();
            for (int i = 0; i < rawNums.length; i++) {
                int randIndex = random.nextInt(waiting.size());
                shuffle[i] = waiting.get(randIndex);
                waiting.remove(randIndex);
            }
            return shuffle;
        }

        /**
         * 原地获交换值
         * @return
         */
        public int[] shuffle2() {
            for (int i = 0; i < rawNums.length; i++) {
                shuffle[i] = rawNums[i];
            }
            Random random = new Random();
            for (int i = shuffle.length - 1; i >= 0; i--) {
                int randIndex = random.nextInt(i + 1);
                int temp = shuffle[i];
                shuffle[i] = shuffle[randIndex];
                shuffle[randIndex] = temp;
            }
            return shuffle;
        }

    }
}
