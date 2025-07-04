package topinterviewquestions;

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

	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i > negNum(1); i = minus(i, 1)) {
			if ((x >> i) >= y) {
				res |= (1 << i);
				x = minus(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	public static int divide(int dividend, int divisor) {
		if (divisor == Integer.MIN_VALUE) {
			return dividend == Integer.MIN_VALUE ? 1 : 0;
		}
		// 除数不是系统最小
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == negNum(1)) {
				return Integer.MAX_VALUE;
			}
			int res = div(add(dividend, 1), divisor);
			return add(res, div(minus(dividend, multi(res, divisor)), divisor));
		}
		// dividend不是系统最小，divisor也不是系统最小
		return div(dividend, divisor);
	}
	// div(a,b) a和b都不能是系统最小

	// 现场福利函数
	public static String printNumBinary(int num) {
		StringBuilder builder = new StringBuilder();
		for (int i = 31; i >= 0; i--) {
			builder.append(((num >> i) & 1) == 0 ? '0' : '1');
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		int num = -1;
		System.out.println(printNumBinary(num));
	}

}
