package topinterviewquestions;

/**
 * [33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)
 * 整数数组 `nums` 按升序排列，数组中的值 ==互不相同== 。
 * 在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 ==旋转==，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 ==从 0 开始== 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。
 * 给你 ==旋转后== 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的下标，否则返回 `-1` 。
 * 你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。
 */
public class Problem_0033_SearchInRotatedSortedArray {

	public static int search(int[] arr, int num) {
		int L = 0;
		int R = arr.length - 1;
		int M = 0;
		while (L <= R) {
			M = (L + R) / 2;
			if (arr[M] == num) {
				return M;
			}
			// arr[M] != num
			if (arr[L] == arr[M] && arr[M] == arr[R]) {
				while (L != M && arr[L] == arr[M]) {
					L++;
				}
				// L和M没撞上，[L]!=[M] L,.....M
				if (L == M) {
					L = M + 1;
					continue;
				}
			}
			// arr[M] != num
			// [L] [M] [R] 不都一样的情况
			if (arr[L] != arr[M]) {
				if (arr[M] > arr[L]) {
					if (num >= arr[L] && num < arr[M]) {
						R = M - 1;
					} else {
						L = M + 1;
					}
				} else { //  [L]  >  [M]
					if (num > arr[M] && num <= arr[R]) {
						L = M + 1;
					} else {
						R = M - 1;
					}
				}
			} else { // [L] === [M] ->  [M]!=[R]
				if (arr[M] < arr[R]) {
					if (num > arr[M] && num <= arr[R]) {
						L = M + 1;
					} else {
						R = M - 1;
					}
				} else {
					if (num >= arr[L] && num < arr[M]) {
						R = M - 1;
					} else {
						L = M + 1;
					}
				}
			}
		}
		return -1;
	}

}
