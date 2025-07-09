package topinterviewquestions;

import java.util.Stack;

/**
 * [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)
 * 给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串 `s` ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 3. 每个右括号都有一个对应的相同类型的左括号。
 */
public class Problem_0020_ValidParentheses {
	/**
	 * 判断括号是否有效
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length; i++) {
			char cha = str[i];
			// 遇到左括号，则入栈另一半括号
			if (cha == '(' || cha == '[' || cha == '{') {
				stack.add(cha == '(' ? ')' : (cha == '[' ? ']' : '}'));
			} else { // 遇到右括号，则出栈比对
				if (stack.isEmpty()) {
					return false;
				}
				char last = stack.pop();
				if (cha != last) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	/**
	 * 判断括号是否有效(一开始的写法)
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValid2(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length; i++) {
			char cha = str[i];
			// 遇到左括号，则入栈
			if (cha == '(' || cha == '[' || cha == '{') {
				stack.add(cha);
				stack.add(cha == '(' ? ')' : (cha == '[' ? ']' : '}'));
			} else { // 遇到右括号，则出栈比对
				if (stack.isEmpty()) {
					return false;
				}
				char last = stack.pop();
				if ((cha == ')' && last != '(') || (cha == ']' && last != '[') || (cha == '}' && last != '{')) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

}
