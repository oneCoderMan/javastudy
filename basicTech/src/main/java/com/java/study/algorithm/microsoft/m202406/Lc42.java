package com.java.study.algorithm.microsoft.m202406;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author： yijun
 * @DATE: 2024/6/8 09:59
 * @Description
 * https://leetcode.cn/problems/trapping-rain-water/description/
 */
public class Lc42 {
    public static void main(String[] args) {
        Lc42 lc42 = new Lc42();
        int[] nums = new int[] { 4,2,0,3,2,5};
        int trap = lc42.trap(nums);
        System.out.println(trap);
        int trap2 = lc42.trap2(nums);
        System.out.println(trap2);
    }

    /**
     * 按列计算面积
     *  计算出每一列向左向右可以有的最大高度，
     *  减去当前列的高度就是能装水的高度
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n = height.length;
        int[] maxRight = new int[n];
        // 计算每个元素右边的最大值
        calcuMaxRight(height, maxRight, n);
        // 计算每个元素左边的最大值
        int[] maxLeft = new int[n];
        calcuMaxLeft(height, maxLeft, n);
        // 计算雨水的最大面积
        int area = 0;
        for (int i = 0; i < n; i++) {
            int curHeight = Math.min(maxRight[i], maxLeft[i]) - height[i];
            if (curHeight > 0) {
                area += curHeight;
            }
        }
        return area;
    }

    private void calcuMaxLeft(int[] nums, int[] dp, int n) {
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1]);
        }
    }

    private void calcuMaxRight(int[] nums, int[] dp, int n) {
        dp[n-1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], nums[i+1]);
        }
    }

    /**
     * 按照行计算雨水面积
     * 单调栈（栈内元素是递减的，栈内存放下标）
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        // 入栈
//        stack.push(1);
//        stack.pop();
//        stack.peek();
        stack.push(0);
        int totalSize = 0;
        for (int i = 1; i < height.length; i++) {
            int curHeight = height[i];
            int topHeight = height[stack.peek()];
            if (topHeight > curHeight) {
                stack.push(i);
            } else if (topHeight == curHeight) {
                stack.pop();
                stack.push(i);
            } else {
                //出现凹槽，可以开始计算面积，一直到栈顶元素比 curHeight 小
                while (!stack.isEmpty() && height[stack.peek()] < curHeight) {
                    Integer buttonIndex = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    Integer leftIndex = stack.peek();
                    // 当前柱子的高度，
                    int curColumnHeight = Math.min(height[leftIndex], curHeight) - height[buttonIndex];
                    int size = (i - leftIndex - 1) * curColumnHeight;
                    totalSize += size;
                }
                stack.push(i);
            }
        }
        return totalSize;
    }

}
