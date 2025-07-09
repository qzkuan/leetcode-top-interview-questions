package topinterviewquestions;

/**
 * [29. 两数相除](https://leetcode.cn/problems/divide-two-integers/)
 * 给你两个整数，被除数 `dividend` 和除数 `divisor`。将两数相除，要求 ==不使用== 乘法、除法和取余运算。
 * 整数除法应该向零截断，也就是截去（`truncate`）其小数部分。例如，`8.345` 将被截断为 `8` ，`-2.7335` 将被截断至 `-2` 。
 * 返回被除数 `dividend` 除以除数 `divisor` 得到的 ==商== 。
 * 注意：假设我们的环境只能存储 ==32 位== 有符号整数，其数值范围是 `[−231, 231 − 1]` 。本题中，如果商 ==严格大于== `231 − 1` ，则返回 `231 − 1` ；如果商 ==严格小于== `-231` ，则返回 `-231` 。
 */
public class Problem_0029_DivideTwoIntegers {

	/**
	 * ⭐️二进制方式实现加法运算
	 * 
	 * @param a
	 * @param b
	 * @return sum
	 */
	public static int add(int a, int b) {
		int sum = a;
		while (b != 0) { // 无进位信息时退出
			sum = a ^ b; // 无进位相加结果
			b = (a & b) << 1; // 进位信息
			a = sum; // 把sum当成a，进位信息当成b，继续异或和与运算
		}
		return sum;
	}

	/**
	 * 二进制求一个数的相反数（取反加一）
	 * 
	 * @param n
	 * @return
	 */
	public static int negNum(int n) {
		return add(~n, 1);
	}

	/**
	 * ⭐️二进制方式实现减法运算（本质是通过加法实现：a➕b的相反数）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int minus(int a, int b) {
		return add(a, negNum(b));
	}

	/**
	 * 二进制方式实现乘法运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) { // b最后一位是否为1
				res = add(res, a); // b最后一位为1时，把此时的a加到res结果里
			}
			a <<= 1; // a左移一位（其实就是末位补一个0）
			b >>>= 1; // b无符号右移（逻辑右移，即无论正负高位都补0）一位
		}
		return res;
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}

	/**
	 * 二进制方式实现除法运算（统一转成正数）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i > negNum(1); i = minus(i, 1)) {
			if ((x >> i) >= y) { // 相当于y左移，但左移有可能导致符号位变化
				res |= (1 << i); // 记录当前商的结果位
				x = minus(x, y << i); // 减掉当前位的除数
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	/**
	 * 二进制方式实现除法运算（含边界处理）
	 * 
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (divisor == Integer.MIN_VALUE) {
			return dividend == Integer.MIN_VALUE ? 1 : 0;
		}
		// 除数不是系统最小
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == negNum(1)) { // 系统最小值÷(-1)，溢出，返回系统最大
				return Integer.MAX_VALUE;
			}
			// (被除数+1)/(除数)
			int res = div(add(dividend, 1), divisor);
			// 补偿少算的：【被除数-（res*除数）】/【除数】
			return add(res, div(minus(dividend, multi(res, divisor)), divisor));
		}
		// dividend不是系统最小，divisor也不是系统最小
		return div(dividend, divisor);
	}
	// div(a,b) a和b都不能是系统最小

	// 整型数打印二进制函数
	public static String printNumBinary(int num) {
		StringBuilder builder = new StringBuilder();
		for (int i = 31; i >= 0; i--) {
			builder.append(((num >> i) & 1) == 0 ? '0' : '1');
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		int num = -1; // 11111111111111111111111111111111
		int num2=Integer.MIN_VALUE; // 10000000000000,-2^31次方
		System.out.println(printNumBinary(num));
	}

}
