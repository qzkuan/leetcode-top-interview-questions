package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)
 * 给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {

	public static char[][] phone = { 
			{ 'a', 'b', 'c' }, // 2    0
			{ 'd', 'e', 'f' }, // 3    1
			{ 'g', 'h', 'i' }, // 4    2
			{ 'j', 'k', 'l' }, // 5    3
			{ 'm', 'n', 'o' }, // 6    
			{ 'p', 'q', 'r', 's' }, // 7 
			{ 't', 'u', 'v' },   // 8
			{ 'w', 'x', 'y', 'z' }, // 9
	};

	/**
	 * 电话号码的字母组合
	 * @param digits "23"
	 * @return
	 */
	public static List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return ans;
		}
		char[] str = digits.toCharArray();
		char[] path = new char[str.length];
		process(str, 0, path, ans);
		return ans;
	}

	/**
	 * 递归收集可能得结果
	 * @param str ['2','3'] 2 2
	 * @param index str[index...]  按完之后，有哪些组合，放入到ans里
	 * @param path str[....index-1]，按出的结果是什么都在path里
	 * @param ans
	 */
	public static void process(char[] str, int index, char[] path, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(path));
		} else {
			char[] cands = phone[str[index] - '2'];
			for (char cur : cands) {
				path[index] = cur;
				process(str, index + 1, path, ans);
			}
		}
	}

}
