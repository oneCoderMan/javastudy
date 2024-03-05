package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/3 10:05
 * @Description
 * 字符串转换整数 (atoi)
 * https://leetcode.cn/problems/string-to-integer-atoi/description/
 */
public class Lc08 {
    int ans = 0;
    int flag = 1;
    public static void main(String[] args) {
        Lc08 lc08 = new Lc08();

//        int i = lc08.myAtoi("4193 with words");
//        int i = lc08.myAtoi("   -42");
        int i = lc08.myAtoi("-0012a42");
        System.out.println(i);
    }

    /**
     * 有穷自动机
     * 溢出处理 （分正数和负数）
     * 正数：(Interger.maxValue - increment) / 10 < ans
     * 负数：(Integer.MIN_VALUE + increment) / 10 > (ans * -1)
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null || s == " ") {
            return 0;
        }
        // 0-start, 1-number, 2-signed 3-end
        int state = 0;
        ans = 0;
        flag = 1;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (state == 0) {
                state = dealInStart(currentChar);
                // 判断四个状态
            } else if (state == 1) {
                state = dealInNumber(currentChar);

            } else if (state == 2) {
                state = dealInSigned(currentChar);
            } else if (state == 3) {
                break;
            }
        }
        return ans * flag;
    }

    private int dealInSigned(char currentChar) {
        if (isNumber(currentChar)) {
            int increment = currentChar - '0';
            // 溢出了
            if (flag == 1 && judgePositiveOverFlow(increment)) {
                ans = Integer.MAX_VALUE;
                return 3;
            }
            if (flag == -1 && judgeNegativeOverFlow(increment)) {
                ans = Integer.MIN_VALUE;
                return 3;
            }
            ans = ans * 10 + increment;
            return 1;
        }
        // 其它符号要结束
        return 3;
    }

    private int dealInNumber(char currentChar) {
        if (isNumber(currentChar)) {
            int increment = currentChar - '0';
            // 溢出了
            if (flag == 1 && judgePositiveOverFlow(increment)) {
                ans = Integer.MAX_VALUE;
                return 3;
            }
            if (flag == -1 && judgeNegativeOverFlow(increment)) {
                ans = Integer.MIN_VALUE;
                return 3;
            }
            ans = ans * 10 + increment;
            return 1;
        }
        // 其它符号要结束
        return 3;
    }

    private int dealInStart(char currentChar) {
        if (isNumber(currentChar)) {
            int increment = currentChar - '0';
            // 溢出了
            if (flag == 1 && judgePositiveOverFlow(increment)) {
                ans = Integer.MAX_VALUE;
                return 3;
            }
            if (flag == -1 && judgeNegativeOverFlow(increment)) {
                ans = Integer.MIN_VALUE;
                return 3;
            }
            ans = ans * 10 + increment;
            return 1;
        }
        if (currentChar == ' ') {
            return 0;
        }
        if (currentChar == '+') {
            return 1;
        }
        if (currentChar == '-') {
            flag = -1;
            return 1;
        }
        return 3;
    }

    /**
     * false -没有溢出
     * @param increment
     * @return
     */
    private boolean judgePositiveOverFlow(int increment) {
        if ((Integer.MAX_VALUE - increment) / 10 < ans) {
            return true;
        }
        return false;
    }

    private boolean judgeNegativeOverFlow(int increment) {
        if ((Integer.MIN_VALUE + increment) / 10 > (ans * -1)) {
            return true;
        }
        return false;
    }

    private boolean isNumber(char currentChar) {
        if (currentChar >= '0' && currentChar <= '9') {
            return true;
        }
        return false;
    }

}
