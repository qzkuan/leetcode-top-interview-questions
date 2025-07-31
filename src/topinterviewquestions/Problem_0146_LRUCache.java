package topinterviewquestions;

import java.util.HashMap;

/*
 * 在leetcode上提交时，把文字替换成下面的代码
 * 然后把类名、构造方法名从Problem_0146_LRUCache改为LRUCache即可
 */
/**
 * [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)

请你设计并实现一个满足 [LRU (最近最少使用) 缓存](https://baike.baidu.com/item/LRU) 约束的数据结构。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以 **正整数** 作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- `void put(int key, int value)` 如果关键字 `key` 已经存在，则变更其数据值 `value` ；如果不存在，则向缓存中插入该组 `key-value` 。如果插入操作导致关键字数量超过 `capacity` ，则应该 **逐出** 最久未使用的关键字。

函数 `get` 和 `put` 必须以 `O(1)` 的平均时间复杂度运行。
 */
public class Problem_0146_LRUCache {

	private MyCache<Integer, Integer> cache;

	public Problem_0146_LRUCache(int capacity) {
		cache = new MyCache<>(capacity);
	}

	public int get(int key) {
		Integer ans = cache.get(key);
		return ans == null ? -1 : ans;
	}

	public void put(int key, int value) {
		cache.set(key, value);
	}

	/**
	 * 封装结构类<key,value>
	 */
	public static class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> last;
		public Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * 封装结构双向链表
	 * 头（时间旧，先被移除）尾（时间新，最近使用）
	 */
	public static class NodeDoubleLinkedList<K, V> {
		private Node<K, V> head; // 头指针
		private Node<K, V> tail; // 尾指针

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		/**
		 * 向双向链表添加结点，尾插法
		 * @param newNode
		 */
		public void addNode(Node<K, V> newNode) {
			if (newNode == null) {
				return;
			}
			if (head == null) { // 双向链表为空，头尾指针都指向该结点
				head = newNode;
				tail = newNode;
			} else {			// 双向链表不空，插入到双向链表尾部，并修改尾指针指向
				tail.next = newNode;
				newNode.last = tail;
				tail = newNode;
			}
		}

		/**
		 * 移动结点到双向链表表尾位置
		 * @param node
		 */
		public void moveNodeToTail(Node<K, V> node) {
			if (tail == node) { // node已经是尾结点，不用操作
				return;
			}
			// node 不是尾巴
			if (head == node) { // node是头结点，修改头指针指向node下一个节点
				head = node.next;
				head.last = null;
			} else {			// node不是头不是尾结点，修改前后结点指向
				node.last.next = node.next;
				node.next.last = node.last;
			}
			// 把node放到链表表尾，并修改表尾指针
			node.last = tail;
			node.next = null;
			tail.next = node;
			tail = node;
		}

		/**
		 * 移除头结点（用于表满的时候删除最近最久未被使用的节点）
		 * @return
		 */
		public Node<K, V> removeHead() {
			if (head == null) {
				return null;
			}
			Node<K, V> res = head; // res指向要移除的结点
			if (head == tail) { // 链表中只有一个节点的时候
				head = null;
				tail = null;
			} else { // 链表不止一个结点，头指针指向下一个结点，移除该结点
				head = res.next;
				res.next = null;
				head.last = null;
			}
			return res;
		}

	}

	/**
	 * LRUCache主类
	 */
	public static class MyCache<K, V> {
		private HashMap<K, Node<K, V>> keyNodeMap;
		private NodeDoubleLinkedList<K, V> nodeList;
		private final int capacity;

		/**
		 * 构造方法，初始化容量和内部keyNodeMap，双向链表
		 * @param cap
		 */
		public MyCache(int cap) {
			if (cap < 1) {
				throw new RuntimeException("should be more than 0.");
			}
			keyNodeMap = new HashMap<K, Node<K, V>>();
			nodeList = new NodeDoubleLinkedList<K, V>();
			capacity = cap;
		}

		/**
		 * 从LRU缓存中获取node
		 * @param key
		 * @return
		 */
		public V get(K key) {
			if (keyNodeMap.containsKey(key)) {
				// 缓存里有该node，最近被使用，把该node移动到链表尾部
				Node<K, V> res = keyNodeMap.get(key);
				nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		/**
		 * 往LRU缓存插入/更新node
		 * @param key
		 * @param value
		 */
		public void set(K key, V value) {
			if (keyNodeMap.containsKey(key)) {
				// 缓存里已有node，更新node值，并移动到链表尾部
				Node<K, V> node = keyNodeMap.get(key);
				node.value = value;
				nodeList.moveNodeToTail(node);
			} else { // 缓存里没有node
				if (keyNodeMap.size() == capacity) {
					removeMostUnusedCache(); // 先检查容量，不够则删除最久未被使用结点
				}
				// 构建结点，插入到双向链表尾部
				Node<K, V> newNode = new Node<K, V>(key, value);
				keyNodeMap.put(key, newNode);
				nodeList.addNode(newNode);
			}
		}

		/**
		 * 删除最近最久未被使用的结点（即链表头结点）
		 */
		private void removeMostUnusedCache() {
			Node<K, V> removeNode = nodeList.removeHead();
			keyNodeMap.remove(removeNode.key);
		}

	}

}
