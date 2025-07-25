package topinterviewquestions;

/**
 * [50. Pow(x, n)](https://leetcode.cn/problems/powx-n/)

实现 [pow(*x*, *n*)](https://www.cplusplus.com/reference/valarray/pow/) ，即计算 `x` 的整数 `n` 次幂函数（即，`xn` ）。
 */
public class Problem_0050_PowXN {

	/**
	 * x的n次方计算（1.00000001的-2147483648次方结果为：0.0）
	 * @param x 底数
	 * @param n 指数
	 * @return
	 */
	public static double myPow1(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		if (n == Integer.MIN_VALUE) { // 系统最小没法转成正数计算
			return (x == 1D || x == -1D) ? 1D : 0;
		}
		int pow = Math.abs(n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t; // 指数二进制位为1时，乘进结果
			}
			pow >>= 1;
			t = t * t;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	/**
	 * x的n次方计算（1.00000001的-2147483648次方结果为：4.716253725603543E-10✅）
	 * @param x 底数
	 * @param n 指数
	 * @return
	 */
	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	public static void main(String[] args) {
		System.out.println("world shut up!");
		int a = Integer.MIN_VALUE;
		int b = -a;
		System.out.println(b);

		System.out.println("==============");

		double test = 1.00000001D;
		int N = Integer.MIN_VALUE;
		System.out.println(test == 1D);
		System.out.println(test + "的" + N + "次方，结果：");
		System.out.println(Math.pow(test, (double) N));
		System.out.println(myPow1(test, N));
		System.out.println(myPow2(test, N));
	}

}
