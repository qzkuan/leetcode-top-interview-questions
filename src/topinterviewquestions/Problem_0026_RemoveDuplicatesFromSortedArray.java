package topinterviewquestions;

/**
 * [26.
 * 删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)
 * 给你一个 **非严格递增排列** 的数组 `nums` ，请你**[ 原地](http://baike.baidu.com/item/原地算法)**
 * 删除重复出现的元素，使每个元素 **只出现一次** ，返回删除后数组的新长度。元素的 **相对顺序** 应该保持 **一致** 。然后返回 `nums`
 * 中唯一元素的个数。
 */
public class Problem_0026_RemoveDuplicatesFromSortedArray {

	/**
	 * 删除有序数组中的重复项
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		if (nums == null) {
			return 0;
		}
		if (nums.length < 2) {
			return nums.length;
		}
		int done = 0; // [...done]表示已经放好的区域
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[done]) {
				nums[++done] = nums[i];
			}
		}
		return done + 1;
	}

	/**
	 * 删除有序数组中的重复项(一开始)
	 * 
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates2(int[] nums) {
		if (nums == null) {
			return 0;
		}
		if (nums.length < 2) {
			return nums.length;
		}
		int index = 0; // [...index-1]表示已经放好的区域
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] != nums[i]) {
				nums[index++] = nums[i];
			}
		}
		return index;
	}

}
