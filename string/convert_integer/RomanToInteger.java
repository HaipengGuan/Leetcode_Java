package string.convert_integer;

/**
 * 13. Roman to Integer
 * 
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * 'I': 1
 * 'V': 5
 * 'X': 10
 * 'L': 50
 * 'C': 100
 * 'D': 500
 * 'M': 1000
 * 
 * @see https://leetcode.com/problems/roman-to-integer/
 *
 */
public class RomanToInteger {

	public int romanToInt(String s) {
		if (s == null || s.length() == 0) return 0;
		int n = s.length();
		final int[] map = new int[90];
		map['I'] = 1;
		map['V'] = 5;
		map['X'] = 10;
		map['L'] = 50;
		map['C'] = 100;
		map['D'] = 500;
		map['M'] = 1000;
		int base = map[s.charAt(n - 1)], prev = base, cur = 0;
		for (int i = n - 2; i >= 0; i--) {
			cur = map[s.charAt(i)];
			base += (cur >= prev) ? cur : -cur;
			prev = cur;
		}
		return base;
	}
}
