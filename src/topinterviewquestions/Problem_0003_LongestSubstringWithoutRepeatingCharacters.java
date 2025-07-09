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
		int pre = 1;
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

}
