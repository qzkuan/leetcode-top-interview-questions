package topinterviewquestions;

/**
 * [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/)
 * 
 * 给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Problem_0042_TrappingRainWater {

	/**
	 * 接雨水
	 * 
	 * @param arr
	 * @return
	 */
	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int N = arr.length;
		int L = 1; // 左边从第二个位置开始，第一个位置肯定为0
		int leftMax = arr[0]; // [0, cur-1]位置的最大值
		int R = N - 2; // 右边从倒数第二个位置开始，倒数第一个位置肯定为0
		int rightMax = arr[N - 1]; // [cur+1, arr.length-1]位置的最大值
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) { // 短板在左边，可以先结算左边的雨水
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else { // 短板在右边，可以先结算右边的雨水
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
