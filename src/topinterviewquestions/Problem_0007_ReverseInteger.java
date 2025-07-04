package topinterviewquestions;

public class Problem_0007_ReverseInteger {

	/**
	 * [7. 整数反转](https://leetcode.cn/problems/reverse-integer/)
	 * 给你一个 32 位的有符号整数 `x` ，返回将 `x` 中的数字部分反转后的结果。
	 * 如果反转后整数超过 32 位的有符号整数的范围 `[−231, 231 − 1]` ，就返回 0。
	 * 假设环境不允许存储 64 位整数（有符号或无符号）。
	 * 
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
		// 判断x是否是负数，x >>> 31得到符号位
		boolean negative = ((x >>> 31) & 1) == 1;
		// 将x统一转换为负数处理
		x = negative ? x : -x;
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
			if (res < m || (res == m && x % 10 < o)) {
				return 0; // 溢出
			}
			// 弹出 x 的末尾数字 x%10 ，并放入res末尾位置
			res = res * 10 + x % 10;
			x /= 10;
		}
		return negative ? res : Math.abs(res);
	}

	/**
	 * 力扣官方解法
	 * https://leetcode.cn/problems/reverse-integer/solutions/755611/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
	 * 
	 * @param x
	 * @return
	 */
	public int reverseWithLeetcode(int x) {
		int rev = 0;
		while (x != 0) {
			if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			x /= 10;
			rev = rev * 10 + digit;
		}
		return rev;
	}

	public int reverse2(int x) {
		int result = 0;
		while (x != 0) {
			if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			result = result * 10 + digit;
			x = x / 10;
		}
		return result;
	}
}
