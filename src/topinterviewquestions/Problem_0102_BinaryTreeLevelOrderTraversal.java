package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

给你二叉树的根节点 `root` ，返回其节点值的 **层序遍历** 。 （即逐层地，从左到右访问所有节点）。
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		LinkedList<TreeNode> deque = new LinkedList<>();
		deque.add(root);
		int size = 0;
		while(!deque.isEmpty()) {
			size = deque.size();
			List<Integer> curLevel = new ArrayList<>();
			for(int i = 0 ; i< size;i++) {
				TreeNode cur = deque.pollLast();
				curLevel.add(cur.val);
				if(cur.left != null) {
					deque.addFirst(cur.left);
				}
				if(cur.right != null) {
					deque.addFirst(cur.right);
				}
			}
			ans.add(curLevel);
		}
		return ans;
	}

	/**
	 * 层序遍历（借用辅助队列）
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderKuan(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
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
			res.add(levelList);
		}
		return res;
	}

}
