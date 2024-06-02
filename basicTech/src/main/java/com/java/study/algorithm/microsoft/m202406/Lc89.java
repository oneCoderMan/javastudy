package com.java.study.algorithm.microsoft.m202406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2024/6/1 22:14
 * @Description
 * https://leetcode.cn/problems/gray-code/
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
 * 1. 每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
 * 2. 第一个整数是 0
 * 3. 一个整数在序列中出现 不超过一次
 * 4. 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 5. 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 *
 */
public class Lc89 {
    public static void main(String[] args) {
        Lc89 lc89 = new Lc89();
        List<Integer> re = lc89.grayCode(3);
        for (Integer item : re) {
            System.out.print(String.format("%d ", item));
        }
        //System.out.println(JsonUtil.toJson(re));
    }

    /**
     * 格雷码规律
     * n = 0
     * (0)
     * n = 1
     * (0, 1)
     * n= 2
     * (00, 01, 11, 10)
     * n = 3
     * (000, 001, 011, 010, 110, 111, 101, 100)
     *
     * 规律：1位格雷码有两个码字
     * (n+1)位格雷码中的前2^n个码字等于n位格雷码的码字，按顺序书写，加前缀0
     * (n+1)位格雷码中的后2^n个码字等于n位格雷码的码字，按逆序书写，加前缀1
     * n+1位格雷码的集合 = n位格雷码集合(顺序)加前缀0 + n位格雷码集合(逆序)加前缀1
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            return Arrays.asList(0);
        }
        if (n == 1) {
            return Arrays.asList(0, 1);
        }
        List<Integer> preGrayCode = grayCode(n - 1);
        // n 阶格雷码前 2^n 个 是，preGrayCode前面加0
        List<Integer> re = new ArrayList<>(2 * preGrayCode.size());
        re.addAll(preGrayCode);
        // 后2^n 个是 preGrayCode前面加1
        int base = (int) Math.pow(2, n - 1);
        for (int i = preGrayCode.size() - 1 ; i >= 0; i--) {
            re.add(preGrayCode.get(i) + base);
        }
        return re;
    }
}
