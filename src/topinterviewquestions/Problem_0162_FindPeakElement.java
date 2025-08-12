package topinterviewquestions;

/**
 * [162. 寻找峰值](https://leetcode.cn/problems/find-peak-element/)
峰值元素是指其值严格大于左右相邻值的元素。
给你一个整数数组 `nums`，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 **任何一个峰值** 所在位置即可。
你可以假设 `nums[-1] = nums[n] = -∞` 。
你必须实现时间复杂度为 `O(log n)` 的算法来解决此问题。
 */
public class Problem_0162_FindPeakElement {

	public static int findPeakElement(int[] nums) {
		int N = nums.length;
		if (N < 2) {
			return 0;
		}
		if (nums[0] > nums[1]) {
			return 0; // 0位置数大于左右两边
		}
		if (nums[N - 2] < nums[N - 1]) {
			return N - 1; // N-1位置数大于左右两边
		}
		// 二分查找，每次看中点 M 位置数是不是峰值元素
		int L = 1;
		int R = N - 2;
		int M = 0;
		while (L < R) {
			M = (L + R) / 2;
			// M = L + (R - L) / 2;
			if (nums[M - 1] < nums[M] && nums[M] > nums[M + 1]) {
				return M;
			} else if (nums[M - 1] > nums[M]) {
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		return L;
	}

}
