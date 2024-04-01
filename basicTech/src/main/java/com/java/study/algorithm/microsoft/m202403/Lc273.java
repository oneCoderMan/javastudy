package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/30 23:21
 * @Description
 * https://leetcode.cn/problems/integer-to-english-words/description/
 * 将非负整数 num 转换为其对应的英文表示。
 * eg:
 * num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class Lc273 {
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    int[] divide = { (int)1e9, (int)1e6, (int)1e3};
    String[] larger = {"Billion", "Million", "Thousand"};

    // 一个主要的点需要先表示0 - 999的数据
    public static void main(String[] args) {
        Lc273 lc273 = new Lc273();
//        String re = lc273.numberToLessOneThousand(1);
//        System.out.println(re);
//        String re1 = lc273.numberToWords(20);
        String re1 = lc273.numberToWords(12345);
//        String re1 = lc273.numberToWords(12);
        System.out.println("==="+re1+"===");


    }
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < divide.length; i++) {
            int pre = num / divide[i];
            if (pre != 0) {
                sb.append(numberToLessOneThousand(pre))
                        .append(" ")
                        .append(larger[i]).append(" ");
            }
            int remain = num % divide[i];
            num = remain;
        }
        if (num > 0) {
            sb.append(numberToLessOneThousand(num));
        }
        return sb.toString().trim();
    }

    public String numberToLessOneThousand(int num) {
        if (num == 0) {
            return "Zero";
        }
        if (num > 999) {
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        // 提取出百位
        int threePoint = num / 100;
        // 提取出十位
        int twoPoint = num % 100 / 10;
        // 提取出个位
        int onePoint = num % 10;
        if (threePoint != 0) {
            sb.append(singles[threePoint]).append(" ").append("Hundred ");
        }
        if (twoPoint != 0 && twoPoint != 1) {
            sb.append(tens[twoPoint]).append(" ");
        }
        // 个位必须有
        if (twoPoint == 1) {
            sb.append(teens[onePoint]).append(" ");
        } else {
            sb.append(singles[onePoint]);
        }
        return sb.toString().trim();
    }
}
