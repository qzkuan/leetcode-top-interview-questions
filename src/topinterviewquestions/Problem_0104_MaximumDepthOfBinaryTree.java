package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)

给定一个二叉树 `root` ，返回其最大深度。

二叉树的 **最大深度** 是指从根节点到最远叶子节点的最长路径上的节点数。
 */
public class Problem_0104_MaximumDepthOfBinaryTree {

	/*
	 * 注意最小高度比这个复杂，要额外小心判断空
	 * */
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	/**
	 * 层序遍历（借用辅助队列）
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepthByLevel(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int level=0;
		while (!queue.isEmpty()) {
			level++;
			int queueSize = queue.size();
			// List<Integer> levelList = new ArrayList<>();
			// 当前队列里的都是同一层的，挨个打印并入队下一层节点
			for (int i = queueSize; i > 0; i--) {
				TreeNode poll = queue.poll();
				// levelList.add(poll.val);
				if (poll.left != null) {
					queue.offer(poll.left);
				}
				if (poll.right != null) {
					queue.offer(poll.right);
				}

			}
			// res.add(levelList);
		}
		return level;
	}

}
