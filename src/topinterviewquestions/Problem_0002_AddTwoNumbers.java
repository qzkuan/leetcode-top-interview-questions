package topinterviewquestions;

public class Problem_0002_AddTwoNumbers {

	// 不要提交这个类描述
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int value) {
			this.val = value;
		}
	}

	/**
	 * [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)
	 * 给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。
	 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
	 * 假设除了数字 0 之外，这两个数都不会以 0 开头。
	 * 
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int carry = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		ListNode c1 = head1;
		ListNode c2 = head2;
		ListNode node = null;
		ListNode pre = null;
		while (c1 != null || c2 != null) {
			// 短的链表补0
			n1 = c1 != null ? c1.val : 0;
			n2 = c2 != null ? c2.val : 0;
			n = n1 + n2 + carry;
			pre = node;// pre指向之前的结点
			node = new ListNode(n % 10);// 生成当前结点，取个位数字
			node.next = pre; // 当前结点往前连，即 pre <- node
			carry = n / 10; // 是否产生进位信息
			// c1/c2不为空的继续往后遍历
			c1 = c1 != null ? c1.next : null;
			c2 = c2 != null ? c2.next : null;
		}
		if (carry == 1) {
			pre = node;
			node = new ListNode(1);
			node.next = pre;
		}
		return reverseList(node);
	}

	/**
	 * 翻转链表
	 * 
	 * @param head
	 * @return
	 */
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

}
