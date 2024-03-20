package com.java.study.algorithm.microsoft.m202403;

import com.java.study.algorithm.init.ArrayTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/3/18 22:35
 * @Description
 * https://leetcode.cn/problems/course-schedule-ii/description/
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，
 * 表示在选修课程 ai 前 必须 先选修 bi 。
 */
public class Lc210 {
    public static void main(String[] args) {
        Lc210 lc210 = new Lc210();
        String inputString = "[[1,0],[2,0],[3,1],[3,2]]";
        int[][] matrix = ArrayTool.strToArray(inputString);
        int[] order = lc210.findOrder(4, matrix);
        System.out.println(JsonUtil.toJson(order));
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        // 初始化入度表和邻接表
        int[] inDegree = new int[numCourses];
        Set<Integer> zeroInDegree = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            // 默认为0
            inDegree[i] = 0;
            zeroInDegree.add(i);
        }
        Map<Integer, Set<Integer>> nextNodeMaps = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int toClass = prerequisites[i][0];
            int fromClass =  prerequisites[i][1];
            inDegree[toClass] = inDegree[toClass] + 1;
            zeroInDegree.remove(toClass);
            Set<Integer> curClassNext = nextNodeMaps.getOrDefault(fromClass, new HashSet<>());
            curClassNext.add(toClass);
            nextNodeMaps.put(fromClass, curClassNext);
        }
        // 开始遍历当前入度为零的节点
        List<Integer> classOrder = new ArrayList<>();
        while (true) {
            if (zeroInDegree.isEmpty()) {
                break;
            }
            Integer curClass = zeroInDegree.iterator().next();
            classOrder.add(curClass);
            zeroInDegree.remove(curClass);
            // 将当前节点的子节点的边删除
            Set<Integer> nextClassId = nextNodeMaps.get(curClass);
            if (nextClassId == null) {
                continue;
            }
            for (Integer classId : nextClassId) {
                inDegree[classId]--;
                if (inDegree[classId] <= 0) {
                    zeroInDegree.add(classId);
                }
            }
        }
        if (classOrder.size() == numCourses) {
            int[] re = new int[numCourses];
            for (int i = 0; i < classOrder.size(); i++) {
                re[i] = classOrder.get(i);
            }
            return re;
        }
        return new int[0];
    }

}
