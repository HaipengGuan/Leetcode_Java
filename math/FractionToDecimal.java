package math;

import java.util.HashMap;

/**
 * 166. Fraction to Recurring Decimal
 * 
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * For example,
 * 
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * 
 * @see https://leetcode.com/problems/fraction-to-recurring-decimal/
 *
 */
public class FractionToDecimal {
	
	public String fractionToDecimal(int numerator, int denominator) {
		long n = numerator, d = denominator;
		if (n == 0) return "0";
		StringBuffer res = new StringBuffer();
		if (n < 0 || d < 0) {
			if ((n ^ d) < 0) res.append('-');
			n = Math.abs(n); d = Math.abs(d);
		}
		res.append(n/d);
		if ((n %= d) == 0) return res.toString();
		res.append('.');
		HashMap<Long, Integer> map = new HashMap<>();
		while (true) {
			if (map.containsKey((n *= 10))) {
				res.insert(map.get(n), "(");
				return res.append(')').toString();
			} else {
				map.put(n, res.length());
			}
			res.append(n/d);
			if ((n %= d) == 0) return res.toString();
		}
	}
	
	private static FractionToDecimal toDecimal = new FractionToDecimal();
	
	public static void main(String[] args) {
		System.out.println(toDecimal.fractionToDecimal(1, 2));
		System.out.println(toDecimal.fractionToDecimal(2, 2));
		System.out.println(toDecimal.fractionToDecimal(2, 1));
		System.out.println(toDecimal.fractionToDecimal(-1, 8));
		System.out.println(toDecimal.fractionToDecimal(1, 70));
		System.out.println(toDecimal.fractionToDecimal(-30, -70));
		System.out.println(toDecimal.fractionToDecimal(-1, Integer.MIN_VALUE));
	}

}
