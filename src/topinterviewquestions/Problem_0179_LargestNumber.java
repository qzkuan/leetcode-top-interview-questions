package topinterviewquestions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [179. 最大数](https://leetcode.cn/problems/largest-number/)

给定一组非负整数 `nums`，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

**注意：**输出结果可能非常大，所以你需要返回一个字符串而不是整数。
输入：nums = [3,30,34,5,9]
输出："9534330"
 */
public class Problem_0179_LargestNumber {

/**
 * 自定义比较器，用于按照特定规则对字符串进行排序
 * 
 * 排序规则：
 *   比较 (o2 + o1) 与 (o1 + o2) 的字典序。
 *   如果 (o2 + o1) > (o1 + o2)，则认为 o2 应该排在前面。
 * 
 * 应用场景：
 *   常用于“将一组数字拼接成最大的数”问题。
 *   例如：["9", "34", "30"] 排序后变成 ["9", "34", "30"]，
 *   拼接结果是 "93430"，是可能的最大值。
 */
public static class MyComparator implements Comparator<String> {

    /**
     * 比较两个字符串在拼接后的大小关系
     *
     * @param o1 第一个字符串
     * @param o2 第二个字符串
     * @return 比较结果：
     *         >0 表示 o1 应排在 o2 之后
     *         <0 表示 o1 应排在 o2 之前
     *         =0 表示顺序相同
     */
    @Override
    public int compare(String o1, String o2) {
        // 将两个字符串分别按不同顺序拼接
        String order1 = o1 + o2; // o1 在前
        String order2 = o2 + o1; // o2 在前

        // 按字典序比较，注意是 (o2+o1) 与 (o1+o2) 比
        // 这样可以保证最终拼接的结果是最大的
		// a.compareTo(b) a>b:1,a=b:0,a<b:-1
        return order2.compareTo(order1);
    }
}


	public String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(strs, new MyComparator());
		// 依次拼接字符串
		StringBuilder builder = new StringBuilder();
		for (String str : strs) {
			builder.append(str);
		}
		String ans = builder.toString();
		char[] str = ans.toCharArray();
		int index = -1;
		// 截掉前缀的0
		for (int i = 0; i < str.length; i++) {
			if (str[i] != '0') {
				index = i;
				break;
			}
		}
		return index == -1 ? "0" : ans.substring(index);
	}


	/**
	 * 这样是不对的，只能过一小部分案例，解答错误 93 / 235 个通过的测试用例
	 * nums = [10,2,9,39,17]
		输出  "92103917"
		预期  "93921710"
	 * @param nums
	 * @return
	 */
	public String largestNumber2(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}
		StringBuffer builder = new StringBuffer(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			if ((builder.toString() + strs[i]).compareTo(strs[i] + builder.toString()) > 0) {
				builder.append(strs[i]);
			} else {
				builder.insert(0, strs[i]);
			}
		}
		String ans = builder.toString();
		char[] str = ans.toCharArray();
		int index = -1;
		// 截掉前缀的0
		for (int i = 0; i < str.length; i++) {
			if (str[i] != '0') {
				index = i;
				break;
			}
		}
		return index == -1 ? "0" : ans.substring(index);
	}

}
