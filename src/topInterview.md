### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。

 

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

**示例 2：**

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

**示例 3：**

```
输入：nums = [3,3], target = 6
输出：[0,1]
```

 [源代码](./topinterviewquestions/Problem_0001_TwoSum.java)

```java
package topinterviewquestions;

import java.util.HashMap;

public class Problem_0001_TwoSum {
	/**
	 * [1. 两数之和](https://leetcode.cn/problems/two-sum/)
	 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
	 * 假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		// key 某个之前的数   value 这个数出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}
		return new int[] { -1, -1 };
	}

}
```



### [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

**示例 2：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

**示例 3：**

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

 

**提示：**

- 每个链表中的节点数在范围 `[1, 100]` 内
- `0 <= Node.val <= 9`
- 题目数据保证列表表示的数字不含前导零

```java
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
	 * @param head1 eg[2,4,3]	[6,7,4,8]
	 * @param head2   [5,6,3]	[4,6,8,9]
	 * @return		  [7,0,7]	[0,4,3,8,1]
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

```



### [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长 子串** 的长度。

 

**示例 1:**

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

 

**提示：**

- `0 <= s.length <= 5 * 104`
- `s` 由英文字母、数字、符号和空格组成

```java
package topinterviewquestions;

public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
	/**
	 * [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)
	 * 给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。
	 * @param s eg abcabcbb
	 * @return	   3
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		// map (a, ?) (b, ?)
		// a, 17
		// map[97] = 17
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		// 收集答案
		int len = 0;
		int pre = -1; // i-1位置结尾的情况下，往左推，推不动的位置是谁
		int cur = 0;
		for (int i = 0; i != str.length; i++) {
			// i位置结尾的情况下，往左推，推不动的位置是谁
			// pre (i-1信息) -> pre(i 结尾信息)
			pre = Math.max(pre, map[str[i]]);
			cur = i - pre;
			len = Math.max(len, cur);
			map[str[i]] = i;
		}
		return len;
	}

	/**
	 * 新版刷题班解法
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring2(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] map = new int[256]; // ASCII最长256
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		map[str[0]] = 0; // 初始化0位置元素出现的位置
		int N = str.length;
		int ans = 1;
		int pre = 1; // 上一个元素及之前元素[0,i-1]的最长无重复子串
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

}

```

### [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)TODO

给你一个字符串 `s`，找到 `s` 中最长的  

**示例 1：**

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```

**示例 2：**

```
输入：s = "cbbd"
输出："bb"
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 仅由数字和英文字母组成

```java
package topinterviewquestions;

public class Problem_0005_LongestPalindromicSubstring {

	/**
	 * [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/description/)
	 * @param str
	 * @return
	 */
	public static String longestPalindrome(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int max = Integer.MIN_VALUE;
		int mid = 0;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			if (max < pArr[i]) {
				max = pArr[i];
				mid = i;
			}
		}
		mid = (mid - 1) / 2;
		max = max - 1;
		return str.substring((max & 1) == 0 ? mid - (max / 2) + 1 : mid - (max / 2), mid + (max / 2) + 1);
	}

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

}

```

### [7. 整数反转](https://leetcode.cn/problems/reverse-integer/)

给你一个 32 位的有符号整数 `x` ，返回将 `x` 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−2^31^, 2^31^ − 1] ，就返回 0。

**假设环境不允许存储 64 位整数（有符号或无符号）。**

 

**示例 1：**

```
输入：x = 123
输出：321
```

**示例 2：**

```
输入：x = -123
输出：-321
```

**示例 3：**

```
输入：x = 120
输出：21
```

**示例 4：**

```
输入：x = 0
输出：0
```

 

**提示：**

- `-231 <= x <= 231 - 1`

```java
package topinterviewquestions;

public class Problem_0007_ReverseInteger {

	/**
	 * [7. 整数反转](https://leetcode.cn/problems/reverse-integer/)
	 * 给你一个 32 位的有符号整数 `x` ，返回将 `x` 中的数字部分反转后的结果。
	 * 如果反转后整数超过 32 位的有符号整数的范围 `[−231, 231 − 1]` ，就返回 0。
	 * 假设环境不允许存储 64 位整数（有符号或无符号）。
	 * 
	 * @param x eg -321 120
	 * @return     -123 21
	 */
	public static int reverse(int x) {
		// 判断x是否是负数，x >>> 31得到符号位
		boolean negative = ((x >>> 31) & 1) == 1;
		// 将x统一转换为负数处理
		x = negative ? x : -x;
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
			if (res < m || (res == m && x % 10 < o)) {
				return 0; // 溢出
			}
			// 弹出 x 的末尾数字 x%10 ，并放入res末尾位置
			res = res * 10 + x % 10;
			x /= 10;
		}
		return negative ? res : Math.abs(res);
	}

	/**
	 * 力扣官方解法
	 * https://leetcode.cn/problems/reverse-integer/solutions/755611/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
	 * 
	 * @param x
	 * @return
	 */
	public int reverseWithLeetcode(int x) {
		int rev = 0;
		while (x != 0) {
			if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			x /= 10;
			rev = rev * 10 + digit;
		}
		return rev;
	}

	public int reverse2(int x) {
		int result = 0;
		while (x != 0) {
			if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
				return 0;
			}
			int digit = x % 10;
			result = result * 10 + digit;
			x = x / 10;
		}
		return result;
	}
}

```

### [8. 字符串转换整数 (atoi)](https://leetcode.cn/problems/string-to-integer-atoi/)

请你来实现一个 `myAtoi(string s)` 函数，使其能将字符串转换成一个 32 位有符号整数。

函数 `myAtoi(string s)` 的算法如下：

1. **空格：**读入字符串并丢弃无用的前导空格（`" "`）
2. **符号：**检查下一个字符（假设还未到字符末尾）为 `'-'` 还是 `'+'`。如果两者都不存在，则假定结果为正。
3. **转换：**通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
4. **舍入：**如果整数数超过 32 位有符号整数范围 `[−231, 231 − 1]` ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 `−231` 的整数应该被舍入为 `−231` ，大于 `231 − 1` 的整数应该被舍入为 `231 − 1` 。

返回整数作为最终结果。

 

**示例 1：**

**输入：**s = "42"

**输出：**42

**解释：**加粗的字符串为已经读入的字符，插入符号是当前读取的字符。

```
带下划线线的字符是所读的内容，插入符号是当前读入位置。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："42"（读入 "42"）
           ^
```

**示例 2：**

**输入：**s = " -042"

**输出：**-42

**解释：**

```
第 1 步："   -042"（读入前导空格，但忽视掉）
            ^
第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
             ^
第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
               ^
```

**示例 3：**

**输入：**s = "1337c0d3"

**输出：**1337

**解释：**

```
第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
             ^
```

**示例 4：**

**输入：**s = "0-1"

**输出：**0

**解释：**

```
第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
         ^
第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
         ^
第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
          ^
```

**示例 5：**

**输入：**s = "words and 987"

**输出：**0

**解释：**

读取在第一个非数字字符“w”处停止。

 

**提示：**

- `0 <= s.length <= 200`
- `s` 由英文字母（大写和小写）、数字（`0-9`）、`' '`、`'+'`、`'-'` 和 `'.'` 组成

```java
package topinterviewquestions;

public class Problem_0008_StringToInteger {
	/**
	 * 8. 字符串转换整数 (atoi)https://leetcode.cn/problems/string-to-integer-atoi/
	 * 实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
	 * 
	 * @param s
	 * @return
	 */
	public static int myAtoi(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		s = removeHeadZero(s.trim());
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		// str 是符合日常书写的，正经整数形式
		boolean posi = str[0] == '-' ? false : true;
		int minq = Integer.MIN_VALUE / 10;
		int minr = Integer.MIN_VALUE % 10;
		int res = 0;
		int cur = 0;
		for (int i = (str[0] == '-' || str[0] == '+') ? 1 : 0; i < str.length; i++) {
			cur = '0' - str[i];
			if ((res < minq) || (res == minq && cur < minr)) {
				return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = res * 10 + cur;
		}
		// Integer取值范围：-2147483648 ~ 2147483647
		// 原数非负&&res负特殊处理： 2147483648 -> 2147483647
		if (posi && res == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		}
		return posi ? -res : res;
	}

	/**
	 * 去除开头的除符号外的字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeHeadZero(String str) {
		boolean r = (str.startsWith("+") || str.startsWith("-"));
		int s = r ? 1 : 0; // 开头有+/-符号则从1位置处理，开头没有符号则从0位置处理
		for (; s < str.length(); s++) {
			if (str.charAt(s) != '0') {
				break;
			}
		}
		// s 到了第一个不是'0'字符的位置
		int e = -1;
		// 左 <- 右，最左的不为数字字符的位置
		for (int i = str.length() - 1; i >= (r ? 1 : 0); i--) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				e = i;
			}
		}
		// e 到了最左的 不是数字字符的位置
		// return [+/-/''] + subStr[s,e)
		return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
	}

	/**
	 * 检验字符串形式整数的合法性
	 * @param chas
	 * @return
	 */
	public static boolean isValid(char[] chas) {
		if (chas[0] != '-' && chas[0] != '+' && (chas[0] < '0' || chas[0] > '9')) {
			return false;
		}
		if ((chas[0] == '-' || chas[0] == '+') && chas.length == 1) {
			return false;
		}
		// 0 +... -... num
		for (int i = 1; i < chas.length; i++) {
			if (chas[i] < '0' || chas[i] > '9') {
				return false;
			}
		}
		return true;
	}

}

```

### [10. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching/)TODO

给你一个字符串 `s` 和一个字符规律 `p`，请你来实现一个支持 `'.'` 和 `'*'` 的正则表达式匹配。

- `'.'` 匹配任意单个字符
- `'*'` 匹配零个或多个前面的那一个元素

所谓匹配，是要涵盖 **整个** 字符串 `s` 的，而不是部分字符串。

**示例 1：**

```
输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3：**

```
输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

 

**提示：**

- `1 <= s.length <= 20`
- `1 <= p.length <= 20`
- `s` 只包含从 `a-z` 的小写字母。
- `p` 只包含从 `a-z` 的小写字母，以及字符 `.` 和 `*`。
- 保证每次出现字符 `*` 时，前面都匹配到有效的字符

```java
package topinterviewquestions;

/**
 * [10. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching/)
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
 */
public class Problem_0010_RegularExpressionMatching {

	public static boolean isValid(char[] str, char[] pattern) {
		for (char cha : str) {
			if (cha == '.' || cha == '*') {
				return false;
			}
		}
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	// TODO课堂现场写
	public static boolean isMatch1(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		return isValid(str, pattern) && process1(str, pattern, 0, 0);
	}

	// 课堂现场写
	// str[si.....] 能否被 pattern[pi...] 变出来
	// 潜台词：pi位置，pattern[pi] != '*'
	public static boolean process1(char[] str, char[] pattern, int si, int pi) {
		if (si == str.length) { // si越界了
			if (pi == pattern.length) {
				return true;
			}
			if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
				return process1(str, pattern, si, pi + 2);
			}
			return false;
		}
		// si 没越界
		if (pi == pattern.length) {
			return si == str.length;
		}
		// si 没越界 pi 没越界
		if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
			return ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && process1(str, pattern, si + 1, pi + 1);
		}
		// si 没越界 pi 没越界 pi+1 *
		if (pattern[pi] != '.' && str[si] != pattern[pi]) {
			return process1(str, pattern, si, pi + 2);
		}
		// si 没越界 pi 没越界 pi+1 * [pi]可配[si]
		if (process1(str, pattern, si, pi + 2)) {
			return true;
		}
		while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
			if (process1(str, pattern, si + 1, pi + 2)) {
				return true;
			}
			si++;
		}
		return false;
	}

	// 课堂现场写
	public static boolean isMatch2(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		int[][] dp = new int[str.length + 1][pattern.length + 1];
		for (int si = 0; si <= str.length; si++) {
			for (int pi = 0; pi <= pattern.length; pi++) {
				dp[si][pi] = -1;
			}
		}
		// dp[si][pi] == -1
		// dp[si][pi] == 0 si pi false
		// dp[si][pi] == 1 si pi true
		return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
	}

	// 课堂现场写
	// str[si.....] 能否被 pattern[pi...] 变出来
	// 潜台词：pi位置，pattern[pi] != '*'
	public static boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {
		if (dp[si][pi] != -1) {
			return dp[si][pi] == 1;
		}
		// si pi 这个参数组合第一次算

		if (si == str.length) { // si越界了
			if (pi == pattern.length) {
				dp[si][pi] = 1;
				return true;
			}
			// (pi pi+1) pi+2 ....
			if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
				boolean ans = process2(str, pattern, si, pi + 2, dp);
				dp[si][pi] = ans ? 1 : 0;
				return ans;
			}
			dp[si][pi] = 0;
			return false;
		}
		// si 没越界
		if (pi == pattern.length) {
			boolean ans = si == str.length;
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		// si 没越界 pi 没越界
		if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
			boolean ans = ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
					&& process2(str, pattern, si + 1, pi + 1, dp);
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		// si 没越界 pi 没越界 pi+1 *
		if (pattern[pi] != '.' && str[si] != pattern[pi]) {
			boolean ans = process2(str, pattern, si, pi + 2, dp);
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		if (process2(str, pattern, si, pi + 2, dp)) {
			dp[si][pi] = 1;
			return true;
		}
		while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
			if (process2(str, pattern, si + 1, pi + 2, dp)) {
				dp[si][pi] = 1;
				return true;
			}
			si++;
		}
		dp[si][pi] = 0;
		return false;
	}

	// 以下的代码是课后的优化
	// 有代码逻辑精简的优化，还包含一个重要的枚举行为优化
	// 请先理解课上的内容
	public static boolean isMatch3(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		return isValid(str, pattern) && process3(str, pattern, 0, 0);
	}

	// 举例说明枚举行为优化
	// 求状态(si = 3, pi = 7)时，假设状况如下
	// str : a a a b ...
	// si  : 3 4 5 6 ...
	// pat : a * ? ...
	// pi  : 7 8 9 ...
	// 状态(si = 3, pi = 7)会依赖：
	//   状态(si = 3, pi = 9)
	//   状态(si = 4, pi = 9)
	//   状态(si = 5, pi = 9)
	//   状态(si = 6, pi = 9)
	//
	// 求状态(si = 2, pi = 7)时，假设状况如下
	// str : a a a a b ...
	// si  : 2 3 4 5 6 ...
	// pat : a * ? ...
	// pi  : 7 8 9 ...
	// 状态(si = 2, pi = 7)会依赖：
	//   状态(si = 2, pi = 9)
	//   状态(si = 3, pi = 9)
	//   状态(si = 4, pi = 9)
	//   状态(si = 5, pi = 9)
	//   状态(si = 6, pi = 9)
	//
	// 注意看状态(si = 2, pi = 7)依赖的后4个，其实就是状态(si = 3, pi = 7)
	// 所以状态(si = 2, pi = 7)的依赖可以化简为：
	//   状态(si = 2, pi = 9)
	//   状态(si = 3, pi = 7)
	// 这样枚举行为就被化简成了有限两个状态，详细情况看代码
	public static boolean process3(char[] str, char[] pattern, int si, int pi) {
		if (si == str.length && pi == pattern.length) {
			return true;
		}
		if (si == str.length) {
			return (pi + 1 < pattern.length && pattern[pi + 1] == '*') && process3(str, pattern, si, pi + 2);
		}
		if (pi == pattern.length) {
			return false;
		}
		if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
			return ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && process3(str, pattern, si + 1, pi + 1);
		}
		// 此处为枚举行为优化，含义看函数注释
		if ((str[si] == pattern[pi] || pattern[pi] == '.') && process3(str, pattern, si + 1, pi)) {
			return true;
		}
		return process3(str, pattern, si, pi + 2);
	}

	// 以下的代码是枚举行为优化后的尝试函数，改成动态规划的解
	// 请先理解基础班中"暴力递归改动态规划"的内容
	// 如果str长度为N，pattern长度为M，最终时间复杂度为O(N*M)
	public static boolean isMatch4(String str, String pattern) {
		if (str == null || pattern == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		if (!isValid(s, p)) {
			return false;
		}
		int N = s.length;
		int M = p.length;
		boolean[][] dp = new boolean[N + 1][M + 1];
		dp[N][M] = true;
		for (int j = M - 1; j >= 0; j--) {
			dp[N][j] = (j + 1 < M && p[j + 1] == '*') && dp[N][j + 2];
		}
		// dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
		if (N > 0 && M > 0) {
			dp[N - 1][M - 1] = (s[N - 1] == p[M - 1] || p[M - 1] == '.');
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 2; j >= 0; j--) {
				if (p[j + 1] != '*') {
					dp[i][j] = ((s[i] == p[j]) || (p[j] == '.')) && dp[i + 1][j + 1];
				} else {
					if ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

}

```

### [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])` 。

找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

**说明：**你不能倾斜容器。

 

**示例 1：**

![img](https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg)

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
```

**示例 2：**

```
输入：height = [1,1]
输出：1
```

 

**提示：**

- `n == height.length`
- `2 <= n <= 105`
- `0 <= height[i] <= 104`

```java
package topinterviewquestions;

public class Problem_0011_ContainerWithMostWater {
	/**
	 * 11. 盛最多水的容器 https://leetcode.cn/problems/container-with-most-water/
	 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
	 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 * 返回容器可以储存的最大水量。
	 * 
	 * @param h
	 * @return
	 */
	public static int maxArea(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

  // ✅
	public int maxAreaWithLeetcode(int[] height) {
		int low = 0;
		int high = height.length - 1;
		int maxArea = 0;
		while (low < high) {
			int area = Math.min(height[low], height[high]) * (high - low);
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[low] < height[high]) {
				low++;
			} else {
				high--;
			}
		}
		return maxArea;
	}

}

```

### [12. 整数转罗马数字](https://leetcode.cn/problems/integer-to-roman/)

七个不同的符号代表罗马数字，其值如下：

| 符号 | 值   |
| ---- | ---- |
| I    | 1    |
| V    | 5    |
| X    | 10   |
| L    | 50   |
| C    | 100  |
| D    | 500  |
| M    | 1000 |

罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：

- 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
- 如果该值以 4 或 9 开头，使用 **减法形式**，表示从以下符号中减去一个符号，例如 4 是 5 (`V`) 减 1 (`I`): `IV` ，9 是 10 (`X`) 减 1 (`I`)：`IX`。仅使用以下减法形式：4 (`IV`)，9 (`IX`)，40 (`XL`)，90 (`XC`)，400 (`CD`) 和 900 (`CM`)。
- 只有 10 的次方（`I`, `X`, `C`, `M`）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (`V`)，50 (`L`) 或 500 (`D`)。如果需要将符号附加4次，请使用 **减法形式**。

给定一个整数，将其转换为罗马数字。

 

**示例 1：**

**输入：**num = 3749

**输出：** "MMMDCCXLIX"

**解释：**

```
3000 = MMM 由于 1000 (M) + 1000 (M) + 1000 (M)
 700 = DCC 由于 500 (D) + 100 (C) + 100 (C)
  40 = XL 由于 50 (L) 减 10 (X)
   9 = IX 由于 10 (X) 减 1 (I)
注意：49 不是 50 (L) 减 1 (I) 因为转换是基于小数位
```

**示例 2：**

**输入：**num = 58

**输出：**"LVIII"

**解释：**

```
50 = L
 8 = VIII
```

**示例 3：**

**输入：**num = 1994

**输出：**"MCMXCIV"

**解释：**

```
1000 = M
 900 = CM
  90 = XC
   4 = IV
```

 

**提示：**

- `1 <= num <= 3999`

```java
package topinterviewquestions;

public class Problem_0012_IntegerToRoman {

	/**
	 * [12. 整数转罗马数字](https://leetcode.cn/problems/integer-to-roman/)
	 * 
	 * @param num
	 * @return
	 */
	public static String intToRoman(int num) {
		String[][] c = {
				{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
				{ "", "M", "MM", "MMM" } };
		StringBuilder roman = new StringBuilder();
		roman
				.append(c[3][num / 1000 % 10])
				.append(c[2][num / 100 % 10])
				.append(c[1][num / 10 % 10])
				.append(c[0][num % 10]);
		return roman.toString();
	}

	public String intToRoman2(int num) {
		String[] thousands = { "", "M", "MM", "MMM" };
		String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
		String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
		String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		StringBuffer roman = new StringBuffer();
		roman.append(thousands[num / 1000]);
		roman.append(hundreds[num % 1000 / 100]);
		roman.append(tens[num % 100 / 10]);
		roman.append(ones[num % 10]);
		return roman.toString();
	}

}

```

### [13. 罗马数字转整数](https://leetcode.cn/problems/roman-to-integer/)

罗马数字包含以下七种字符: `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 `2` 写做 `II` ，即为两个并列的 1 。`12` 写做 `XII` ，即为 `X` + `II` 。 `27` 写做 `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V` (5) 和 `X` (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。

 

**示例 1:**

```
输入: s = "III"
输出: 3
```

**示例 2:**

```
输入: s = "IV"
输出: 4
```

**示例 3:**

```
输入: s = "IX"
输出: 9
```

**示例 4:**

```
输入: s = "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
```

**示例 5:**

```
输入: s = "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

 

**提示：**

- `1 <= s.length <= 15`
- `s` 仅含字符 `('I', 'V', 'X', 'L', 'C', 'D', 'M')`
- 题目数据保证 `s` 是一个有效的罗马数字，且表示整数在范围 `[1, 3999]` 内
- 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
- IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
- 关于罗马数字的详尽书写规则，可以参考 [罗马数字 - 百度百科](https://baike.baidu.com/item/罗马数字/772296)。

```java
package topinterviewquestions;

public class Problem_0013_RomanToInteger {

	/**
	 * [13. 罗马数字转整数](https://leetcode.cn/problems/roman-to-integer/)
	 * 
	 * @param s
	 * @return
	 */
	public static int romanToInt(String s) {
		int nums[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case 'M':
					nums[i] = 1000;
					break;
				case 'D':
					nums[i] = 500;
					break;
				case 'C':
					nums[i] = 100;
					break;
				case 'L':
					nums[i] = 50;
					break;
				case 'X':
					nums[i] = 10;
					break;
				case 'V':
					nums[i] = 5;
					break;
				case 'I':
					nums[i] = 1;
					break;
			}
		}
		int sum = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1]) {
				sum -= nums[i];
			} else {
				sum += nums[i];
			}
		}
		return sum + nums[nums.length - 1];
	}

}

```

### [14. 最长公共前缀](https://leetcode.cn/problems/longest-common-prefix/)

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

 

**示例 1：**

```
输入：strs = ["flower","flow","flight"]
输出："fl"
```

**示例 2：**

```
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
```

 

**提示：**

- `1 <= strs.length <= 200`
- `0 <= strs[i].length <= 200`
- `strs[i]` 如果非空，则仅由小写英文字母组成

```java
package topinterviewquestions;

public class Problem_0014_LongestCommonPrefix {

	/**
	 * [14. 最长公共前缀](https://leetcode.cn/problems/longest-common-prefix/)
	 * 编写一个函数来查找字符串数组中的最长公共前缀，不存在则返回“”
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		char[] chs = strs[0].toCharArray();
		int min = Integer.MAX_VALUE;
		for (String str : strs) {
			char[] tmp = str.toCharArray();
			int index = 0;
			// 从第二个字符串开始与第一个字符串逐字符对比，查找最长的公共位置
			while (index < tmp.length && index < chs.length) {
				if (chs[index] != tmp[index]) {
					break;
				}
				index++;
			}
			min = Math.min(index, min);
			if (min == 0) {
				return "";
			}
		}
		return strs[0].substring(0, min);
	}

	class Solution {
		/**
		 * 14.最长公共前缀（分治法）
		 * 
		 * @param strs
		 * @return
		 */
		public String longestCommonPrefix(String[] strs) {
			if (strs == null || strs.length == 0) {
				return "";
			} else {
				return longestCommonPrefix(strs, 0, strs.length - 1);
			}
		}
	
		/**
		 * strs[start,end]分治查找最长公共前缀
		 * 
		 * @param strs
		 * @param start
		 * @param end
		 * @return
		 */
		public String longestCommonPrefix(String[] strs, int start, int end) {
			if (start == end) {
				return strs[start];
			} else {
				int mid = (end - start) / 2 + start;
				String lcpLeft = longestCommonPrefix(strs, start, mid);
				String lcpRight = longestCommonPrefix(strs, mid + 1, end);
				return commonPrefix(lcpLeft, lcpRight);
			}
		}
	
		/**
		 * 寻找两个字符串的公共前缀
		 * 
		 * @param lcpLeft
		 * @param lcpRight
		 * @return
		 */
		public String commonPrefix(String lcpLeft, String lcpRight) {
			int minLength = Math.min(lcpLeft.length(), lcpRight.length());
			for (int i = 0; i < minLength; i++) {
				if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
					// substring[beginIndex, endIndex - 1]
					return lcpLeft.substring(0, i);
				}
			}
			return lcpLeft.substring(0, minLength);
		}
	}

}

```

### [15. 三数之和](https://leetcode.cn/problems/3sum/)

给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请你返回所有和为 `0` 且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

 

 

**示例 1：**

```
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
```

**示例 2：**

```
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
```

**示例 3：**

```
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
```

 

**提示：**

- `3 <= nums.length <= 3000`
- `-105 <= nums[i] <= 105`

```java
package topinterviewquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0015_3Sum {

	/**
	 * [15. 三数之和](https://leetcode.cn/problems/3sum/)
	 * 给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k`
	 * 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请你返回所有和为 `0` 且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum1(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		// 第一个数选了i位置的数
		// i < nums.length - 2 因为后边是二元组，要有足够的数
		for (int i = 0; i < nums.length - 2; i++) {
			// i位置的数与左边的数不同才有必要收集
			if (i == 0 || nums[i - 1] != nums[i]) {
				List<List<Integer>> nexts = twoSum1(nums, i + 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					// ArrayList往头位置插数据会导致数据后移浪费额外时间
					// 可以采用LinkedList或者从后往前遍历的方式规避cur.add(nums[i])
					cur.add(0, nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	// nums已经有序了
	// nums[begin......]范围上，找到累加和为target的所有二元组
	public static List<List<Integer>> twoSum1(int[] nums, int begin, int target) {
		int L = begin;
		int R = nums.length - 1;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				// nums[L] + nums[R] == target
				// nums[L]与左边的数不一样才收集（防止重复）
				if (L == begin || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				L++;
			}
		}
		return ans;
	}
 
  // 从后往前找法
	public static List<List<Integer>> threeSum2(int[] nums) {
		Arrays.sort(nums);
		int N = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = N - 1; i > 1; i--) {
			if (i == N - 1 || nums[i] != nums[i + 1]) {
				List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
				for (List<Integer> cur : nexts) {
					cur.add(nums[i]);
					ans.add(cur);
				}
			}
		}
		return ans;
	}

	public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
		int L = 0;
		int R = end;
		List<List<Integer>> ans = new ArrayList<>();
		while (L < R) {
			if (nums[L] + nums[R] > target) {
				R--;
			} else if (nums[L] + nums[R] < target) {
				L++;
			} else {
				if (L == 0 || nums[L - 1] != nums[L]) {
					List<Integer> cur = new ArrayList<>();
					cur.add(nums[L]);
					cur.add(nums[R]);
					ans.add(cur);
				}
				L++;
			}
		}
		return ans;
	}

}

```

### [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)TODO

给定一个仅包含数字 `2-9` 的字符串，返回所有它能表示的字母组合。答案可以按 **任意顺序** 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

<img src="https://pic.leetcode.cn/1752723054-mfIHZs-image.png" alt="img" style="zoom: 33%;" />

 

**示例 1：**

```
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```

**示例 2：**

```
输入：digits = ""
输出：[]
```

**示例 3：**

```
输入：digits = "2"
输出：["a","b","c"]
```

 

**提示：**

- `0 <= digits.length <= 4`
- `digits[i]` 是范围 `['2', '9']` 的一个数字。

```java
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

```





### [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)

给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

 

**示例 1：**

```
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
```

**示例 2：**

```
输入：head = [1], n = 1
输出：[]
```

**示例 3：**

```
输入：head = [1,2], n = 1
输出：[1]
```

 

**提示：**

- 链表中结点的数目为 `sz`
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

```java
package topinterviewquestions;

/**
 * [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)
 * 给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。
 */
public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}
	/**
	 * 删除链表的倒数第 `n` 个结点，并且返回链表的头结点
	 * @param head [1,2,3,4,5]
	 * @param n    2
	 * @return     [1,2,3,5]
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			n--;
			if (n == -1) { // cur多走一个才设置pre，因为要知道删除节点的前一个节点
				pre = head;
			}
			if (n < -1) { // pre和cur保持n+1距离，同步往下遍历
				pre = pre.next;
			}
			cur = cur.next;
		}
		if (n > 0) { // 不够删除的，直接返回head
			return head;
		}
		if (pre == null) { // 要删除的是头结点，返回head.next
			return head.next;
		}
		pre.next = pre.next.next; // 删除倒数第n个位置元素（Java会自动回收♻️）
		return head;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

// ✅
class Solution {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode pre = new ListNode();
		pre.next = head;
		ListNode slow = pre;
		ListNode fast = pre;
		while (n > 0) {
			fast = fast.next;
			n--;
		}
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
		return pre.next;
	}
}
```











---------------

### [202. 快乐数](https://leetcode.cn/problems/happy-number/)

编写一个算法来判断一个数 `n` 是不是快乐数。

**「快乐数」** 定义为：

- 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
- 然后重复这个过程直到这个数变为 1，也可能是 **无限循环** 但始终变不到 1。
- 如果这个过程 **结果为** 1，那么这个数就是快乐数。

如果 `n` 是 *快乐数* 就返回 `true` ；不是，则返回 `false` 。

 

**示例 1：**

```
输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

**示例 2：**

```
输入：n = 2
输出：false
```

 

**提示：**

- `1 <= n <= 231 - 1`



### [204. 计数质数](https://leetcode.cn/problems/count-primes/)

给定整数 `n` ，返回 *所有小于非负整数 `n` 的质数的数量* 。

 

**示例 1：**

```
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
```

**示例 2：**

```
输入：n = 0
输出：0
```

**示例 3：**

```
输入：n = 1
输出：0
```

 

**提示：**

- `0 <= n <= 5 * 106`



### [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```



### [207. 课程表](https://leetcode.cn/problems/course-schedule/)

你这个学期必须选修 `numCourses` 门课程，记为 `0` 到 `numCourses - 1` 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 `prerequisites` 给出，其中 `prerequisites[i] = [ai, bi]` ，表示如果要学习课程 `ai` 则 **必须** 先学习课程 `bi` 。

- 例如，先修课程对 `[0, 1]` 表示：想要学习课程 `0` ，你需要先完成课程 `1` 。

请你判断是否可能完成所有课程的学习？如果可以，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
```

**示例 2：**

```
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
```

 

**提示：**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- `prerequisites[i]` 中的所有课程对 **互不相同**



### [208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/)

**[Trie](https://baike.baidu.com/item/字典树/9825209?fr=aladdin)**（发音类似 "try"）或者说 **前缀树** 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

- `Trie()` 初始化前缀树对象。
- `void insert(String word)` 向前缀树中插入字符串 `word` 。
- `boolean search(String word)` 如果字符串 `word` 在前缀树中，返回 `true`（即，在检索之前已经插入）；否则，返回 `false` 。
- `boolean startsWith(String prefix)` 如果之前已经插入的字符串 `word` 的前缀之一为 `prefix` ，返回 `true` ；否则，返回 `false` 。

 

**示例：**

```
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
```

 

**提示：**

- `1 <= word.length, prefix.length <= 2000`
- `word` 和 `prefix` 仅由小写英文字母组成
- `insert`、`search` 和 `startsWith` 调用次数 **总计** 不超过 `3 * 104` 次



### [210. 课程表 II](https://leetcode.cn/problems/course-schedule-ii/)

现在你总共有 `numCourses` 门课需要选，记为 `0` 到 `numCourses - 1`。给你一个数组 `prerequisites` ，其中 `prerequisites[i] = [ai, bi]` ，表示在选修课程 `ai` 前 **必须** 先选修 `bi` 。

- 例如，想要学习课程 `0` ，你需要先完成课程 `1` ，我们用一个匹配来表示：`[0,1]` 。

返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 **任意一种** 就可以了。如果不可能完成所有课程，返回 **一个空数组** 。

 

**示例 1：**

```
输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
```

**示例 2：**

```
输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
```

**示例 3：**

```
输入：numCourses = 1, prerequisites = []
输出：[0]
```

 

**提示：**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= numCourses * (numCourses - 1)`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- `ai != bi`
- 所有`[ai, bi]` **互不相同**

### [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

你必须设计并实现时间复杂度为 `O(n)` 的算法解决此问题。

 

**示例 1:**

```
输入: [3,2,1,5,6,4], k = 2
输出: 5
```

**示例 2:**

```
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
```

 

**提示：** 

- `1 <= k <= nums.length <= 105`
- `-104 <= nums[i] <= 104`

### [217. 存在重复元素](https://leetcode.cn/problems/contains-duplicate/)

给你一个整数数组 `nums` 。如果任一值在数组中出现 **至少两次** ，返回 `true` ；如果数组中每个元素互不相同，返回 `false` 。

 

**示例 1：**

**输入：**nums = [1,2,3,1]

**输出：**true

**解释：**

元素 1 在下标 0 和 3 出现。

**示例 2：**

**输入：**nums = [1,2,3,4]

**输出：**false

**解释：**

所有元素都不同。

**示例 3：**

**输入：**nums = [1,1,1,3,3,4,3,2,4,2]

**输出：**true

 

**提示：**

- `1 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`

























