package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/27 21:58
 * @Description
 * https://leetcode.cn/problems/add-strings/description/
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 */
public class Lc415 {
    public static void main(String[] args) {
        Lc415 lc415 = new Lc415();
        String num1 = "456";
        String num2 = "77";
        String re = lc415.addStrings2(num1, num2);
        System.out.println(re);
    }

    public String addStrings(String num1, String num2) {
        if (isEmpty(num1) && isEmpty(num2)) {
            return null;
        }
        if (isEmpty(num1)) {
            return num2;
        }
        if (isEmpty(num2)) {
            return num1;
        }
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int sum;
        // 下面三个循环可以合并
        while (index2 >= 0 && index1 >= 0) {
            sum = (num1.charAt(index1) - '0') + (num2.charAt(index2) - '0') + c;
            c = sum / 10;
            sb.append(sum - c * 10);
            index2--;
            index1--;
        }
        // 剩余没有计算完的
        while (index1 >= 0) {
            sum = (num1.charAt(index1) - '0') + c;
            c = sum / 10;
            sb.append(sum - c * 10);
            index1--;
        }

        while (index2 >= 0) {
            sum = (num2.charAt(index2) - '0') + c;
            c = sum / 10;
            sb.append(sum - c * 10);
            index2--;
        }
        if (c > 0) {
            sb.append(c);
        }
        return sb.reverse().toString();
    }
    public Boolean isEmpty(String num) {
        if (num == null || "".equals(num)) {
            return true;
        }
        return false;
    }

    /**
     * 将三个循环合并
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings2(String num1, String num2) {
        if (isEmpty(num1) && isEmpty(num2)) {
            return null;
        }
        if (isEmpty(num1)) {
            return num2;
        }
        if (isEmpty(num2)) {
            return num1;
        }
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int sum;
        // 下面三个循环可以合并
        while (index1 >= 0 || index2 >= 0 || c > 0) {
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            sum = n1 + n2 + c;
            c = sum / 10;
            sb.append(sum - c * 10);
            index1--;
            index2--;
        }
        return sb.reverse().toString();
    }
}
