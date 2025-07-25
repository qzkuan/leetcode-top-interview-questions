package topinterviewquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * [46. 全排列](https://leetcode.cn/problems/permutations/)

给定一个不含重复数字的数组 `nums` ，返回其 *所有可能的全排列* 。你可以 **按任意顺序** 返回答案。
 */
public class Problem_0046_Permutations {

	public static List<List<Integer>> onClass(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		HashSet<Integer> rest = new HashSet<>();
		for (int num : nums) {
			rest.add(num);
		}
		ArrayList<Integer> path = new ArrayList<>();
		f(rest, path, ans);
		return ans;
	}

	// rest中有剩余数字，已经选过的数字不在rest中，选过的数字在path里
	public static void f(HashSet<Integer> rest, ArrayList<Integer> path, List<List<Integer>> ans) {
		if (rest.isEmpty()) {
			ans.add(path);
		} else {
			for (int num : rest) {
				ArrayList<Integer> curPath = new ArrayList<>(path);
				curPath.add(num);
				HashSet<Integer> clone = cloneExceptNum(rest, num);
				f(clone, curPath, ans);
			}
		}
	}

	public static HashSet<Integer> cloneExceptNum(HashSet<Integer> rest, int num) {
		HashSet<Integer> clone = new HashSet<>(rest);
		clone.remove(num);
		return clone;
	}

	/**
	 * 全排列 最优解法
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		process(nums, 0, ans);
		return ans;
	}

	/**
	 * 全排列-置换解法
	 * @param nums 置换的结果
	 * @param index 当前置换的位置，往右不回头
	 * @param ans
	 */
	public static void process(int[] nums, int index, List<List<Integer>> ans) {
		if (index == nums.length) { // 路走到最后，收集结果
			ArrayList<Integer> cur = new ArrayList<>();
			for (int num : nums) {
				cur.add(num);
			}
			ans.add(cur);
		} else {
			// 从index位置往右，依次和index位置交换
			for (int j = index; j < nums.length; j++) {
				swap(nums, index, j); // 当前选中一条路
				process(nums, index + 1, ans); // 走下一条路
				swap(nums, index, j); // 恢复现场
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
