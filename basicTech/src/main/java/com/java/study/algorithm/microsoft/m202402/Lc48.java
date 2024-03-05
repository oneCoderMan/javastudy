package com.java.study.algorithm.microsoft.m202402;

import com.java.study.algorithm.init.ArrayTool;

/**
 * @Author： yijun
 * @DATE: 2024/2/29 07:33
 * @Description
 * https://leetcode.cn/problems/rotate-image/description/
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Lc48 {
    public static void main(String[] args) {
        Lc48 lc48 = new Lc48();
        // [1,2,3],[4,5,6],[7,8,9]
//         String inputString = "[[1,2,3],[4,5,6],[7,8,9]]";
//         String inputString = "[[1,2],[3,4]]";
        String inputString = "[[5]]";
        int[][] matrix = ArrayTool.strToArray(inputString);
        ArrayTool.printArray(matrix);
        lc48.rotate(matrix);
        System.out.println("====");
        ArrayTool.printArray(matrix);
    }

    /**
     * 一圈一圈的旋转，每次由外向内缩小一圈
     * 法二：先上下翻转，在对角线翻转
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int top = 0;
        int button = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top < button && left < right) {
            // 循环这一圈
            for (int i = 0; i < right - left; i++) {
                // 左上角
                int temp = matrix[top][left + i];
                // 左下 -> 左上
                matrix[top][left + i] = matrix[button - i][left];
                // 右下 -> 左下
                matrix[button - i][left] = matrix[button][right - i];
                // 右上 -> 右下
                matrix[button][right - i] = matrix[top + i][right];
                // 左上 -> 右上
                matrix[top + i][right] = temp;
            }
            top++;
            button--;
            left++;
            right--;
        }

    }
}
