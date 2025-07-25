package topinterviewquestions;

/**
 * [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx/)

给你一个非负整数 `x` ，计算并返回 `x` 的 **算术平方根** 。

由于返回类型是整数，结果只保留 **整数部分** ，小数部分将被 **舍去 。**

**注意：**不允许使用任何内置指数函数和算符，例如 `pow(x, 0.5)` 或者 `x ** 0.5` 。
 */
public class Problem_0069_SqrtX {

	// x一定非负，输入可以保证
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		if (x < 3) {
			return 1;
		}
		long ans = 1; // 记录离开方结果最近的数
		long L = 1;
		long R = x;
		long M = 0;
		while (L <= R) { // 不断二分查找
			M = (L + R) / 2;
			if (M * M <= x) {
				ans = M; // 小于等于目标数且目前离得最近的数
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return (int) ans;
	}

}
