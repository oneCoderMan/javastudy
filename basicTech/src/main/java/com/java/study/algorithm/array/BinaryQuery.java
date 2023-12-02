package com.java.study.algorithm.array;

/**
 * @Author： yijun
 * @DATE: 2023/12/2 10:13
 * @Description
 */
public class BinaryQuery {

    public static void main(String[] args) {
        int[] test = new int[]{-1,0,3,5,9,12};
        BinaryQuery binaryQuery = new BinaryQuery();
        int search = binaryQuery.search(test, 9);
        System.out.println(search);

    }

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * @param nums
     * @param target
     * @return
     */
    int search(int[] nums, int target) {
        // 在[a,b)里面搜索值
        int a = 0;
        int b = nums.length;
        // [1,1)这样一个半开半闭的时候，表示空数据
        while (a < b) {
            // 这里注意可能会溢出
            int mid = (a + b) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                a = mid + 1;
            } else if (nums[mid] > target) {
                b = mid;
            }
        }
        return -1;
    }

}
