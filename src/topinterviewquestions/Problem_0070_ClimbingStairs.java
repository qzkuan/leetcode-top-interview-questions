package topinterviewquestions;

/**
 * [70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

假设你正在爬楼梯。需要 `n` 阶你才能到达楼顶。

每次你可以爬 `1` 或 `2` 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Problem_0070_ClimbingStairs {

	/**
	 * 状态定义： 设 dp 为一维数组，其中 dp[i] 的值代表斐波那契数列的第 i 个数字。
	 * 转移方程： dp[i+1]=dp[i]+dp[i−1] ，即对应数列定义 f(n+1)=f(n)+f(n−1) 。
	 * 初始状态： dp[0]=1, dp[1]=1 ，即初始化前两个数字。
	 * 返回值： dp[n] ，即斐波那契数列的第 n 个数字。
	 * 
	 * https://leetcode.cn/problems/climbing-stairs/solutions/2361764/70-pa-lou-ti-dong-tai-gui-hua-qing-xi-tu-ruwa/
	 */
	class Solution {
		/**
		 * 动态规划解法（空间复杂度O(1)）
		 * @param n
		 * @return
		 */
		public int climbStairs(int n) {
			int a = 1, b = 1, sum;
			for(int i = 0; i < n - 1; i++){
				sum = a + b;
				a = b;
				b = sum;
			}
			return b;
		}

		/**
		 * 动态规划解法(常规版)
		 * 
		 * @param n
		 * @return
		 */
		public int climbStairs2(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2) {
				return n;
			}
			int dp[] = new int[n];
			dp[0] = 1;
			dp[1] = 2;
			for (int i = 2; i < n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}
			return dp[n - 1];
		}
	}
	

	/**
	 * 矩阵快速幂解法
	 * @param n
	 * @return
	 */
    public static int climbStairs(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int[][] res = matrixPower(base, n - 2);
		return 2 * res[0][0] + res[1][0];
	}
    
	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		
		// res = 矩阵中的1
		int[][] tmp = m;// 矩阵1次方
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}

	// 两个矩阵乘完之后的结果返回
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}

}

