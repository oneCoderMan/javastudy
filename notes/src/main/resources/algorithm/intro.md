# 1. 算法介绍
性能标准：时间复杂度，空间复杂度

# 2. 数组
[704-二分查找](./array/binaryQuery.md)


# 3. 排序算法

# 图论算法
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

[797.所有可能的路径](../../../../../basicTech/src/main/java/com/java/study/algorithm/graph/Lc797.java)

# REF
[代码随想录](https://programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)

[leetcode-master](https://github.com/youngyangyang04/leetcode-master)