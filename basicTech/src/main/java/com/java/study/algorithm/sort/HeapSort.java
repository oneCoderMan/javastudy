package com.java.study.algorithm.sort;

import java.util.Arrays;

/**
 * @Author： yijun
 * @DATE: 2023/12/21 22:22
 * @Description
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] x = new int[]{7,1,3,10,5,2,8,9,6};
        buildSmallHeap(x, 8);
        System.out.println(Arrays.toString(x));

    }

    /**
     * lastIndex之前是一个完全构建好的二叉堆，上浮操作
     * 这里以小顶堆为例
     * @param nums 原二叉堆
     * @param lastIndex 最后一个元素位置
     */
    public static void upAdjust(int[] nums, int lastIndex) {
        int currentIndex = lastIndex;
        int parentIndex = (currentIndex - 1) / 2;
        while (true) {
            if (parentIndex <= 0) {
                break;
            }
            if (nums[parentIndex] > nums[currentIndex]) {
                // 交换两个数据
                int t = nums[currentIndex];
                nums[currentIndex] = nums[parentIndex];
                nums[parentIndex] = t;
            } else {
                break;
            }
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    /**
     * 以小顶堆为例
     * @param nums 待调整的堆
     * @param nodeIndex 当前要下沉的节点
     * @param lastIndex 堆的最后一个节点下标
     */
    public static void downAdjust(int[] nums, int nodeIndex, int lastIndex) {
        int currentIndex = nodeIndex;
        int leftIndex = currentIndex * 2 + 1;
        int rightIndex = currentIndex * 2 + 2;
        while (true) {
            if (leftIndex > lastIndex) {
                break;
            }
            // 选一个比较小的左右节点
            int smallIndex = rightIndex > lastIndex
                    ? leftIndex
                    : (nums[leftIndex] <= nums[rightIndex] ? leftIndex : rightIndex);
            // 无法继续下沉的情况
            if (nums[currentIndex] <= nums[smallIndex]) {
                break;
            }
            // 交换节点
            int t = nums[currentIndex];
            nums[currentIndex] = nums[smallIndex];
            nums[smallIndex] = t;

            currentIndex = smallIndex;
            leftIndex = currentIndex * 2 + 1;
            rightIndex = currentIndex * 2 + 2;
        }
    }

    /**
     * 构建堆
     * @param array
     * @param lastIndex 最后一个节点的下标
     */
    public static void buildSmallHeap(int[] array, int lastIndex) {
        // 最后一个非叶子节点依次下沉
        int lastNonLeafIndex = (lastIndex - 1) / 2;
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            downAdjust(array, i, lastIndex);
        }
    }
}
