package topinterviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

给你一个链表的头节点 `head` ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。**注意：`pos` 不作为参数进行传递** 。仅仅是为了标识链表的实际情况。

*如果链表中存在环* ，则返回 `true` 。 否则，返回 `false` 。
 */
public class Problem_0141_LinkedListCycle {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public static boolean hasCycle(ListNode head) {
		return getFirstLoopNode(head) != null;
	}

	public static ListNode getFirstLoopNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	/**
	 * 判断链表是否有环（快慢指针解法）
	 * 时间复杂度O(N)，空间复杂度O(1)
	 * 「Floyd 判圈算法」（又称龟兔赛跑算法）
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 这里一定要保证快慢指针初始位置相同，或者同时先走一次(判环可以不用)
        // ListNode slow = head.next;
		// ListNode fast = head.next.next;
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        return true;
    }

	/**
	 * 判断链表是否有环（哈希表解法）
	 * 时间和空间复杂度都是O(n)
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycleHashSet(ListNode head) {
		Set<ListNode> seen = new HashSet<ListNode>();
		while (head != null) {
			if (!seen.add(head)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}

}
