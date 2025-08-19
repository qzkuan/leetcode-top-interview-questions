package topinterviewquestions;

import java.util.Arrays;

/**
 * [204. 计数质数](https://leetcode.cn/problems/count-primes/)

给定整数 `n` ，返回 *所有小于非负整数 `n` 的质数的数量* 。
输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class Problem_0204_CountPrimes {

	public static int countPrimes(int n) {
		if (n < 3) {
			return 0;
		}
		// f[i]=true 代表不是质数
		// f[i]=false 代表是质数
		boolean[] f = new boolean[n];
		int count = n / 2; // 所有包含2因子的数
		for (int i = 3; i * i < n; i += 2) { // 3 5 7 9 ...
			if (f[i]) { // i不是质数
				continue;
			}
			// 从i*i开始验证，是因为i*i之前的(i-1)*i数已经验证过
			for (int j = i * i; j < n; j += 2 * i) {
				if (!f[j]) { // i是质数，总质数-1，并标记
					--count;
					f[j] = true;
				}
			}
		}
		return count;
	}

	/**
	 * 作者：力扣官方题解
	 * 链接：https://leetcode.cn/problems/count-primes/solutions/507273/ji-shu-zhi-shu-by-leetcode-solution/
	 */
	class Solution {
		/**
		 * 埃氏筛（方便理解）☆
		 * 链接：https://leetcode.cn/problems/count-primes/solutions/507445/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
		 * @param n
		 * @return
		 */
		public int countPrimes(int n) {
			boolean[] isPrim = new boolean[n];
			Arrays.fill(isPrim, true);
			// 从 2 开始枚举到 sqrt(n)，将当前数字的倍数全都标记为合数。
			for (int i = 2; i * i < n; i++) {
				// 如果当前是素数
				if (isPrim[i]) {
					// 每次找当前素数x的倍数时，是从 x^2开始的。因为如果 x>2，那么 2∗x 肯定被素数 2 给过滤了，最小未被过滤的肯定是 x^2
					// 就把从 i*i 开始，i 的所有倍数都设置为 false。
					for (int j = i * i; j < n; j+=i) {
						isPrim[j] = false;
					}
				}
			}
	
			// 遍历isPrim数组计数，从2开始，0,1都不是质数
			int cnt = 0;
			for (int i = 2; i < n; i++) {
				if (isPrim[i]) {
					cnt++;
				}
			}
			return cnt;
		}
	
	
		public int countPrimes3(int n) {
			int[] isPrime = new int[n];
			Arrays.fill(isPrime, 1);
			int ans = 0;
			for (int i = 2; i < n; ++i) {
				if (isPrime[i] == 1) {
					ans += 1;
					if ((long) i * i < n) {
						for (int j = i * i; j < n; j += i) {
							isPrime[j] = 0;
						}
					}
				}
			}
			return ans;
		}
		/**
		 * 暴力枚举，会超时
		 * @param n
		 * @return
		 */
		public int countPrimes2(int n) {
			int ans = 0;
			for (int i = 2; i < n; ++i) {
				ans += isPrime(i) ? 1 : 0;
			}
			return ans;
		}
	
		public boolean isPrime(int x) {
			for (int i = 2; i * i <= x; ++i) {
				if (x % i == 0) {
					return false;
				}
			}
			return true;
		}
}

}
