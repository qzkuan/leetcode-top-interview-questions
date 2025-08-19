package topinterviewquestions;

/**
 * 
[206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。
 */
public class Problem_0206_ReverseLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	class Solution {
		/**
		 * 迭代法（双指针）
		 * @param head
		 * @return
		 */
		public ListNode reverseList(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			ListNode cur = head;
			ListNode tmp = null;
			ListNode res = null;
			while (cur != null) {
				tmp = cur.next; // 暂存下一个结点，放置断链
				cur.next = res; // 翻转当前结点指针
				res = cur;
				cur = tmp;
			}
			return res;
		}
	}

}
