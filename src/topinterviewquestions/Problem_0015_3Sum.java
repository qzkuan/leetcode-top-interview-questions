package topinterviewquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0015_3Sum {

	/**
	 * [15. 三数之和](https://leetcode.cn/problems/3sum/)
	 * 给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k`
	 * 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请你返回所有和为 `0` 且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum1(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		// 第一个数选了i位置的数
		// i < nums.length - 2 因为后边是二元组，要有足够的数
		for (int i = 0; i < nums.length - 2; i++) {
			// i位置的数与左边的数不同才有必要收集
			if (i == 0 || nums[i - 1] != nums[i]) {
				List<List<Integer>> nexts = twoSum1(nums, i + 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					// ArrayList往头位置插数据会导致数据后移浪费额外时间
					// 可以采用LinkedList或者从后往前遍历的方式规避cur.add(nums[i])
					cur.add(0, nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	// nums已经有序了
	// nums[begin......]范围上，找到累加和为target的所有二元组
	public static List<List<Integer>> twoSum1(int[] nums, int begin, int target) {
		int L = begin;
		int R = nums.length - 1;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				// nums[L] + nums[R] == target
				// nums[L]与左边的数不一样才收集（防止重复）
				if (L == begin || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				L++;
			}
		}
		return ans;
	}

	public static List<List<Integer>> threeSum2(int[] nums) {
		Arrays.sort(nums);
		int N = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = N - 1; i > 1; i--) {
			if (i == N - 1 || nums[i] != nums[i + 1]) {
				List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					cur.add(nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
		int L = 0;
		int R = end;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				if (L == 0 || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				L++;
			}
		}
		return ans;
	}

}
