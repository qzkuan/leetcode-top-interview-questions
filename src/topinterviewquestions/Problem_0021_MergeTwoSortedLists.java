package topinterviewquestions;

/**
 * [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Problem_0021_MergeTwoSortedLists {

	// public static class ListNode {
	// 	public int val;
	// 	public ListNode next;
	// }
	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	/**
	 * 合并两个有序链表
	 * 
	 * @param l1 [1,2,4]
	 * @param l2 [1,3,4]
	 * @return [1,1,2,3,4,4]
	 */
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode head = l1.val <= l2.val ? l1 : l2;
		ListNode cur1 = head.next;
		ListNode cur2 = head == l1 ? l2 : l1;
		ListNode pre = head;
		while (cur1 != null && cur2 != null) {
			if (cur1.val <= cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1 != null ? cur1 : cur2;
		return head;
	}

	/**
	 * LeetCode官方解法
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
		// 由于初始状态合并链表中无节点，因此循环第一轮时无法将节点添加到合并链表中。
		// 解决方案：初始化一个辅助节点 prehead 作为合并链表的伪头节点，将各节点添加至 prehead 之后。
		ListNode prehead = new ListNode(-1);

		ListNode curNode = prehead;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				curNode.next = list1;
				list1 = list1.next;
			} else {
				curNode.next = list2;
				list2 = list2.next;
			}
			curNode = curNode.next;
		}

		// 合并后 list1 和 list2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
		curNode.next = list1 == null ? list2 : list1;

		return prehead.next;
	}

}
