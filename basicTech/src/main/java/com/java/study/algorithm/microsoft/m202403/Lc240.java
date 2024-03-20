package com.java.study.algorithm.microsoft.m202403;

import static com.java.study.algorithm.init.ArrayTool.strToArray;

/**
 * @Author： yijun
 * @DATE: 2024/3/20 21:41
 * @Description
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class Lc240 {
    public static void main(String[] args) {
        Lc240 lc240 = new Lc240();
        String inputString = "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24]]";
        int[][] matrix = strToArray(inputString);
        int target = 5;
        boolean re = lc240.searchMatrix(matrix, target);
        System.out.println(re);
    }

    /**
     * 方法：从最右上开始
     * target > 最右上， 当前行可以排除
     * target < 最右上，当前列可以排除
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0) {
            return false;
        }
        if (matrix[0] == null) {
            return false;
        }
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        int curRow = 0;
        int curCol = maxCol - 1;
        // 最右上开始循环
        while (true) {
            if (curRow >= maxRow || curCol < 0) {
                break;
            }
            if (matrix[curRow][curCol] == target) {
                return true;
            }
            // 当前行可以排除
            if (matrix[curRow][curCol] < target) {
                curRow++;
                continue;
            }
            // 当前列可以排除
            if ( matrix[curRow][curCol] > target) {
                curCol--;
            }
        }
        return false;
    }
}
