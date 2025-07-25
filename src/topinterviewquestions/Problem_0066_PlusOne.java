package topinterviewquestions;

/**
 * [66. 加一](https://leetcode.cn/problems/plus-one/)

给定一个表示 **大整数** 的整数数组 `digits`，其中 `digits[i]` 是整数的第 `i` 位数字。这些数字按从左到右，从最高位到最低位排列。这个大整数不包含任何前导 `0`。

将大整数加 1，并返回结果的数字数组。
 */
public class Problem_0066_PlusOne {

	public static int[] plusOne(int[] digits) {
		int n = digits.length;
		// 从后往前遍历（即从低位往高位）
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) { // 低位小于9，直接+1返回
				digits[i]++;
				return digits;
			}
			digits[i] = 0; // 低位==9，该位为0，进位1，继续往高位遍历
		}
		// digits全是99999，加一会多一位1，其余位为0
		int[] ans = new int[n + 1];
		ans[0] = 1;
		return ans;
	}

}
