package string.convert_integer;

/**
 * 273. Integer to English Words
 * 
 * Convert a non-negative integer to its english words representation. Given
 * input is guaranteed to be less than 2^31 - 1.
 * 
 * For example,
 * 
 * 123 -> "One Hundred Twenty Three" 
 * 12345 -> "Twelve Thousand Three Hundred Forty Five" 
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 
 * @see https://leetcode.com/problems/integer-to-english-words/
 *
 */
public class IntegerToWord {

	private final static String[] LESS_THAN_20 = { 
				"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
				"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" 
			};
	private final static String[] TENS = { 
				"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" 
			};
	private final static String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

	public String numberToWords(int num) {
		if (num == 0) return "Zero";
		int thouInd = 0;
		String words = "";
		while (num > 0) {
			if (num % 1000 != 0) {
				words = threeDigits(num%1000) + THOUSANDS[thouInd] + ' ' + words;
			}
			thouInd++;
			num /= 1000;
		}
		return words.trim();
	}
	
	private String threeDigits(int num) {
		if (num == 0) return "";
		if (num < 20) return LESS_THAN_20[num] + ' ';
		if (num < 100) return TENS[num/10]  + ' ' + LESS_THAN_20[num%10] + ' ';
		return LESS_THAN_20[num/100] + " Hundred " + threeDigits(num%100);
	}
	
	
	public static void main(String[] args) {
		IntegerToWord word = new IntegerToWord();
		System.out.println(word.numberToWords(123));
		System.out.println(word.numberToWords(12345));
		System.out.println(word.numberToWords(1234567));
	}
}
