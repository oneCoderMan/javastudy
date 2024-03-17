package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/3/14 22:58
 * @Description
 */
public class Lc752 {
    public static void main(String[] args) {
        Lc752 l752 = new Lc752();
//        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
//        String target = "0202";

//        String[] deadends = new String[]{"8888"};
//        String target = "0009";

        String[] deadends = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";

        int re = l752.openLock(deadends, target);
        System.out.println(re);
    }

    /**
     * BFS
     *
     */
    public int openLock(String[] deadends, String target) {
        if (target == null || target == "") {
            return 0;
        }
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> deadEnds = new HashSet<>(Arrays.asList(deadends));
        // 包含死亡数字,开始就是死亡数字
        if (deadEnds.contains(target) || deadEnds.contains("0000")) {
            return -1;
        }
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> hasVisited = new HashSet<>();
        hasVisited.add("0000");
        queue.offer(new Pair<>("0000", 0));
        while (!queue.isEmpty()) {
            Pair<String, Integer> currentState = queue.poll();
            // 当前状态往后变化 (会生成8个字符)
            List<String> nextStates = generatorNextState(currentState.getFirst());
            for (String nextState : nextStates) {
                // 已经找到了，结束递归
                if (nextState.equals(target)) {
                    return currentState.getSecond() + 1;
                }
                // 已经访问过不需要了 死亡的不需要
                if (hasVisited.contains(nextState) || deadEnds.contains(nextState)) {
                    continue;
                }
                // 标识已经访问过
                hasVisited.add(nextState);
                queue.add(new Pair<>(nextState, currentState.getSecond() + 1));
            }
        }
        // 没有找到
        return -1;

    }

    private List<String> generatorNextState(String currentState) {
        char[] chars = currentState.toCharArray();
        List<String> re = new ArrayList<>();
        char temp;
        for (int i = 0; i < chars.length; i++) {
            temp = chars[i];
            chars[i] = minsOne(chars[i]);
            re.add(new String(chars));
            chars[i] = temp;
            chars[i] = addOne(chars[i]);
            re.add(new String(chars));
            chars[i] = temp;
        }
        return re;
    }

    private char minsOne(char num) {
        if (num == '0') {
            return '9';
        }
        return (char) (num - 1);
    }
    public char addOne(char num) {
        if (num == '9') {
            return '0';
        }
        return (char) (num + 1);
    }

}
