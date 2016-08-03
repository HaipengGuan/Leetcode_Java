package dynamic_programming;

/**
 * 91. Decode Ways
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * Given an encoded message containing digits, determine the total number of
 * ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * 
 * @see https://leetcode.com/problems/decode-ways/
 *
 */
public class DecodeWays {

	public int numDecodings(String s) {
    	int n = s.length();
    	if (n == 0 || s.charAt(0) == '0') return 0;
    	int dp0 = 1, dp1 = 1, dp2 = 0;
    	int num = s.charAt(0) - '0', digit = 0;
    	for (int i = 2; i <= n; i++) {
    		digit = s.charAt(i-1) - '0';
    		if (digit != 0) dp2 += dp1;
			num = (num % 10) * 10 + digit;
			if (10 <= num && num <= 26) dp2 += dp0;
			dp0 = dp1; dp1 = dp2; dp2 = 0;
    	}
    	return dp1;
	}
    
    // ------------------------
    private static DecodeWays decode = new DecodeWays();
    
    public static void main(String[] args) {
		System.out.println(decode.numDecodings(""));
		System.out.println(decode.numDecodings("0"));
		System.out.println(decode.numDecodings("1"));
		System.out.println(decode.numDecodings("9"));
		System.out.println(decode.numDecodings("12"));
		System.out.println(decode.numDecodings("26"));
		System.out.println(decode.numDecodings("27"));
	}
}