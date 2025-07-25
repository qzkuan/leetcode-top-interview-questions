package topinterviewquestions;

/**\
 * [106. 从中序与后序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

给定两个整数数组 `inorder` 和 `postorder` ，其中 `inorder` 是二叉树的中序遍历， `postorder` 是同一棵树的后序遍历，请你构造并返回这颗 *二叉树* 。
 */
import java.util.HashMap;

public class Problem_0106_ConstructBinaryTreeFromInorderAndPostOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// map方便每次从中序遍历中寻找头结点位置
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return f(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	/**
	 * 后序+中序递归构建二叉树
	 * 
	 * @param post 后序遍历列表
	 * @param L1   当前树在后序中的左区间
	 * @param R1   当前树在后序中的右区间
	 * 
	 * @param in   中序遍历列表
	 * @param L2   当前树在中序中的左区间
	 * @param R2   当前树在中序中的右区间
	 * 
	 * @param map  中序遍历节点的位置信息map（加速查找）
	 * @return 构建完后的二叉树头结点
	 */
	public static TreeNode f(int[] post, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
		if (L1 > R1) { // 空/只有左/只有右情况
			return null;
		}
		TreeNode head = new TreeNode(post[R1]);
		if (L1 == R1) {
			return head;
		}
		int findIndex = map.get(post[R1]); // 常规for循环查找O(n)，优化map查找O(1)
		head.left = f(post, L1, L1 + findIndex - L2 - 1, in, L2, findIndex - 1, map);
		head.right = f(post, L1 + findIndex - L2, R1 - 1, in, findIndex + 1, R2, map);
		return head;
	}

}
