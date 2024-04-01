package com.java.study.algorithm.microsoft.m202403;

import com.java.study.utils.JsonUtil;

/**
 * @Author： yijun
 * @DATE: 2024/3/31 10:45
 * @Description
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
 * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 *
 */
public class LCR170 {
    private Integer count;
    public static void main(String[] args) {
        LCR170 lcr170 = new LCR170();
//        int[] nums = {9, 7, 5, 4, 6};
        int[] nums = {1,3,2,3,1};
        int re = lcr170.reversePairs(nums);
        System.out.println(re);
        System.out.println(JsonUtil.toJson(nums));
    }

    /**
     * 1,3,2,3,1  => 6
     * 9, 7, 5, 4, 6 => 8
     *  使用归并排序是解决逆序对的经典问题
     *  在归并阶段发现逆序数组，两个排序的数组，如果num1[i] > num2[j]
     *  那么num1中 i 到 nums1末尾的数字都会大于num2[j]
     * @param record
     * @return
     */
    public int reversePairs(int[] record) {
        count = 0;
        mergeSort(record, 0, record.length);
        return count;
    }

    /**
     * 左闭右开区间
     * @param record
     * @param left
     * @param right
     */
    public void mergeSort(int[] record, int left, int right) {
        if (record == null) {
            return;
        }
        // 无效区间
        if (right == left) {
            return;
        }
        // 只有一个数
        if ((right - left) == 1) {
            return;
        }
        int mid = left + (right - left) / 2;
        // 将[left, mid) 的数据排好
        mergeSort(record, left, mid);
        // 将[mid, right) 的数据排好
        mergeSort(record, mid, right);
        // 归并
        doMerge(record, left, mid, mid, right);
    }

    private void doMerge(int[] record, int left1, int right1,
                         int left2, int right2) {
        // 将两个数组合并成一个有序数组
        int len = right2 - left1;
        int[] temp = new int[len];
        int index1 = left1;
        int index2 = left2;
        int indexTemp = 0;
        while (index1 < right1 && index2 < right2) {
            // 注意这里需要是 小于等于， 等于的时候不是逆序对
            if (record[index1] <= record[index2]) {
                temp[indexTemp] = record[index1];
                index1++;
            } else {
                temp[indexTemp] = record[index2];
                // 从index1到right1都是逆序对
                count = count + (right1 - index1);
                index2++;
            }
            indexTemp++;
        }
        // 剩余没有排完的
        while (index1 < right1) {
            temp[indexTemp] = record[index1];
            index1++;
            indexTemp++;
        }
        while (index2 < right2) {
            temp[indexTemp] = record[index2];
            index2++;
            indexTemp++;
        }
        // 复制值
        // System.out.println(JsonUtil.toJson(temp));
        for (int i = 0; i < len; i++) {
            record[left1 + i] = temp[i];
        }
    }

}
