package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * [118. 杨辉三角](https://leetcode.cn/problems/pascals-triangle/)
 *    1
 *   1 1
 * 1  2  1

给定一个非负整数 *`numRows`，*生成「杨辉三角」的前 *`numRows`* 行。

在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
public class Problem_0118_PascalTriangle {

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			ans.add(new ArrayList<>());
			ans.get(i).add(1); // 添加每行第一位置的1
		}
		for (int i = 1; i < numRows; i++) {
			for (int j = 1; j < i; j++) { // 添加每行从第2列开始 =[上一行j-1]+[上一行j]
				ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
			}
			ans.get(i).add(1); // 添加每行最后位置的1
		}
		return ans;
	}

	class Solution {
		/**
		 * 杨辉三角
		 * @param numRows
		 * @return
		 */
		public List<List<Integer>> generate(int numRows) {
			List<List<Integer>> ret = new ArrayList<List<Integer>>();
			for (int i = 0; i < numRows; ++i) {
				List<Integer> row = new ArrayList<Integer>();
				for (int j = 0; j <= i; ++j) {
					if (j == 0 || j == i) { // 每一行的第一个和最后一个位置为1
						row.add(1);
					} else { // 每行第2列开始=上一行的对应位置数＋上一行对应位置前一个数
						row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
					}
				}
				ret.add(row);
			}
			return ret;
		}
	}

}
