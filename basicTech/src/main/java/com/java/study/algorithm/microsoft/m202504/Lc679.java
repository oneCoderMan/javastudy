package com.java.study.algorithm.microsoft.m202504;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2025/4/5 16:39
 * @Description
 * https://leetcode.cn/problems/24-game/description/
 * 可以通过回溯的方法遍历所有不同的可能性。具体做法是，使用一个列表存储目前的全部数字，每次从列表中选出 2 个数字，
 * 再选择一种运算操作，用计算得到的结果取代选出的 2 个数字，这样列表中的数字就减少了 1 个。
 * 重复上述步骤，直到列表中只剩下 1 个数字，这个数字就是一种可能性的结果，
 * 如果结果等于 24，则说明可以通过运算得到 24。
 * 如果所有的可能性的结果都不等于 24，则说明无法通过运算得到 24。
 */
public class Lc679 {
    private static Double zeroCmp = 1e-6;
    private static Double target = 24D;

    /**
     * 一些细节：
     * 1. 在判断结果是否等于 24 时应考虑精度误差，这道题中，误差小于 10−6 可以认为是相等。
     * 2. double 类型不能使用 "==", 需要用做差和一个较小的值比较判断
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = new int[]{4, 1, 8, 7};
        int[] nums = new int[]{1, 2, 1, 2};

        Lc679 lc679 = new Lc679();
        boolean result = lc679.judgePoint24(nums);
        System.out.println(result);

    }

    public boolean judgePoint24(int[] cards) {

        List<Double> curCards = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            curCards.add(Double.valueOf(cards[i]));
        }
        return solve(curCards);
    }

    private boolean solve(List<Double> curCards) {
        if (curCards.size() < 1) {
            return false;
        }
        if (curCards.size() == 1) {
            // 已经达到了24点
            if (Math.abs(curCards.get(0) - target) < zeroCmp) {
                return true;
            }
        }
        // 从curCards里面挑选两个数字出来
        for (int i = 0; i < curCards.size(); i++) {
            for (int j = 0; j < curCards.size(); j++) {
                if (i == j) {
                    continue;
                }
                // 这两个数字进行四种不同的操作，放入下一轮的集合中
                Double num1 = curCards.get(i);
                Double num2 = curCards.get(j);
                List<Double> nextCard = new ArrayList<>();
                // 剩余的数字放入到 nextCard
                for (int k = 0; k < curCards.size(); k++) {
                    if (k != i && k != j) {
                        nextCard.add(curCards.get(k));
                    }
                }
                Double nextNum = 0D;
                for (int k = 0; k < 4; k++) {
                    if (k == 0) {
                        nextNum = num1 + num2;
                    } else if (k == 1) {
                        nextNum = num1 - num2;
                    } else if (k == 2) {
                        nextNum = num1 * num2;
                    } else  {
                        // 除数不能为0
                        if (num2 < zeroCmp) {
                            continue;
                        }
                        nextNum = num1 / num2;
                    }
                    // 这个时候已经降维了
                    nextCard.add(nextNum);
                    boolean result = solve(nextCard);
                    if (result) {
                        return true;
                    }
                    // 否则放出来下一个继续
                    nextCard.remove(nextCard.size() - 1);
                }
            }
        }
        return false;
    }

}
