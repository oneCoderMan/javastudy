package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/20 22:21
 * @Description
 * https://leetcode.cn/problems/excel-sheet-column-title/
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * A -> 1
 * B -> 2
 * C -> 3
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */
public class Lc168 {
    public static void main(String[] args) {
        Lc168 lc168 = new Lc168();
        System.out.println(lc168.convertToTitle(27));
    }

    /**
     * 需要注意没有基数0的时候的处理
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        if (columnNumber < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int modNum;
        int divNum;
        int curNum = columnNumber;
        while (true) {
            // 注意，26进制的基数是（0-25）
            curNum = curNum - 1;
            modNum = curNum % 26;
            divNum = curNum / 26;
            sb.append((char) ('A' + modNum));
            if (divNum == 0) {
                break;
            }
            curNum = divNum;
        }
        return sb.reverse().toString();
    }

}
