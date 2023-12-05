package com.java.study.algorithm.graph;

import com.java.study.algorithm.init.ArrayTool;
import com.java.study.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2023/12/5 21:52
 * @Description
 */
public class Lc797 {
    /**
     * https://leetcode.cn/problems/all-paths-from-source-to-target/description/
     * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
     * @param args
     */
    public static void main(String[] args) {
        Lc797 lc797 = new Lc797();
        String inputString = "[[4,3,1],[3,2,4],[3],[4],[]]";
        int[][] graph = ArrayTool.strToArray(inputString);
        List<List<Integer>> lists = lc797.allPathsSourceTarget(graph);
        System.out.println(JsonUtil.toJson(lists));
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        int n = graph.length;
        currentPath.add(0);
        dfs(graph, result, currentPath, 0, n-1);
        System.out.println(result);
        return null;
    }


    private void dfs(int[][] graph,
                     List<List<Integer>> result,
                     List<Integer> currentPath,
                     int startNode,
                     int targetNode) {
        if (startNode == targetNode) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        int[] edges = graph[startNode];
        if (edges == null || edges.length <= 0) {
            // 没有路径了
            return;
        }
        for (int i = 0; i < edges.length; i++) {
            currentPath.add(edges[i]);
            dfs(graph, result, currentPath, edges[i], targetNode);
            currentPath.remove(currentPath.size() - 1);
        }
    }

}
