package com.java.study.algorithm.microsoft.m202402;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @Author： yijun
 * @DATE: 2024/2/20 20:50
 * @Description
 * https://leetcode.cn/problems/course-schedule/solutions/359392/ke-cheng-biao-by-leetcode-solution/
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class Lc207 {
    public static void main(String[] args) {
        Lc207 lc207 = new Lc207();
        int[][] prerequisites = new int[2][];
        prerequisites[0] = new int[] {1, 0};
        prerequisites[1] = new int[] {0, 1};
        boolean canFinish = lc207.canFinish(2, prerequisites);
        System.out.println(canFinish);
    }

    /**
     * 通过拓扑排序找判断一个有向图是否存在环
     * 如果一个拓扑排序的路径存在，就说明没有环
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 预处理，转换为邻接表和入度表
        if (prerequisites == null || prerequisites.length <= 1) {
            return true;
        }
        // 入度表
        int[] inDegree = new int[numCourses];
        // 邻接表
        List<Set<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new HashSet<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            // prerequisites[i][1] -> prerequisites[i][0]有一条边
            inDegree[prerequisites[i][0]]++;
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Queue<Integer> zeroQueue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroQueue.offer(i);
            }
        }
        // 进行遍历，删减
        int totalCount = 0;
        while (!zeroQueue.isEmpty()) {
            Integer curNode = zeroQueue.poll();
            totalCount++;
            // 删除这个节点的边
            Set<Integer> toNodes = graph.get(curNode);
            for (Integer toNode : toNodes) {
                inDegree[toNode]--;
                if (inDegree[toNode] == 0) {
                    zeroQueue.add(toNode);
                }
            }
        }
        // 做一个拓扑排序
        return totalCount == numCourses;
    }
}
