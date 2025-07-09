package topinterviewquestions;

/**
 * [36. 有效的数独](https://leetcode.cn/problems/valid-sudoku/)
 * 请你判断一个 `9 x 9` 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 1. 数字 `1-9` 在每一行只能出现一次。
 * 2. 数字 `1-9` 在每一列只能出现一次。
 * 3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。（请参考示例图）
 * 注意：
 * - 一个有效的数独（部分已被填充）不一定是可解的。
 * - 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * - 空白格用 `'.'` 表示。
 */
public class Problem_0036_ValidSudoku {

	public static boolean isValidSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10]; // [每一行]是否出现过[某个数]
		boolean[][] col = new boolean[9][10]; // [每一列]是否出现过[某个数]
		boolean[][] bucket = new boolean[9][10]; // [每一九宫格]是否出现过[某个数]
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int bid = 3 * (i / 3) + (j / 3); // 计算当前在第几个九宫格
				if (board[i][j] != '.') {
					int num = board[i][j] - '0';
					if (row[i][num] || col[j][num] || bucket[bid][num]) {
						return false;
					}
					row[i][num] = true;
					col[j][num] = true;
					bucket[bid][num] = true;
				}
			}
		}
		return true;
	}

	public static void main(String[] args){
		int i=2;
		int j=2;
		int bid = 3 * (i / 3) + (j / 3); // 结果为整数（会忽略余数）
		System.out.println(bid); // 0
	}

}
