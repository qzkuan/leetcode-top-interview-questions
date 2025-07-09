package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * [22. 括号生成](https://leetcode.cn/problems/generate-parentheses/)
 * 数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。
 */
public class Problem_0022_GenerateParentheses {

	/**
	 * 括号生成
	 * @param n 3
	 * @return  ["((()))", "(()())", "(())()", "()(())", "()()()"]
	 */
	public static List<String> generateParenthesis(int n) {
		char[] path = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process(path, 0, 0, n, ans);
		return ans;
	}

	/**
	 * 依次在path上填写决定
	 * ( ( ) ) ( )....
	 * 0 1 2 3 4 5
	 * @param path path[0...index-1]决定已经做完了
	 * @param index index位置上做决定 ( )
	 * @param leftMinusRight 已经做过决定的区域左括号 - 右括号的数量
	 * @param leftRest 剩余多少左括号可做决定
	 * @param ans
	 */
	public static void process(char[] path, int index, int leftMinusRight, int leftRest, List<String> ans) {
		if (index == path.length) {
			ans.add(String.valueOf(path));
		} else {
			if (leftRest > 0) {
				path[index] = '(';
				process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
			}
			if (leftMinusRight > 0) {
				path[index] = ')';
				process(path, index + 1, leftMinusRight - 1, leftRest, ans);
			}
		}
	}

	/**
	 * 递归不剪枝的做法
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis2(int n) {
		char[] path = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process2(path, 0, ans);
		return ans;
	}
	/**
	 * 递归不剪枝的处理
	 * @param path
	 * @param index
	 * @param ans
	 */
	public static void process2(char[] path, int index, List<String> ans) {
		if (index == path.length) {
			if (isValid(path)) {
				ans.add(String.valueOf(path));
			}
		} else {
			path[index] = '(';
			process2(path, index + 1, ans);
			path[index] = ')';
			process2(path, index + 1, ans);
		}
	}
	/**
	 * 括号合法性校验
	 * @param path
	 * @return
	 */
	public static boolean isValid(char[] path) {
		int count = 0;
		for (char cha : path) {
			if (cha == '(') {
				count++;
			} else {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

}
