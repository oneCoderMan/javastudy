# 1. 算法介绍
性能标准：时间复杂度，空间复杂度

# 2. 数组
[704-二分查找](./array/binaryQuery.md)


# 3. 排序算法

# 4. 图论算法
DFS算法框架
```java
void dfs(参数) {
    if (终止条件) {
        存放结果;
        return;
    }

    for (选择：本节点所连接的其他节点) {
        处理节点;
        dfs(图，选择的节点); // 递归
        回溯，撤销处理结果
    }
}
```

BFS模版
```java
private int[] dj = new int[]{1, 0, -1 , 0};
private int[] di = new int[]{0, 1, 0, -1};
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
```

[797.所有可能的路径](../../../../../basicTech/src/main/java/com/java/study/algorithm/graph/Lc797.java)

[200.岛屿数量](../../../../../basicTech/src/main/java/com/java/study/algorithm/graph/Lc200.java)

# REF
[代码随想录](https://programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

[leetcode-master](https://github.com/youngyangyang04/leetcode-master)