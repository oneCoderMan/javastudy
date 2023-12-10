# Java中的队列
[代码实践](../../../../../../basicTech/src/main/java/com/java/study/algorithm/init/QueueTest.java)
```java
 Queue<Integer> queue = new LinkedList<>();
// 进入队列
queue.offer(1);
queue.offer(4);
// 获取队列头的元素，不移除
Integer peek = queue.peek();
System.out.println(peek);
System.out.println(queue);
// 获取队列头的元素，移除
Integer poll = queue.poll();
System.out.println(poll);
System.out.println(queue);
```