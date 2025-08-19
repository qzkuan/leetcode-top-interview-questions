package topinterviewquestions;

import java.util.HashSet;

/**
 * [217. 存在重复元素](https://leetcode.cn/problems/contains-duplicate/)

给你一个整数数组 `nums` 。如果任一值在数组中出现 **至少两次** ，返回 `true` ；如果数组中每个元素互不相同，返回 `false` 。
 */
public class Problem_0217_ContainsDuplicate {

	public boolean containsDuplicate1(int[] nums) {
		if (nums == null || nums.length < 2) {
			return false;
		}
		heapSort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return true;
			}
		}
		return false;
	}

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		int heapSize = arr.length;
		swap(arr, 0, --heapSize);
		while (heapSize > 0) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
	}

	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) {
				break;
			}
			swap(arr, largest, index);
			index = largest;
			left = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public boolean containsDuplicate2(int[] nums) {
		if (nums == null || nums.length < 2) {
			return false;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	class Solution {

		/**
		 * 利用堆排序算法
		 * @param nums
		 * @return
		 */
		public boolean containsDuplicate(int[] nums) {
			heapSort(nums);
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] == nums[i - 1]) {
					return true;
				}
			}
			return false;
		}
		/**
		 * 堆排序算法-大根堆方式实现
		 * 
		 * @param nums
		 * @param k
		 * @return
		 */
		public void heapSort(int[] nums) {
			int n = nums.length;
			// 建立最大堆，从最后一个非叶结点开始 n/2-1 下滤
			for (int i = n / 2 - 1; i >= 0; i--) {
				siftDownAsMaxHeap(nums, n, i);
			}
			// 上边只是确保了最大的在位，还要依次调整剩余的
			// 逐步取出堆顶元素，放到数组末尾，并重新调整堆结构
			for (int i = n - 1; i > 0; i--) {
				// 将当前堆顶元素（最大值）与数组末尾元素交换
				swap(nums, 0, i);
				// 调整堆结构，确保剩余元素仍满足最大堆的性质
				siftDownAsMaxHeap(nums, i, 0);
			}
		}

		/**
		 * 调整大根堆，在区间 [0,n) 上 非常重要☆☆☆
		 * 
		 * @param array    待排序数组
		 * @param heapSize 参与堆排序的节点个数
		 * @param i        需要下滤的起始索引
		 */
		private void siftDownAsMaxHeap(int[] array, int heapSize, int i) {
			int largest = i; // 假设当前父节点最大
			int left = (i << 1) + 1; // 2 * i + 1 理论上左子节点索引
			int right = (i << 1) + 2; // 2 * i + 2 理论上右子节点索引
			// 如果左子节点存在且大于父节点
			if (left < heapSize && array[left] > array[largest]) {
				largest = left;
			}
			// 如果右子节点存在且大于父节点和左子节点
			if (right < heapSize && array[right] > array[largest]) {
				largest = right;
			}
			// 如果最大值不是父节点，则交换父节点和最大值，并递归调整被影响的子树
			if (largest != i) {
				swap(array, i, largest);
				// 递归调整被影响的子树
				siftDownAsMaxHeap(array, heapSize, largest);
			}
		}

		/**
		 * 交换数组两位置元素
		 */
		private static void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}

		public boolean containsDuplicateSet(int[] nums) {
			HashSet<Integer> hashSet = new HashSet<>();
			for (int num : nums) {
				if (hashSet.contains(num)) {
					return true;
				}
				hashSet.add(num);
			}
			return false;
		}
	}

}
