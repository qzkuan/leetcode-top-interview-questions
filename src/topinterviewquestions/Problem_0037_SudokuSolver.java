package topinterviewquestions;

import utils.ArrayUtils;

/**
 * [37. 解数独](https://leetcode.cn/problems/sudoku-solver/)
编写一个程序，通过填充空格来解决数独问题。
数独的解法需 **遵循如下规则**：
1. 数字 `1-9` 在每一行只能出现一次。
2. 数字 `1-9` 在每一列只能出现一次。
3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。（请参考示例图）
数独部分空格内已填入了数字，空白格用 `'.'` 表示。
 */
public class Problem_0037_SudokuSolver {

	public static void solveSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10];
		boolean[][] col = new boolean[9][10];
		boolean[][] bucket = new boolean[9][10];
		initMaps(board, row, col, bucket);
		process(board, 0, 0, row, col, bucket);
	}

	/**
	 * 初始化剪枝矩阵
	 * @param board 原始数独矩阵
	 * @param row 剪枝行矩阵，标识某一行哪些数字已被使用过，【行号】【数字】
	 * @param col 剪枝列矩阵，标识某一列哪些数字已被使用过，【列号】【数字】
	 * @param bucket 剪枝九宫格矩阵，标识某一个九宫格桶哪些数字已被使用过，【桶号】【数字】
	 */
	public static void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int bid = 3 * (i / 3) + (j / 3);
				if (board[i][j] != '.') {
					int num = board[i][j] - '0';
					row[i][num] = true;
					col[j][num] = true;
					bucket[bid][num] = true;
				}
			}
		}
	}

	/**
	 * 
	 * @param board 原始数独矩阵
	 * @param i
	 * @param j
	 * @param row 剪枝行矩阵，标识某一行哪些数字已被使用过，【行号】【数字】
	 * @param col 剪枝列矩阵，标识某一列哪些数字已被使用过，【列号】【数字】
	 * @param bucket 剪枝九宫格矩阵，标识某一个九宫格桶哪些数字已被使用过，【桶号】【数字】
	 * @return
	 */
	public static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
		if (i == 9) {
			System.out.println("该数独有解：");
			ArrayUtils.print2DArrayPretty(board);
			return true;
		}
		// 当前行后边还有数，继续当前行往后；没有了就换下一行，列重置0
		int nexti = j != 8 ? i : i + 1;
		int nextj = j != 8 ? j + 1 : 0;

		if (board[i][j] != '.') { // 当前位置不需要填数，继续下一个位置
			return process(board, nexti, nextj, row, col, bucket);
		} else {
			int bid = 3 * (i / 3) + (j / 3);
			for (int num = 1; num <= 9; num++) {
				// 行/列/九宫格都没出现过的数字才能填
				if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
					row[i][num] = true;
					col[j][num] = true;
					bucket[bid][num] = true;
					board[i][j] = (char) (num + '0');
					if (process(board, nexti, nextj, row, col, bucket)) {
						return true;
					}
					// 深度优先遍历没有返回，恢复现场
					row[i][num] = false;
					col[j][num] = false;
					bucket[bid][num] = false;
					board[i][j] = '.';
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, 
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, 
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, 
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		ArrayUtils.print2DArrayPretty(board);
		solveSudoku(board);
	}

}
