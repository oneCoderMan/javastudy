# Java中的栈
[代码实践](../../../../../../basicTech/src/main/java/com/java/study/algorithm/init/StackTest.java)
## 基于Stack实现
是同步方法，性能问题
```java
public static void testStack() {
        Stack<Integer> stack = new Stack();
        // 元素入栈
        stack.push(1);
        stack.push(3);
        System.out.println(stack);
        // 取栈顶元素，不移除
        System.out.println(stack.peek());
        System.out.println(stack);
        // 取栈顶元素，并且移除
        System.out.println(stack.pop());
        System.out.println(stack);

    }
```

## 基于LinedList
```java
Deque<Integer> stack = new LinkedList<>();
// 入栈
stack.push(1);
stack.push(2);
if (stack.isEmpty()) {
    System.out.println("stack is not empty");
}
// 取栈顶元素，不移除
Integer peek = stack.peek();
System.out.println(peek);
System.out.println(JsonUtil.toJson(stack));

// 取栈顶元素，并且移除
Integer pop = stack.pop();
System.out.println(pop);
System.out.println(JsonUtil.toJson(stack));
```