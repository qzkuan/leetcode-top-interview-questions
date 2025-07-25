package topinterviewquestions;

/**
 * [75. 颜色分类](https://leetcode.cn/problems/sort-colors/)

给定一个包含红色、白色和蓝色、共 `n` 个元素的数组 `nums` ，**[原地](https://baike.baidu.com/item/原地算法)** 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 `0`、 `1` 和 `2` 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class Problem_0075_SortColors {
	public static void sortColors(int[] nums) {
		int less = -1;
		int more = nums.length;
		int index = 0;
		while (index < more) {
			if (nums[index] == 1) { 		// 1的时候不交换，只有当前指针+1
				index++;
			} else if (nums[index] == 0) {  // 0的时候，把0放到前边0区，当前指针+1
				swap(nums, index++, ++less);
			} else {						// 2的时候，把2放到后边的2区，当前指针不变
				swap(nums, index, --more);
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// https://leetcode.cn/problems/sort-colors/solutions/437968/yan-se-fen-lei-by-leetcode-solution/
	class Solution {
		/**
		 * 双指针法（p0,p2)
		 * @param nums
		 */
		public void sortColors2(int[] nums) {
			int n = nums.length;
			int p0 = 0;     // 0区域，从左往右
			int p2 = n - 1; // 2区域，从右往左
			for (int i = 0; i <= p2; ++i) {
				// 找到2，交换i放到2区，p2--，但是当前i不变，因为当前位置仍有可能是2
				while (i <= p2 && nums[i] == 2) {
					int temp = nums[i];
					nums[i] = nums[p2];
					nums[p2] = temp;
					--p2;
				}
				// 找到0，交换i放到0区，因为从左往右已经保证前边的是0/1，不需要while
				if (nums[i] == 0) {
					int temp = nums[i];
					nums[i] = nums[p0];
					nums[p0] = temp;
					++p0;
				}
			}
		}
	
		/**
		 * 双指针法（p0,p1）
		 * @param nums
		 */
		public void sortColors(int[] nums) {
			int n = nums.length;
			int p0 = 0, p1 = 0;
			for (int i = 0; i < n; ++i) {
				if (nums[i] == 1) {
					int temp = nums[i];
					nums[i] = nums[p1];
					nums[p1] = temp;
					++p1;
				} else if (nums[i] == 0) {
					int temp = nums[i];
					nums[i] = nums[p0];
					nums[p0] = temp;
					// if (nums[i] ==1) { // 当前位置换走的是1，自己的改法，这样也对
					if(p0 < p1){ // 1占了0的位置，1被换到了后边i，需要换回1区
						temp = nums[i];
						nums[i] = nums[p1];
						nums[p1] = temp;
					}
					++p0;
					++p1;
				}
			}
		}
	}

}
