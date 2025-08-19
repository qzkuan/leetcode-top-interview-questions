package topinterviewquestions;

/**
 * [172. 阶乘后的零](https://leetcode.cn/problems/factorial-trailing-zeroes/)

给定一个整数 `n` ，返回 `n!` 结果中尾随零的数量。

提示 `n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1`
输入：n = 5
输出：1
解释：5! = 120 ，有一个尾随 0
 */
public class Problem_0172_FactorialTrailingZeroes {

	/**
	 * 1-5,6-10,11-15,16-20,21-25 每五个会有一个5，n/5
	 * 每25个会多一个5，n/25
	 * 每125个多一个5，n/125
	 * ...
	 * @param n
	 * @return
	 */
	public static int trailingZeroes(int n) {
		int ans = 0;
		while (n != 0) {
			n /= 5;
			ans += n;
		}
		return ans;
	}

	/**
	 * 上一个方法的方便理解法
	 * @param n
	 * @return
	 */
	public int trailingZeroes3(int n) {
        int ans = 0;
        int tmp = 0;
        int sqrt = 5;
        // 依次算n/5   有几个
        //      n/25  有几个
        //      n/125 有几个
        while (n / sqrt > 0) {
            tmp = n / sqrt;
            ans += tmp;
            sqrt *= 5;
        }
        return ans;
    }

	/**
	 * 暴力写法（击败 5%）
	 * @param n
	 * @return
	 */
	public static int trailingZeroes2(int n) {
        if (n < 5) {
            return 0;
        }
        int count = 0;
        for (; n >= 5; n--) {
            if (n % 5 != 0) {
                continue;
            }
            int w = n;
            while (w >= 5) {
                if (w % 5 == 0) {
                    System.out.println(w);
                    count++;
                    w = w / 5;
                } else {
                    break;
                }
            }
        }
        return count;
    }

	public static void main(String[] args) {
		trailingZeroes2(10);
	}

}
