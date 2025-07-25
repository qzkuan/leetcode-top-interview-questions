package topinterviewquestions;

/**
 * [73. 矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes/)

给定一个 `*m* x *n*` 的矩阵，如果一个元素为 **0** ，则将其所在行和列的所有元素都设为 **0** 。请使用 **[原地](http://baike.baidu.com/item/原地算法)** 算法**。**
 */
public class Problem_0073_SetMatrixZeroes {

	public static void setZeroes1(int[][] matrix) {
		boolean row0Zero = false;
		boolean col0Zero = false;
		int i = 0;
		int j = 0;
		// 先存储第一行是否要全变0，因为第一行会被征用去存储当前列是否要变0
		for (i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				row0Zero = true;
				break;
			}
		}
		// 先存储第一列是否要全变0，因为第一列会被征用去存储当前行是否要变0
		for (i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				col0Zero = true;
				break;
			}
		}
		// 从[1][1]位置开始遍历，征用第一行第一列做辅助变量存储需要变0的行列
		for (i = 1; i < matrix.length; i++) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// 当前已知到所有需要变0的位置
		// 先修改除了第一行/列之外的位置
		for (i = 1; i < matrix.length; i++) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// 再修改第一行/第一列的位置
		if (row0Zero) {
			for (i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}
		if (col0Zero) {
			for (i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

	public static void setZeroes2(int[][] matrix) {
		boolean col0 = false;
		int i = 0;
		int j = 0;
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					if (j == 0) {
						col0 = true;
					} else {
						matrix[0][j] = 0;
					}
				}
			}
		}
		for (i = matrix.length - 1; i >= 0; i--) {
			for (j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if (col0) {
			for (i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}

}
