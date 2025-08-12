package topinterviewquestions;

/**
 * [148. 排序链表](https://leetcode.cn/problems/sort-list/)
 * 
 * 给你链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。
 */
public class Problem_0148_SortList {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int v) {
			val = v;
		}
	}

	public static ListNode sortList(ListNode head) {
		int N = 0;
		ListNode cur = head;
		while (cur != null) {
			N++;
			cur = cur.next;
		}
		ListNode h = head;
		ListNode teamFirst = head;
		ListNode pre = null;
		for (int len = 1; len < N; len <<= 1) {
			while (teamFirst != null) {
				ListNode[] hthtn = hthtn(teamFirst, len);
				ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
				if (h == teamFirst) {
					h = mhmt[0];
					pre = mhmt[1];
				} else {
					pre.next = mhmt[0];
					pre = mhmt[1];
				}
				teamFirst = hthtn[4];
			}
			teamFirst = h;
			pre = null;
		}
		return h;
	}

	public static ListNode[] hthtn(ListNode teamFirst, int len) {
		ListNode ls = teamFirst;
		ListNode le = teamFirst;
		ListNode rs = null;
		ListNode re = null;
		ListNode next = null;
		int pass = 0;
		while (teamFirst != null) {
			pass++;
			if (pass <= len) {
				le = teamFirst;
			}
			if (pass == len + 1) {
				rs = teamFirst;
			}
			if (pass > len) {
				re = teamFirst;
			}
			if (pass == (len << 1)) {
				break;
			}
			teamFirst = teamFirst.next;
		}
		le.next = null;
		if (re != null) {
			next = re.next;
			re.next = null;
		}
		return new ListNode[] { ls, le, rs, re, next };
	}

	public static ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re) {
		if (rs == null) {
			return new ListNode[] { ls, le };
		}
		ListNode head = null;
		ListNode pre = null;
		ListNode cur = null;
		ListNode tail = null;
		while (ls != le.next && rs != re.next) {
			if (ls.val <= rs.val) {
				cur = ls;
				ls = ls.next;
			} else {
				cur = rs;
				rs = rs.next;
			}
			if (pre == null) {
				head = cur;
				pre = cur;
			} else {
				pre.next = cur;
				pre = cur;
			}
		}
		if (ls != le.next) {
			while (ls != le.next) {
				pre.next = ls;
				pre = ls;
				tail = ls;
				ls = ls.next;
			}
		} else {
			while (rs != re.next) {
				pre.next = rs;
				pre = rs;
				tail = rs;
				rs = rs.next;
			}
		}
		return new ListNode[] { head, tail };
	}

	/**
	 * 链接：https://leetcode.cn/problems/sort-list/solutions/13728/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
	 */
	class Solution {
		/**
		 * 分割 cut 环节： 找到当前链表 中点，并从 中点 将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
		 * 我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
		 * 找到中点 slow 后，执行 slow.next = None 将链表切断。
		 * 递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)。
		 * cut 递归终止条件： 当 head.next == None 时，说明只有一个节点了，直接返回此节点。
		 * 
		 * 合并 merge 环节： 将两个排序链表合并，转化为一个排序链表。
		 * 双指针法合并，建立辅助 ListNode h 作为头部。
		 * 设置两指针 left, right 分别指向两链表头部，比较两指针处节点值大小，由小到大加入合并链表头部，指针交替前进，直至添加完两个链表。
		 * 返回辅助ListNode h 作为头部的下个节点 h.next。
		 * 时间复杂度 O(l + r)，l, r 分别代表两个链表长度。
		 * 当题目输入的 head == None 时，直接返回 None。
		 * 
		 * @param head
		 * @return
		 */
		public ListNode sortList(ListNode head) {
			// ① 分割cut链表
			if (head == null || head.next == null){
				return head; // 分割 递归终止条件： 当 head.next == None 时，说明只有一个节点了，直接返回此节点。
			}
			ListNode fast = head.next; // fast这里可以是head.next.next，但是不能是head，会栈溢出
			ListNode slow = head;
			// fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点
			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			ListNode tmp = slow.next;
			slow.next = null; // 从中间[slow]切断链表分两半
			ListNode left = sortList(head);
			ListNode right = sortList(tmp);
			// ② 合并merge两个链表
			ListNode curNode = new ListNode(0); // 合并后链表结点
			ListNode res = curNode; // 指向头结点，方便最后返回
			while (left != null && right != null) {
				if (left.val < right.val) {
					curNode.next = left;
					left = left.next;
				} else {
					curNode.next = right;
					right = right.next;
				}
				curNode = curNode.next; // 合并两个链表较小那个结点，当前结点同步后移
			}
			// 合并剩余的没有合并完的那个链表
			curNode.next = left != null ? left : right;
			return res.next;
		}
	}

}
