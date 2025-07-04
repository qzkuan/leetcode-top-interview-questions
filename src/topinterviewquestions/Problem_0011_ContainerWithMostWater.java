package topinterviewquestions;

public class Problem_0011_ContainerWithMostWater {
	/**
	 * 11. 盛最多水的容器 https://leetcode.cn/problems/container-with-most-water/
	 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
	 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 * 返回容器可以储存的最大水量。
	 * 
	 * @param h
	 * @return
	 */
	public static int maxArea(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

	public int maxAreaWithLeetcode(int[] height) {
		int low = 0;
		int high = height.length - 1;
		int maxArea = 0;
		while (low < high) {
			int area = Math.min(height[low], height[high]) * (high - low);
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[low] < height[high]) {
				low++;
			} else {
				high--;
			}
		}
		return maxArea;
	}

}
