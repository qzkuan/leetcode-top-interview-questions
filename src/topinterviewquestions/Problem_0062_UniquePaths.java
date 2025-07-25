package topinterviewquestions;

/**
 * [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

一个机器人位于一个 `m x n` 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？
 */
public class Problem_0062_UniquePaths {

	/**
	 * 求不同路径
	 * 其实 就是排列组合 C
	 * @param m
	 * @param n
	 * @return
	 */
	public static int uniquePaths(int m, int n) {
		int part = n - 1;
		int all = m + n - 2;
		long o1 = 1;
		long o2 = 1;
		for (int i = part + 1, j = 1; i <= all || j <= all - part; i++, j++) {
			o1 *= i;
			o2 *= j;
			long gcd = gcd(o1,o2);
			o1 /= gcd;
			o2 /= gcd;
		}
		return (int)o1;
	}
	
	// 调用的时候，请保证初次调用时，m和n都不为0
	/**
	 * 求两个数的最大公约数
	 * @param m 21
	 * @param n 70
	 * @return  7
	 */
	public static long gcd(long m, long n) {
		if (n == 0) {
			return m;
		} else {
			System.out.printf("m:%3s, n:%3s\n", m, n);
			return gcd(n, m % n);
		}
		// 原始写法
		// return n == 0 ? m : gcd(n, m % n);
	}

	public static void main(String[] args) {
		long m = 21L;
		long n = 70L;
		long gcd = gcd(m, n);
		System.out.println(gcd);
	}

}
