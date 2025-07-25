package topinterviewquestions;

/**
 * [79. 单词搜索](https://leetcode.cn/problems/word-search/)

给定一个 `m x n` 二维字符网格 `board` 和一个字符串单词 `word` 。如果 `word` 存在于网格中，返回 `true` ；否则，返回 `false` 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Problem_0079_WordSearch {

	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (process(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	// 目前到达了b[i][j]，word[k....]
	// 从b[i][j]出发，能不能搞定word[k....]  true  false
	public static boolean process(char[][] b, int i, int j, char[] w, int k) {
        if(k == w.length) {
			return true;
		}
        // k 有字符
		if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
			return false;
		}
		if (b[i][j] != w[k]) {
			return false;
		}
		// 标记当前位置已走过
		char tmp = b[i][j];
		b[i][j] = 0;
		boolean ans =  process(b, i - 1, j, w, k + 1) 
				|| process(b, i + 1, j, w, k + 1) 
				|| process(b, i, j - 1, w, k + 1)
				|| process(b, i, j + 1, w, k + 1);
		// 取消标记当前位置，恢复现场
		b[i][j] = tmp;
		return ans;
	}

}
