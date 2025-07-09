package topinterviewquestions;

/**
 * [38. 外观数列](https://leetcode.cn/problems/count-and-say/)
 * 
 * 「外观数列」是一个数位字符串序列，由递归公式定义：
 * 
 * - `countAndSay(1) = "1"`
 * - `countAndSay(n)` 是 `countAndSay(n-1)` 的行程长度编码。
 * 
 * [行程长度编码](https://baike.baidu.com/item/行程长度编码/2931940)（RLE）是一种字符串压缩方法，其工作原理是通过将连续相同字符（重复两次或更多次）替换为字符重复次数（运行长度）和字符的串联。例如，要压缩字符串
 * `"3322251"` ，我们将 `"33"` 用 `"23"` 替换，将 `"222"` 用 `"32"` 替换，将 `"5"` 用 `"15"`
 * 替换并将 `"1"` 用 `"11"` 替换。因此压缩后字符串变为 `"23321511"`。
 * 
 * 给定一个整数 `n` ，返回 **外观数列** 的第 `n` 个元素。
 */
public class Problem_0038_CountAndSay {

	public static String countAndSay(int n) {
		if (n < 1) {
			return "";
		}
		if (n == 1) {
			return "1";
		}
		char[] last = countAndSay(n - 1).toCharArray();
		StringBuilder ans = new StringBuilder();
		int times = 1;
		for (int i = 1; i < last.length; i++) {
			if (last[i - 1] == last[i]) {
				times++;
			} else {
				ans.append(String.valueOf(times));
				ans.append(String.valueOf(last[i - 1]));
				times = 1;
			}
		}
		ans.append(String.valueOf(times));
		ans.append(String.valueOf(last[last.length - 1]));
		System.out.printf("%2d次：%s\n", n, ans);
		return ans.toString();
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(13));
	}

}
