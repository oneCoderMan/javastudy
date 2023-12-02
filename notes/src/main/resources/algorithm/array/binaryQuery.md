# 二分查找介绍
[leetcode链接](https://leetcode.cn/problems/binary-search/description/)
```
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
```

# 题解
[代码实现](../../../../../../basicTech/src/main/java/com/java/study/algorithm/array/BinaryQuery.java)

时间复杂度：O(log n)
空间复杂度：O(1)

# 技巧
半开半闭区间搜索可以简化编码
> 1. [a,b) + [b,c) = [a,c);
> 2. b-a = len([a,b))
> 3. [a, a) = empty

# REF
[二分查找介绍](https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html#%E6%80%9D%E8%B7%AF)