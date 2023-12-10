package com.java.study.algorithm.graph;

import com.java.study.algorithm.init.ArrayTool;
import com.java.study.algorithm.init.Pair;
import com.java.study.utils.JsonUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author： yijun
 * @DATE: 2023/12/9 11:28
 * @Description
 */
public class Lc200 {
    private int[] dj = new int[]{1, 0, -1 , 0};
    private int[] di = new int[]{0, 1, 0, -1};
    /**
     *
     * https://leetcode.cn/problems/number-of-islands/description/
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     * @param args
     */
    public static void main(String[] args) {
        String charInputStr = "[[\"1\",\"1\",\"1\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"0\",\"0\",\"0\"]]";
        char[][] chats = ArrayTool.strToCharArray(charInputStr);
        System.out.println(JsonUtil.toJson(chats));
        Lc200 lc200 = new Lc200();
        int nums = lc200.numIslands(chats);
        System.out.println(nums);
    }

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            visited[i] = new boolean[grid[i].length];
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    bfs(i, j, grid, visited);
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * 从当前位置出发，一圈圈往外走，如果遇到0就结束
     * @param i 当前位置x坐标
     * @param j 当前位置y坐标
     * @param grid
     * @param visited
     */
    private void bfs(int i, int j, char[][] grid, boolean[][] visited) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(i, j));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> peek = queue.poll();
            int currentI = peek.getFirst();
            int currentJ = peek.getSecond();

            if (visited[currentI][currentJ] == true) {
                continue;
            }
            // 标记访问过
            visited[currentI][currentJ] = true;

            // 没有访问过，往四周扩散
            for (int index = 0; index < di.length; index++) {
                int newi = currentI + di[index];
                int newj = currentJ + dj[index];
                // 如果有效，并且没有被访问过，加入到队列中
                if (newi >= 0 && newi < grid.length
                        && newj >= 0 && newj < grid[0].length
                        && visited[newi][newj] == false
                        && grid[newi][newj] == '1') {
                    queue.add(new Pair<>(newi, newj));
                }
            }
        }
    }

}
