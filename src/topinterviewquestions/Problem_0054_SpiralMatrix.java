package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。
 */
public class Problem_0054_SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return ans;
		}
		int a = 0;
		int b = 0;
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a <= c && b <= d) {
			addEdge(matrix, a++, b++, c--, d--, ans);
		}
		return ans;
	}

	/**
	 * 分解打印一圈
	 * @param m 矩阵
	 * @param a 左上点 行坐标
	 * @param b 左上点 列坐标
	 * @param c 右下点 行坐标
	 * @param d 右下点 列坐标
	 * @param ans 结果集
	 */
	public static void addEdge(int[][] m, int a, int b, int c, int d, List<Integer> ans) {
		if (a == c) { // 一条横行
			for (int i = b; i <= d; i++) {
				ans.add(m[a][i]);
			}
		} else if (b == d) { // 一条竖列
			for (int i = a; i <= c; i++) {
				ans.add(m[i][b]);
			}
		} else { // 四方框 分解成四块 → ↓ ← ↑ 打印
			int curC = b;
			int curR = a;
			while (curC != d) {
				ans.add(m[a][curC]);
				curC++;
			}
			while (curR != c) {
				ans.add(m[curR][d]);
				curR++;
			}
			while (curC != b) {
				ans.add(m[c][curC]);
				curC--;
			}
			while (curR != a) {
				ans.add(m[curR][b]);
				curR--;
			}
		}
	}

}
