package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

你必须设计并实现时间复杂度为 `O(n)` 的算法解决此问题。
 */
public class Problem_0215_KthLargestElementInAnArray {

	public int findKthLargest(int[] nums, int k) {
		return minKth(nums, nums.length + 1 - k);
	}

	public static int minKth(int[] arr, int k) {
		return process(arr, 0, arr.length - 1, k - 1);
	}

	public static int process(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[L];
		}
		int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return process(arr, L, range[0] - 1, index);
		} else {
			return process(arr, range[1] + 1, R, index);
		}
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;
	}

	class Solution {

		/**
		 * 利用大根堆方式实现
		 * @param nums
		 * @param k
		 * @return
		 */
		public int findKthLargest(int[] nums, int k) {
			int n = nums.length;
	
			// 建立最大堆，从最后一个非叶结点开始 n/2-1 下滤
			for (int i = n / 2 - 1; i >= 0; i--) {
				heapify3(nums, n, i);
			}
	
			// 逐步取出堆顶元素，放到数组末尾，并重新调整堆结构
			for (int i = n - 1; i > n-k; i--) {
				// 将当前堆顶元素（最大值）与数组末尾元素交换
				int temp = nums[0];
				nums[0] = nums[i];
				nums[i] = temp;
	
				// 调整堆结构，确保剩余元素仍满足最大堆的性质
				heapify3(nums, i, 0);
			}
			return nums[0];
		}
		/**
		 * 调整大根堆，在区间 [0,n) 上
		 * @param array 待排序数组
		 * @param n 参与堆排序的节点个数
		 * @param i 需要下滤的起始索引
		 */
		private void heapify3(int[] array, int n, int i) {
			int largest = i; // 假设当前父节点最大
			int left = 2 * i + 1; // 左子节点索引 (i << 1) + 1
			int right = 2 * i + 2; // 右子节点索引
	
			// 如果左子节点存在且大于父节点
			if (left < n && array[left] > array[largest]) {
				largest = left;
			}
	
			// 如果右子节点存在且大于父节点和左子节点
			if (right < n && array[right] > array[largest]) {
				largest = right;
			}
	
			// 如果最大值不是父节点，则交换父节点和最大值，并递归调整被影响的子树
			if (largest != i) {
				int swap = array[i];
				array[i] = array[largest];
				array[largest] = swap;
	
				// 递归调整被影响的子树
				heapify3(array, n, largest);
			}
		}
	
		/**
		 * 利用大根堆方式实现
		 * 
		 * @param nums
		 * @param k
		 * @return
		 */
		public int findKthLargest2(int[] nums, int k) {
			int n = nums.length;
			// 建立最大堆，从最后一个非叶结点开始 n/2-1 下滤
			for (int i = n / 2 - 1; i >= 0; i--) {
				siftDownAsMaxHeap(nums, n, i);
			}
			// 逐步取出堆顶元素，放到数组末尾，并重新调整堆结构
			for (int i = n - 1; i > n - k; i--) {
				// 将当前堆顶元素（最大值）与数组末尾元素交换
				swap(nums, 0, i);
				// 调整堆结构，确保剩余元素仍满足最大堆的性质
				siftDownAsMaxHeap(nums, i, 0);
			}
			return nums[0]; // 返回当前大根堆的堆顶元素
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


 
		/**
		 * 快速选择
		 * 快速排序的核心包括“哨兵划分” 和 “递归” 。
    哨兵划分： 以数组某个元素（一般选取首元素）为基准数，将所有小于基准数的元素移动至其左边，大于基准数的元素移动至其右边。
    递归： 对 左子数组 和 右子数组 递归执行 哨兵划分，直至子数组长度为 1 时终止递归，即可完成对整个数组的排序。
		 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array/solutions/2361969/215-shu-zu-zhong-de-di-k-ge-zui-da-yuan-d786p/
		 * 
		 * @param nums
		 * @param k
		 * @return
		 */
		public int findKthLargest4(int[] nums, int k) {
			List<Integer> numList = new ArrayList<>();
			for (int num : nums) {
				numList.add(num);
			}
			return quickSelect(numList, k);
		}

		/**
		 * 快速选择nums链表的第k大元素
		 * @param nums
		 * @param k
		 * @return
		 */
		private int quickSelect(List<Integer> nums, int k) {
			// 随机选择基准数
			Random rand = new Random();
			int pivot = nums.get(rand.nextInt(nums.size()));
			// 将大于、小于、等于 pivot 的元素划分至 big, small, equal 中
			List<Integer> big = new ArrayList<>();
			List<Integer> equal = new ArrayList<>();
			List<Integer> small = new ArrayList<>();
			for (int num : nums) {
				if (num > pivot)
					big.add(num);
				else if (num < pivot)
					small.add(num);
				else
					equal.add(num);
			}
			// 第 k 大元素在 big 中，递归划分
			if (k <= big.size())
				return quickSelect(big, k);
			// 第 k 大元素在 small 中，递归划分
			if (nums.size() - small.size() < k)
				return quickSelect(small, k - nums.size() + small.size());
			// 第 k 大元素在 equal 中，直接返回 pivot
			return pivot;
		}

	}

}
