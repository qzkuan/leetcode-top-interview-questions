package topinterviewquestions;

/**
 * [191. 位1的个数](https://leetcode.cn/problems/number-of-1-bits/)

给定一个正整数 `n`，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 

 的个数（也被称为[汉明重量](https://baike.baidu.com/item/汉明重量)）。
输入：n = 11
输出：3
解释：输入的二进制串 1011 中，共有 3 个设置位。
 */
public class Problem_0191_NumberOf1Bits {
    
	public static int hammingWeight1(int n) {
		int bits = 0;
		int rightOne = 0;
		while(n != 0) {
			bits++;
			rightOne = n & (-n);
			n ^= rightOne;	
		}
		return bits;
	}

	public static int hammingWeight2(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}

	class Solution {
		/**
		 * 逐位判断
		 * @param n
		 * @return
		 */
		public int hammingWeight(int n) {
			int count = 0;
			for (int i = 0; i < 32 && n != 0; i++) {
				int low = n & 1;
				if (low == 1) {
					count++;
				}
				n = n >>> 1;
			}
			return count;

		}
		
		/**
		 * 巧用 n&(n−1)
		 * (n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
		 * n&(n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
		 * n       1010 1000
		 * n-1     1010 0111
		 * n&(n-1) 1010 0000
		 * @param n
		 * @return
		 */
		public int hammingWeight2(int n) {
			int res = 0;
			while (n != 0) {
				res++;
				n = n & (n - 1);
			}
			return res;
		}
		/**
		 * 逐位1判断
		 * @param n
		 * @return
		 */
		public int hammingWeight3(int n) {
			int count = 0;
			int rightOne = 0;
			while(n!=0){
				count++;
				rightOne = n & ( (~n) + 1); // 提取最右侧的1
				n ^= rightOne; // 消去最右侧的1
			}
			return count;
		}
		/**
		 * 常用位运算☆
		 * 
		 * 消除最右侧的1 n&(n−1)
		 * (n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
		 * n&(n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
		 * n       1010 1000
		 * n-1     1010 0111
		 * n&(n-1) 1010 0000
		 * 
		 * 提取最右侧的1 n & ( (~n) + 1) 或者 n & (-n)
		 * (n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
		 * n&(n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
		 * n          1010 1000
		 * ~n+1       0101 1000
		 * n & (~n+1) 0000 1000
		 * n ^= (n & (~n+1)) 1010 0000 消去最右侧的1
		 */
	}
	
}
