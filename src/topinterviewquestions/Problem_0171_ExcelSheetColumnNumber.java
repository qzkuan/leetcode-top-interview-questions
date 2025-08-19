package topinterviewquestions;

/**
 * [171. Excel 表列序号](https://leetcode.cn/problems/excel-sheet-column-number/)
给你一个字符串 `columnTitle` ，表示 Excel 表格中的列名称。返回 *该列名称对应的列序号* 。
例如：
```
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
```
**示例 1:**
```
输入: columnTitle = "A"
输出: 1
```
**示例 2:**
```
输入: columnTitle = "AB"
输出: 28
```
**示例 3:**
输入: columnTitle = "ZY"
输出: 701
 */
public class Problem_0171_ExcelSheetColumnNumber {

	// 这道题反过来也要会写
	public static int titleToNumber(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			ans = ans * 26 + (str[i] - 'A') + 1;
		}
		return ans;
	}

	/**
	 * 自己按照自然规则写的
	 * @param s
	 * @return
	 */
	public static int titleToNumber2(String s) {
		char[] str = s.toCharArray();
		int len = str.length;
		int ans = 0;
		for (int i = 0; i < str.length - 1; i++) {
			ans += Math.pow(26, len - i - 1) * ((str[i] - 'A') + 1);
		}
		ans += (str[len - 1] - 'A') + 1;
		return ans;
	}

}
