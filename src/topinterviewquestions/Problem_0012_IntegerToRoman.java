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
