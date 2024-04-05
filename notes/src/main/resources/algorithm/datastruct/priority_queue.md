# Java中的优先队列
[代码实践](../../../../../../basicTech/src/main/java/com/java/study/algorithm/init/PriorityQueueTest.java)
```java
PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> (b-a));
maxQueue.add(1);
maxQueue.add(7);
System.out.println(maxQueue.peek());
Integer poll = maxQueue.poll();
System.out.println(maxQueue.peek());
```