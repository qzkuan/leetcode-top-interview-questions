package topinterviewquestions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * [150. 逆波兰表达式求值](https://leetcode.cn/problems/evaluate-reverse-polish-notation/)

给你一个字符串数组 `tokens` ，表示一个根据 [逆波兰表示法](https://baike.baidu.com/item/逆波兰式/128437) 表示的算术表达式。

请你计算该表达式。返回一个表示表达式值的整数。

**注意：**

- 有效的算符为 `'+'`、`'-'`、`'*'` 和 `'/'` 。
- 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
- 两个整数之间的除法总是 **向零截断** 。
- 表达式中不含除零运算。
- 输入是一个根据逆波兰表示法表示的算术表达式。
- 答案及所有中间计算结果可以用 **32 位** 整数表示。
 */
public class Problem_0150_EvaluateReversePolishNotation {

	public static int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String str : tokens) {
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
				compute(stack, str);
			} else {
				stack.push(Integer.valueOf(str));
			}
		}
		return stack.peek();
	}

	public static void compute(Stack<Integer> stack, String op) {
		int num2 = stack.pop();
		int num1 = stack.pop();
		int ans = 0;
		switch (op) {
			case "+":
				ans = num1 + num2;
				break;
			case "-":
				ans = num1 - num2;
				break;
			case "*":
				ans = num1 * num2;
				break;
			case "/":
				ans = num1 / num2;
				break;
		}
		stack.push(ans);
	}

	class Solution {
		public int evalRPN(String[] tokens) {
			Deque<Integer> stack = new LinkedList<Integer>();
			for (int i = 0; i < tokens.length; i++) {
				String token = tokens[i];
				if (isNumber(token)) { // 是数字，直接入栈
					stack.push(Integer.valueOf(token));
				} else { // 是符号，出栈两个数并做对应运算
					int num2 = stack.pop();
					int num1 = stack.pop();
					switch (token) {
						case "+":
							stack.push(num1 + num2);
							break;
						case "-":
							stack.push(num1 - num2);
							break;
						case "*":
							stack.push(num1 * num2);
							break;
						case "/":
							stack.push(num1 / num2);
							break;
						default:
					}
				}
			}
			return stack.pop();
		}

		/**
		 * 判断token是不是数字
		 * 
		 * @param token
		 * @return
		 */
		public boolean isNumber(String token) {
			return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
		}
	}

}
