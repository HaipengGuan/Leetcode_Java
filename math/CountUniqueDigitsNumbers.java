package math;

/**
 * 357. Count Numbers with Unique Digits
 * 
 * Given a non-negative integer n, count all numbers with unique digits, x,
 * where 0 ≤ x < 10^n.
 * 
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range
 * of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * 
 * @see https://leetcode.com/problems/count-numbers-with-unique-digits/
 */
public class CountUniqueDigitsNumbers {

	// f(4) = f(3) + 9*9*8*7
	public int countNumbersWithUniqueDigits(int n) {
		if (n == 0) return 1;
		int sum = 10, temp = 9;
		for (int i = 2; i <= n && i <= 10; i++) {
			temp *= (9 - i + 2);
			sum += temp;
		}
		return sum;
	}
}
