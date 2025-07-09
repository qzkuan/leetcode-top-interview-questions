package topinterviewquestions;

/**
 * [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)
 * 给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。
 */
public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}
	/**
	 * 删除链表的倒数第 `n` 个结点，并且返回链表的头结点
	 * @param head [1,2,3,4,5]
	 * @param n    2
	 * @return     [1,2,3,5]
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			n--;
			if (n == -1) { // cur多走一个才设置pre，因为要知道删除节点的前一个节点
				pre = head;
			}
			if (n < -1) { // pre和cur保持n+1距离，同步往下遍历
				pre = pre.next;
			}
			cur = cur.next;
		}
		if (n > 0) { // 不够删除的，直接返回head
			return head;
		}
		if (pre == null) { // 要删除的是头结点，返回head.next
			return head.next;
		}
		pre.next = pre.next.next; // 删除倒数第n个位置元素（Java会自动回收♻️）
		return head;
	}
}

class ListNode {
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

class Solution {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = new ListNode();
		pre.next = head;
		ListNode slow = pre;
		ListNode fast = pre;
		while (n > 0) {
			fast = fast.next;
			n--;
		}
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
		return pre.next;
	}
}