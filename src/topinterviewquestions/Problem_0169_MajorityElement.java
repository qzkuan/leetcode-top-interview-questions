package topinterviewquestions;

/**
 * [169. 多数元素](https://leetcode.cn/problems/majority-element/)

给定一个大小为 `n` 的数组 `nums` ，返回其中的多数元素。多数元素是指在数组中出现次数 **大于** `⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Problem_0169_MajorityElement {

	public static int majorityElement(int[] nums) {
		int cand = 0; // 靶子
		int HP = 0; // 血量
		for (int i = 0; i < nums.length; i++) {
			if (HP == 0) {
				cand = nums[i];
				HP = 1;
			} else if (nums[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		return cand;
	}

	class Solution {
		public int majorityElement(int[] nums) {
			int candidate = 0; // 假设众数
			int votes = 0; // 票数统计
			for (int num : nums) {
				if (votes == 0) {
					candidate = num;
				}
				votes += (num == candidate) ? +1 : -1;
			}
			// return candidate;
			// 验证candidate是否真的为众数(如果存在没有众数的情况)
			int count = 0;
			for (int num : nums) {
				if (num == candidate) {
					count++;
				}
			}
			return (count > nums.length / 2) ? candidate : 0;
		}
	}

}
