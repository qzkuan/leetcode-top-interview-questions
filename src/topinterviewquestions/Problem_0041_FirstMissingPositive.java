package topinterviewquestions;

/**
 * [41. 缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive/)

给你一个未排序的整数数组 `nums` ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 `O(n)` 并且只使用常数级别额外空间的解决方案。
 */
public class Problem_0041_FirstMissingPositive {
	/**
	 * 寻找缺失的第一个正数
	 * @param arr
	 * @return
	 */
    public static int firstMissingPositive(int[] arr) {
		int l = 0; // 最好预期情况下，l位置应该放着l+1这个数
		int r = arr.length; // 最好预期情况下，尽可能大的最小正整数
		while (l < r) {
			if (arr[l] == l + 1) {
				l++;
			} else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
				// arr[l] <= l 小于于最好预期[l+1,r]内，无效数字
				// arr[l] > r 大于最好预期[l+1,r]内，无效数字
				// arr[arr[l] - 1] == arr[l] 在最好预期内，且该数字已经在预期位置上了，无效
				swap(arr,l,--r); // arr[l]发送到垃圾区，最好预期-1
			} else {
				// arr[l]在最好预期内，且该数字的预期位置上不是自己，
				swap(arr, l, arr[l] - 1); // 将该数与它预期位置上的？数交换
			}
		}
		return l + 1;
	}

	/**
	 * 交换arr数组的两个位置的数
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
