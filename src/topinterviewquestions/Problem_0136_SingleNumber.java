package topinterviewquestions;

/**
 * [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

给你一个 **非空** 整数数组 `nums` ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class Problem_0136_SingleNumber {

	public static int singleNumber(int[] nums) {
		int eor = 0;
		// 两个相同数字异或为 0 ，即对于任意整数 a 有 a⊕a=0
		// 异或运算满足交换律 a⊕b=b⊕a
		for (int num : nums) {
			eor ^= num;
		}
		return eor;
	}

}
