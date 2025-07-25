package topinterviewquestions;

/**\
 * [105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

给定两个整数数组 `preorder` 和 `inorder` ，其中 `preorder` 是二叉树的**先序遍历**， `inorder` 是同一棵树的**中序遍历**，请构造二叉树并返回其根节点。
 */
import java.util.HashMap;

public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		// map方便每次从中序遍历中寻找头结点位置
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	/**
	 * 前序+中序递归构建二叉树
	 * 
	 * @param pre 前序遍历列表
	 * @param L1 当前树在前序中的左区间
	 * @param R1 当前树在前序中的右区间
	 * 
	 * @param in  中序遍历列表
	 * @param L2 当前树在中序中的左区间
	 * @param R2 当前树在中序中的右区间
	 * 
	 * @param map 中序遍历节点的位置信息map（加速查找）
	 * @return 构建完后的二叉树头结点
	 */
	public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
		if (L1 > R1) { // 空/只有左/只有右情况
			return null;
		}
		TreeNode head = new TreeNode(pre[L1]);
		if (L1 == R1) {
			return head;
		}
		int findIndex = map.get(pre[L1]); // 常规for循环查找O(n)，优化map查找O(1)
		head.left = f(pre, L1 + 1, L1 + findIndex - L2, in, L2, findIndex - 1, map);
		head.right = f(pre, L1 + findIndex - L2 + 1, R1, in, findIndex + 1, R2, map);
		return head;
	}

}
