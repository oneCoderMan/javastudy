package com.java.study.algorithm.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2023/12/5 22:01
 * @Description
 */
public class ArrayTool {
    public static void main(String[] args) {
        String inputString = "[[1,2],[3],[3],[]]";
        int[][] ints = strToArray(inputString);
        System.out.println(JsonUtil.toJson(ints));

        String charInputStr = "[[\"1\",\"1\",\"1\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"0\",\"0\",\"0\"]]";
        char[][] chats = strToCharArray(charInputStr);
        System.out.println(JsonUtil.toJson(chats));
    }

    /**
     * 字符串转换为二维数组
     * @param inputString "[[1,2],[3],[3],[]]"
     * @return
     */
    public static int[][] strToArray(String inputString) {
        // 使用 Jackson 解析字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputString, int[][].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static char[][] strToCharArray(String inputString) {
        // 使用 Jackson 解析字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputString, char[][].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printArray(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j< matrix[i].length; j ++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
