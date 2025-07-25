package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * [103. 二叉树的锯齿形层序遍历](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/)

给你二叉树的根节点 `root` ，返回其节点值的 **锯齿形层序遍历** 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		LinkedList<TreeNode> deque = new LinkedList<>();
		deque.add(root);
		int size = 0;
		boolean isHead = true;
		while (!deque.isEmpty()) {
			size = deque.size();
			List<Integer> curLevel = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = isHead ? deque.pollFirst() : deque.pollLast();
				curLevel.add(cur.val);
				if(isHead) {
					if (cur.left != null) {
						deque.addLast(cur.left);
					}
					if (cur.right != null) {
						deque.addLast(cur.right);
					}
				}else {
					if (cur.right != null) {
						deque.addFirst(cur.right);
					}
					if (cur.left != null) {
						deque.addFirst(cur.left);
					}
				}
			}
			ans.add(curLevel);
			isHead = !isHead;
		}
		return ans;
	}

	/**
	 * 层序遍历（借用辅助队列）
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrderKuan(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		boolean reverse = false;
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			List<Integer> levelList = new ArrayList<>();
			// 当前队列里的都是同一层的，挨个打印并入队下一层节点
			for (int i = queueSize; i > 0; i--) {
				TreeNode poll = queue.poll();
				levelList.add(poll.val);
				if (poll.left != null) {
					queue.offer(poll.left);
				}
				if (poll.right != null) {
					queue.offer(poll.right);
				}

			}
			if (reverse) {
				levelList = reverseList(levelList);
			}
			reverse = !reverse;
			res.add(levelList);
		}
		return res;
	}

	/**
	 * 返回链表的逆序链表
	 * @param originList
	 * @return
	 */
	List<Integer> reverseList(List<Integer> originList) {
		List<Integer> targetList = new ArrayList<>();
		for (int i = originList.size() - 1; i >= 0; i--) {
			targetList.add(originList.get(i));
		}
		return targetList;
	}

}
