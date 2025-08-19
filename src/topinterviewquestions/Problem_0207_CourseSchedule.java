package topinterviewquestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [207. 课程表](https://leetcode.cn/problems/course-schedule/)

你这个学期必须选修 `numCourses` 门课程，记为 `0` 到 `numCourses - 1` 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 `prerequisites` 给出，其中 `prerequisites[i] = [ai, bi]` ，表示如果要学习课程 `ai` 则 **必须** 先学习课程 `bi` 。

- 例如，先修课程对 `[0, 1]` 表示：想要学习课程 `0` ，你需要先完成课程 `1` 。

请你判断是否可能完成所有课程的学习？如果可以，返回 `true` ；否则，返回 `false` 。
 */
public class Problem_0207_CourseSchedule {

	// 一个node，就是一个课程
	// name是课程的编号
	// in是课程的入度
	public static class Node {
		public int name;
		public int in;
		public ArrayList<Node> nexts;

		public Node(int n) {
			name = n;
			in = 0;
			nexts = new ArrayList<>();
		}
	}

	/**
	 * 拓扑排序 判断此课程安排图是否是 有向无环图(DAG)
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0) {
			return true;
		}
		HashMap<Integer, Node> nodes = new HashMap<>();
		// 根据课程先后关系建立拓扑排序图
		for (int[] arr : prerequisites) {
			int to = arr[0];
			int from = arr[1];
			// 没有当前结点则建立放入map
			if (!nodes.containsKey(to)) {
				nodes.put(to, new Node(to));
			}
			if (!nodes.containsKey(from)) {
				nodes.put(from, new Node(from));
			}
			// 构建指向关系及入度
			Node t = nodes.get(to);
			Node f = nodes.get(from);
			f.nexts.add(t);
			t.in++;
		}
		// 开始拓扑排序检测
		int needPrerequisiteNums = nodes.size(); // 有多少个结点
		Queue<Node> zeroInQueue = new LinkedList<>();
		// 先把入度为0的节点入队
		for (Node node : nodes.values()) {
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		int count = 0; // 实际有多少个结点入队
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			count++;
			// 出队一个结点，并消去该结点指向的相邻节点入度
			for (Node next : cur.nexts) {
				if (--next.in == 0) { // 相邻结点消去入度后入度为0，加入队列
					zeroInQueue.add(next);
				}
			}
		}
		return count == needPrerequisiteNums;
	}

}
