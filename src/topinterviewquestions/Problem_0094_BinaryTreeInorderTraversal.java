package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)

给定一个二叉树的根节点 `root` ，返回 *它的 **中序** 遍历* 。
 */
public class Problem_0094_BinaryTreeInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		TreeNode cur = root;
		TreeNode mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			ans.add(cur.val);
			cur = cur.right;
		}
		return ans;
	}

	/**
	 * 递归方式中序遍历
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversalRecursive(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		inorder(root, res);
		return res;
	}

	/**
	 * 中序打印所有节点
	 * 
	 * @param head 二叉树头结点
	 */
	public static void inorder(TreeNode root, List<Integer> res) {
		if (root == null) {
			return;
		}
		inorder(root.left, res);
		res.add(root.val);
		inorder(root.right, res);
	}
	/**
	 * 非递归/迭代方式中序遍历（左 根 右）
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversalUnRecursive(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root != null) { // 加这个过滤条件可以加速
			Stack<TreeNode> stack = new Stack<TreeNode>();
			while (!stack.isEmpty() || root != null) {
				if (root != null) { // 依次先把最左元素入栈
					stack.push(root);
					root = root.left;
				} else { // 出栈打印，并把当前结点的右子树入栈
					root = stack.pop();
					res.add(root.val);
					root = root.right;
				}
			}
		}
		return res;
	}

	/**
	 * 非递归/迭代方式中序遍历（左 根 右）
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversalUnRecursive2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root != null) { // 加这个过滤条件可以加速
			Stack<TreeNode> stack = new Stack<TreeNode>();
			while (!stack.isEmpty() || root != null) {
				while (root != null) { // 依次先把最左元素入栈
					stack.push(root);
					root = root.left;
				}
				// 出栈打印，并把当前结点的右子树入栈
				root = stack.pop();
				res.add(root.val);
				root = root.right;
			}
		}
		return res;
	}



}
