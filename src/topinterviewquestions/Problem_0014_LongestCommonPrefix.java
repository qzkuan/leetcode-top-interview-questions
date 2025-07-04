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
