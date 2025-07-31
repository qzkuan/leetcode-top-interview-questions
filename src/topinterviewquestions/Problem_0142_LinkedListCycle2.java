package topinterviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 *如果链表无环，则返回 `null`。*

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（**索引从 0 开始**）。如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

**不允许修改** 链表。
 */
public class Problem_0142_LinkedListCycle2 {

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
	 * 寻找链表入环的第一个节点（快慢指针解法）
	 * 时间复杂度O(N)，空间复杂度O(1)
	 * 「Floyd 判圈算法」（又称龟兔赛跑算法）
	 * 
	 * @param head
	 * @return
	 */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 这里一定要保证快慢指针初始位置相同，或者同时先走一次
        // ListNode slow = head.next;
		// ListNode fast = head.next.next;
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

	/**
	 * 寻找链表入环的第一个节点（哈希表解法）
	 * 时间和空间复杂度都是O(n)
	 * 
	 * @param head
	 * @return
	 */
    public ListNode detectCycleHashSet(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

}
