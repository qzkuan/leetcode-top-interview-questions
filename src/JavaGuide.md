

# Java

## Java基础



## Java集合

#### ArrayList

- 支持快速随机访问能力

- 线程不安全

- `ArrayList` 中可以存储任何类型的对象，包括 `null` 值。不过，不建议向`ArrayList` 中添加 `null` 值， `null` 值无意义，会让代码难以维护比如忘记做判空处理就会导致空指针异常。


> 扩容机制

底层是`动态数组`，**以无参数构造方法创建 `ArrayList` 时，实际上初始化赋值的是一个空数组。当真正对数组进行添加元素操作时，才真正分配容量。即向数组中添加第一个元素时，数组容量扩为 10。**

直到添加第 11 个元素，`minCapacity`(为 11)比 `elementData.length`（为 10）要大。进入 `grow` 方法进行扩容。

**ArrayList 每次扩容之后容量都会变为原来的 1.5 倍左右（oldCapacity 为偶数就是 1.5 倍，否则是 1.5 倍左右）！** 奇偶不同，比如：10+10/2 = 15, 33+33/2=49。如果是奇数的话会丢掉小数

部分源码

```java
    /**
     * 默认初始容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;

/**
 * 要分配的最大数组大小
 */
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

/**
 * ArrayList扩容的核心方法。
 */
private void grow(int minCapacity) {
    // oldCapacity为旧容量，newCapacity为新容量
    int oldCapacity = elementData.length;
    // 将oldCapacity 右移一位，其效果相当于oldCapacity /2，
    // 我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将新容量更新为旧容量的1.5倍，
    int newCapacity = oldCapacity + (oldCapacity >> 1);
}
```



#### LinkedList

- 基于双向链表实现

> LinkedList 插入和删除元素的时间复杂度？

- 头部插入/删除：只需要修改头结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
- 尾部插入/删除：只需要修改尾结点的指针即可完成插入/删除操作，因此时间复杂度为 O(1)。
- 指定位置插入/删除：需要先移动到指定位置，再修改指定节点的指针完成插入/删除，不过由于有头尾指针，可以从较近的指针出发，因此需要遍历平均 n/4 个元素，时间复杂度为 O(n)。

#### Queue和Dequeue

> Queue 接口方法

| 方法                 | 作用                   | 特点（异常/返回null） |
| -------------------- | ---------------------- | --------------------- |
| `boolean add(E e)`   | 入队                   | 队满时抛异常          |
| `boolean offer(E e)` | 入队                   | 队满时返回 `false`    |
| `E remove()`         | 出队（取并删除头元素） | 队空时抛异常          |
| `E poll()`           | 出队                   | 队空时返回 `null`     |
| `E element()`        | 查看队头（不删除）     | 队空时抛异常          |
| `E peek()`           | 查看队头（不删除）     | 队空时返回 `null`     |

**记忆口诀**：

- `add/remove/element` → “严格版”，异常处理
- `offer/poll/peek` → “温和版”，返回特殊值

> Deque 接口方法（比 Queue 多了双端操作）

在 Queue 基础上，Deque 提供了**头尾操作**：

| 方法                            | 作用               | 队满/空行为  |
| ------------------------------- | ------------------ | ------------ |
| `addFirst(e)` / `offerFirst(e)` | 插入到头部         | 异常 / false |
| `addLast(e)` / `offerLast(e)`   | 插入到尾部         | 异常 / false |
| `removeFirst()` / `pollFirst()` | 删除并返回头部元素 | 异常 / null  |
| `removeLast()` / `pollLast()`   | 删除并返回尾部元素 | 异常 / null  |
| `getFirst()` / `peekFirst()`    | 查看头部元素       | 异常 / null  |
| `getLast()` / `peekLast()`      | 查看尾部元素       | 异常 / null  |

特别用途

- **栈（Stack）** → `push(e) = addFirst(e)`，`pop() = removeFirst()`，`peek() = peekFirst()`，Dequeue接口也有push(), pop()和peek()`方法。
- **队列（FIFO）** → `offerLast(e)` 入队，`pollFirst()` 出队
- **双端队列（Deque）** → 两端都能进出，灵活



#### HashMap

非线程安全

`HashMap` 可以存储 null 的 key 和 value，但 null 作为键只能有一个，null 作为值可以有多个

JDK1.8 之前 HashMap 由 ==数组+链表== 组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。1.7及之前使用`头插法`，1.8使用`尾插法`避免多线程出现循环链表。

JDK1.8 以后的 `HashMap` 在解决哈希冲突时有了较大的变化，当链表长度大于等于阈值（默认为 8）时，将链表转化为红黑树，以减少搜索时间。（注：将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）

**为什么选择阈值 8 和 64？**

1. 泊松分布表明，链表长度达到 8 的概率极低（小于千万分之一）。在绝大多数情况下，链表长度都不会超过 8。阈值设置为 8，可以保证性能和空间效率的平衡。
2. 数组长度阈值 64 同样是经过实践验证的经验值。在小数组中扩容成本低，优先扩容可以避免过早引入红黑树。数组大小达到 64 时，冲突概率较高，此时红黑树的性能优势开始显现。

`HashMap` 默认的初始化大小为 16。之后每次扩充，容量变为原来的 2 倍。并且， `HashMap` 总是使用 2 的幂作为哈希表的大小。

```java
    // 默认的初始容量是16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 默认的负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 当桶(bucket)上的结点数大于等于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8;
    // 当桶(bucket)上的结点数小于等于这个值时树转链表
    static final int UNTREEIFY_THRESHOLD = 6;
    // 桶中结构转化为红黑树对应的table的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;

```

> `put`添加元素流程

![ ](https://oss.javaguide.cn/github/javaguide/database/sql/put.png)

#### ConcurrentHashMap

在 JDK1.7 的时候，`ConcurrentHashMap` 对整个桶数组进行了分割分段(`Segment`，分段锁)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率。

到了 JDK1.8 的时候，`ConcurrentHashMap` 已经摒弃了 `Segment` 的概念，而是直接用 `Node` 数组+链表+红黑树的数据结构来实现，并发控制使用 `synchronized` 和 CAS 来操作。（JDK1.6 以后 `synchronized` 锁做了很多优化） 整个看起来就像是优化过且线程安全的 `HashMap`，虽然在 JDK1.8 中还能看到 `Segment` 的数据结构，但是已经简化了属性，只是为了兼容旧版本；

- **并发度**：JDK 1.7 最大并发度是 Segment 的个数，默认是 16。JDK 1.8 最大并发度是 Node 数组的大小，并发度更大。

#### LinkedHashMap

`LinkedHashMap` 是在 `HashMap` 基础上在各个节点之间维护一条双向链表，使得原本散列在不同 bucket 上的节点、链表、红黑树有序关联起来。使之拥有按照==插入顺序==或==访问顺序==迭代元素的功能。

**accessOrder = false**（默认插入顺序）

- 保持插入顺序的有序 Map，比如需要 JSON 序列化时保证字段顺序。

**accessOrder = true**（访问顺序）

- 常用于实现 **LRU 缓存**：最近使用过的放到尾部，最久未使用的在头部。
- 可以结合 `removeEldestEntry` 钩子方法自动淘汰旧数据：

```java
public LinkedHashMap(int initialCapacity,
                     float loadFactor,
                     boolean accessOrder)

LinkedHashMap<Integer,String> map = new LinkedHashMap<>(16,0.75f,true);
map.put(3,"C");
map.put(1,"A");
map.put(2,"B");

map.get(3); // 访问 key=3
// 遍历输出：1=A, 2=B, 3=C （3 被移到最后）



public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 判断size超过容量时返回true，告知LinkedHashMap移除最老的缓存项(即链表的第一个元素)
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity; // 超过设定的capacity时，移除最老的
    }
}
LRUCache<Integer, String> cache = new LRUCache<>(3);
cache.put(1, "one");
cache.put(2, "two");
cache.put(3, "three");
cache.put(4, "four");
cache.put(5, "five");
for (int i = 1; i <= 5; i++) {
    System.out.println(cache.get(i));
}
// 输出如下：null null three four five

```



<img src="https://oss.javaguide.cn/github/javaguide/java/collection/linkhashmap-structure-overview.png" alt="LinkedHashMap 逻辑结构" style="zoom: 60%;" />

## PriorityQueue

默认小根堆



```java
// 大根堆实现方式：
// 方式1：Lambda表达式
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
        (a, b) -> {
            return b - a; // 大根堆
        });
// 方式2：Comparator.reverseOrder()
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

// 方式3：自定义Comparator
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
});


import java.util.PriorityQueue;
import java.util.Comparator;

public class MaxHeapExample {
    public static void main(String[] args) {
        // 创建大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
        
        // 插入元素
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);
        
        // 依次取出最大元素
        while(!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}

```

