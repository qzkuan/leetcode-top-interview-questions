package topinterviewquestions;

/**
 * [189. 轮转数组](https://leetcode.cn/problems/rotate-array/)

给定一个整数数组 `nums`，将数组中的元素向右轮转 `k` 个位置，其中 `k` 是非负数。
示例 1:
输入: nums = [1,2,3,4,5,6,7], k = 3
输出:        [5,6,7,1,2,3,4]
 */
public class Problem_0189_RotateArray {

	public void rotate1(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		// a b c d e | g h
		reverse(nums, 0, N - k - 1);
		// e d c b a | g h
		reverse(nums, N - k, N - 1);
		// e d c b a | h g
		reverse(nums, 0, N - 1);
		// g h a b c | d e
	}

	/**
	 * 原地逆序数组
	 * @param nums 原始数组
	 * @param L 左边界
	 * @param R 右边界
	 */
	public static void reverse(int[] nums, int L, int R) {
		while (L < R) {
			int tmp = nums[L];
			nums[L++] = nums[R];
			nums[R--] = tmp;
		}
	}

	public static void rotate2(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		if (k == 0) {
			return;
		}
		int L = 0;
		int R = N - 1;
		int lpart = N - k;
		int rpart = k;
		int same = Math.min(lpart, rpart);
		int diff = lpart - rpart;
		exchange(nums, L, R, same);
		while (diff != 0) {
			if (diff > 0) {
				L += same;
				lpart = diff;
			} else {
				R -= same;
				rpart = -diff;
			}
			same = Math.min(lpart, rpart);
			diff = lpart - rpart;
			exchange(nums, L, R, same);
		}
	}

	public static void exchange(int[] nums, int start, int end, int size) {
		int i = end - size + 1;
		int tmp = 0;
		while (size-- != 0) {
			tmp = nums[start];
			nums[start] = nums[i];
			nums[i] = tmp;
			start++;
			i++;
		}
	}

}
