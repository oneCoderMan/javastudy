package com.java.study.algorithm.microsoft.m202404;

import com.java.study.utils.JsonUtil;

import java.util.PriorityQueue;

/**
 * @Author： yijun
 * @DATE: 2024/4/4 22:16
 * @Description
 * https://leetcode.cn/problems/find-median-from-data-stream/description/
 * 数据流中的中位数
 */
public class Lc295 {
    public static void main(String[] args) {
        Lc295 lc295 = new Lc295();
        lc295.test();



    }

    public void test() {
        MedianFinder medianFinder = new MedianFinder();
        // [],[],[],[],[],[],[],[],[],[],[],[],[6],[],[8],[],[2],[],[14],[],[25],[],[25]
        // [4],[],[33],[],[18],[],[10],[],[14],[]
        int[] nums = {40, 12, 16, 14,35,19,34,35,28,35,26,6,8, 2, 14, 25};
        for (int i = 0; i < nums.length; i++) {
            medianFinder.addNum(nums[i]);
            System.out.println(JsonUtil.toJson(medianFinder.l));
            System.out.println(JsonUtil.toJson(medianFinder.r));
            System.out.println("====");
//            System.out.println(medianFinder.findMedian());
        }
        System.out.println("=== 加入25 前");
        medianFinder.addNum(25);
        System.out.println("=== 加入25 后");
        System.out.println(JsonUtil.toJson(medianFinder.l));
        System.out.println(JsonUtil.toJson(medianFinder.r));
        System.out.println(medianFinder.findMedian());


    }

    // [null,null,40.0,null,26.0,null,16.0,null,15.0,null,16.0,null,17.5,null,19.0,null,
    // 26.5,null,28.0,null,31.0,null,28.0,null,27.0,null,26.0,null,22.5,
    // null,19.0,null,22.0,null,25.0,
    // null,22.0,null,25.0,null,22.0,null,19.0,null,18.5,null,19.0,null,18.5,null,19.0,null,18.5,null,19.0,null,21.5,null,19.0,null,18.5,null,18.0,null,18.5,null,19.0,null,19.0,null,19.0,null,18.5,null,19.0,null,19.0,null,19.0,null,19.0,null,19.0]

    class MedianFinder {
        // 大顶堆
        PriorityQueue<Integer> l;
        // 小顶堆
        PriorityQueue<Integer> r;

        public MedianFinder() {
            // 大顶堆
            l = new PriorityQueue<>((a, b) -> (b - a));
            // 小顶堆
            r = new PriorityQueue<>();

        }

        /**
         * 两个队列，数据流的左半部分放到大顶堆
         * 右半部分放到小顶堆
         * 添加元素的时候维护这两个队列一直有序，并且保持平衡，
         * @param num
         */
        public void addNum(int num) {
            if (l.isEmpty()) {
                l.add(num);
                return;
            }
            // 第二个数
            if (r.isEmpty()) {
                if (num >= l.peek()) {
                    r.add(num);
                } else {
                    l.add(num);
                    r.add(l.poll());
                }
                return;
            }
            // 之后的数
            if (l.size() == r.size()) {
                // 后半部分的数 (这里要注意两个数相等的时候)
                // num == l.peek()的时候需要放到前面
                if (num > l.peek()) {
                    r.add(num);
                } else if (num < l.peek()) {
                    l.add(num);
                } else {
                    // 等于左边的时候，需要放到右边去 *** 并且需要把右边的元素放到左边来
                    r.add(num);
                    l.add(r.poll());
                }
                return;
            }
            // 两个堆大小不等 (需要调整相等)
            // 需要判断这个数需要放到哪一边，大还是小边
            if (l.size() < r.size()) {
                if (num > r.peek()) {
                    // poll 发生之前先加元素 （这样才能维护有序）
                    r.add(num);
                    l.add(r.poll());
                } else if (num < r.peek()) {
                    l.add(num);
                } else {
                    // num == r.peek()的时候需要放到前面
                    l.add(num);
                }
            } else {
                // 左边的多
                if (num >= r.peek()) {
                    r.add(num);
                } else {
                    l.add(num);
                    r.add(l.poll());
                }
            }
        }

        public double findMedian() {
            // 偶数个数
            if (l.size() == r.size()) {
                double re = (l.peek() + r.peek()) / 2.0F;
                return re;
            }
            if (l.size() > r.size()) {
                return l.peek();
            } else {
                return r.peek();
            }
        }
    }
}


