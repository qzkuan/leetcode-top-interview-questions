package topinterviewquestions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [23. 合并 K 个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Problem_0023_MergeKSortedLists {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	/**
	 * 小根堆比较器
	 */
	public static class ListNodeComparator implements Comparator<ListNode> {

		@Override
		public int compare(ListNode o1, ListNode o2) {
			return o1.val - o2.val;
		}

	}

	/**
	 * 合并K个升序列表
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
		// 小根堆结构
		PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
		// 所有链表头结点先入小根堆
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(lists[i]);
			}
		}
		if (heap.isEmpty()) {
			return null;
		}
		// 先弹出第一个节点，即所有节点里最小的那个
		ListNode head = heap.poll();
		ListNode pre = head;
		if (pre.next != null) {
			heap.add(pre.next); // 压入弹出节点的下一个节点
		}
		// 不断弹出小根堆节点加入结果链表里，并压入弹出节点的下一个节点
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;
			pre = cur;
			if (cur.next != null) {
				heap.add(cur.next);
			}
		}
		return head;
	}
	/**
	 * queue队列基本方法
	 * 
	 * ‌add(E element)‌：将元素添加到队列尾部，若队列已满则抛出异常。
	 * ‌offer(E element)‌：尝试将元素添加到队列尾部，若成功返回true，失败则返回false。
	 * ‌remove()‌：移除并返回队列头部的元素，若队列为空则抛出异常。 ‌
	 * ‌poll()‌：移除并返回队列头部的元素，若队列为空则返回null。 ‌
	 * ‌element()‌：返回队列头部的元素但不移除，若为空则抛出异常。 ‌
	 * ‌peek()‌：返回队列头部的元素但不移除，若为空则返回null。 ‌
	 * 
	 * ‌isEmpty()‌：检查队列是否为空。 ‌
	 */

}
