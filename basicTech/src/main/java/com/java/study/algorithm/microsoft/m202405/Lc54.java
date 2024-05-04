package com.java.study.algorithm.microsoft.m202405;

import com.java.study.algorithm.init.ArrayTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/5/3 22:18
 * @Description
 * https://leetcode.cn/problems/spiral-matrix/description/
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class Lc54 {
    public static void main(String[] args) {
        Lc54 lc54 = new Lc54();
        String inputString = "[[1,2,3,4],[5,6,7,8],[9,10,11,12]]";
        int[][] matrix = ArrayTool.strToArray(inputString);
        List<Integer> result = lc54.spiralOrder(matrix);
        System.out.println(JsonUtil.toJson(result));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return new ArrayList<>();
        }
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();
        int i;
        int j;
        while (true) {
            if (top > bottom || left > right) {
                break;
            }
            i = top;
            j = left;
            // 往右走
            for (j = left; j <= right; j++) {
                result.add(matrix[i][j]);
            }
            // 往下走
            j = right;
            for (i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][j]);
            }
            // 往左走,需要控制top== bottom的时候不重复访问
            i = bottom;
            for (j = right - 1; j >= left && top != bottom; j--) {
                result.add(matrix[i][j]);
            }
            // 往上走
            j = left;
            // 这个不能是等于，需要控制刚好走一圈， 需要控right == left 的时候不重复访问
            for (i = bottom - 1; i > top && right != left; i--) {
                result.add(matrix[i][j]);
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }
}
