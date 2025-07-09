package topinterviewquestions;

/**
 * [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)
 * 给你一个按照非递减顺序排列的整数数组 `nums`，和一个目标值 `target`。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 `target`，返回 `[-1, -1]`。
 * 你必须设计并实现时间复杂度为 `O(log n)` 的算法解决此问题。
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

	public static int[] searchRange(int[] nums, int target) {
		int[] ans = { -1, -1 };
		if (nums == null || nums.length == 0) {
			return ans;
		}
		ans[0] = findFirst(nums, target);
		ans[1] = findLast(nums, target);
		return ans;
	}

	/**
	 * 寻找最左的num位置
	 * @param arr
	 * @param num
	 * @return
	 */
	public static int findFirst(int[] arr, int num) {
		int L = 0;
		int R = arr.length - 1;
		int ans = -1;
		int mid = 0;
		while (L <= R) {
			mid = L + ((R - L) >> 1);
			if (arr[mid] < num) {
				L = mid + 1;
			} else if (arr[mid] > num) {
				R = mid - 1;
			} else {
				ans = mid;
				R = mid - 1; //找到num之后继续往左找，直至最后
			}
		}
		return ans;
	}

	/**
	 * 寻找最右的num位置
	 * @param arr
	 * @param num
	 * @return
	 */
	public static int findLast(int[] arr, int num) {
		int L = 0;
		int R = arr.length - 1;
		int ans = -1;
		int mid = 0;
		while (L <= R) {
			mid = L + ((R - L) >> 1);
			if (arr[mid] < num) {
				L = mid + 1;
			} else if (arr[mid] > num) {
				R = mid - 1;
			} else {
				ans = mid;
				L = mid + 1; //找到num之后继续往右找，直至最后
			}
		}
		return ans;
	}

}
