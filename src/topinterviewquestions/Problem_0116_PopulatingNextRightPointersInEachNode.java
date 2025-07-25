package topinterviewquestions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [116.
 * 填充每个节点的下一个右侧节点指针](https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/)
 * 
 * 给定一个 **完美二叉树** ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * 
 * ```
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * ```
 * 
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 `NULL`。
 * 
 * 初始状态下，所有 next 指针都被设置为 `NULL`。
 */
public class Problem_0116_PopulatingNextRightPointersInEachNode {

	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
	}

	public static class MyQueue {
		public Node head;
		public Node tail;
		public int size;

		public MyQueue() {
			head = null;
			tail = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void offer(Node cur) {
			size++;
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
		}

		public Node poll() {
			size--;
			Node ans = head;
			head = head.next;
			ans.next = null;
			return ans;
		}

	}

	public static Node connect(Node root) {
		if (root == null) {
			return root;
		}
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 第一个弹出的节点
			Node pre = null;
			int size = queue.size;
			for (int i = 0; i < size; i++) {
				Node cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				if (pre != null) {
					pre.next = cur;
				}
				pre = cur;
			}
		}
		return root;
	}

	/**
	 * 填充每个节点的下一个右侧节点指针（层序遍历写法）
	 * 
	 * @param root
	 * @return
	 */
	public Node connectKuan(Node root) {
		if (root == null) {
			return root;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			Node nextNode = null;
			// 当前队列里的都是同一层的，挨个打印并入队下一层节点
			for (int i = queueSize; i > 0; i--) {
				Node poll = queue.poll();
				poll.next = nextNode;
				nextNode = poll;
				if (poll.right != null) {
					queue.offer(poll.right);
				}
				if (poll.left != null) {
					queue.offer(poll.left);
				}

			}
		}
		return root;
	}

}
