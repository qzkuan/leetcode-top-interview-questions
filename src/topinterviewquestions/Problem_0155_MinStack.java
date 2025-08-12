package topinterviewquestions;

import java.util.Stack;

/*
 * 在leetcode上提交时，把文字替换成下面的代码
 * 然后把类名、构造方法名从Problem_0155_MinStack改为MinStack即可
 */
public class Problem_0155_MinStack {

	private Stack<Integer> data;
	private Stack<Integer> min;

	public Problem_0155_MinStack() {
		data = new Stack<>();
		min = new Stack<>();
	}

	/**
	 * 当一个元素要入栈时，我们取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，
	 * 将这个最小值插入辅助栈中；
	 * @param x
	 */
	public void push(int x) {
		data.push(x);
		if (min.isEmpty()) {
			min.push(x);
		} else {
			min.push(Math.min(min.peek(), x));
		}
	}

	/**
	 * 当一个元素要出栈时，我们把辅助栈的栈顶元素也一并弹出；
	 */
	public void pop() {
		data.pop();
		min.pop();
	}

	public int top() {
		return data.peek();
	}

	public int getMin() {
		return min.peek();
	}
}
