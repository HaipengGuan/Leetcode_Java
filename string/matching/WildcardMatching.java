package string.matching;

/**
 * 44. Wildcard Matching
 * 
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * 
 * @see https://leetcode.com/problems/wildcard-matching/
 *
 */
public class WildcardMatching {
	
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int j = 1; j < m + 1; j++) {
			if (p.charAt(j-1) == '*')
				dp[0][j] = dp[0][j-1];
		}
        for (int i = 1; i < n + 1; i++) {
        	for (int j = 1; j < m + 1; j++) {
				if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) { 
					dp[i][j] = dp[i-1][j-1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				} else {
					dp[i][j] = false;
				}
			}
		}
        return dp[n][m];
    }
    
    public void test(String s, String p) {
    	System.out.print(String.format("[%s - %s]  \t", s, p));
    	System.out.println(isMatch(s, p));
	}
    
    public static void main(String[] args) {
    	WildcardMatching matching = new ImprovedDP2();
    	matching.test("", "");		// true
    	matching.test("a", "");		// false
    	matching.test("", "a");		// false
    	matching.test("a", "a");	// true
    	matching.test("aa", "a");	// false
    	matching.test("a", "aa");	// false
    	matching.test("", "*");		// true
    	matching.test("", "?");		// false
    	matching.test("a", "*");	// true
    	matching.test("a", "a*");	// true
    	matching.test("a", "?");	// true
    	matching.test("aa", "*");	// true
    	matching.test("ab", "?*");	// true
    	matching.test("aab", "c*a*b");	// false
	}
}

class TwoPointer extends WildcardMatching {
	@Override
	public boolean isMatch(String str, String pattern) {
		int n = str.length(), m = pattern.length();
		int s = 0, p = 0, lastS = 0, lastP = -1;
		while (s < n) {
			if (p < m && (pattern.charAt(p) == '?' || pattern.charAt(p) == str.charAt(s))) {
				p++; s++;
			} else if (p < m && pattern.charAt(p) == '*') {
				lastP = p++;
				lastS = s;
			} else if (lastP != -1) {
				p = lastP + 1;
				s = ++ lastS;
			} else {
				return false;
			}
		}
		while (p < m && pattern.charAt(p) == '*') {
			p++;
		}
		return p == m;
	}
}


class ImprovedDP2 extends WildcardMatching {
	
	@Override
	public boolean isMatch(String s, String p) {
	    int n = s.length(), m = p.length();
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for (int j = 0; j < m; j++) {
			dp[j+1] = (p.charAt(j) == '*' && dp[j]);
		}
		boolean pre, cur;
		char sc, pc;
		for (int i = 0; i < n; i++) {
			pre = dp[0];
			dp[0] = false;
			sc = s.charAt(i);
			for (int j = 0; j < m; j++) {
				cur = dp[j+1];
				pc = p.charAt(j);
				if (pc == '*') {
					dp[j+1] = dp[j+1] || dp[j];
				} else {
					dp[j+1] = pre && (pc == '?' || pc == sc);
				}
				pre = cur;
			}
		}
		return dp[m];
	}
	
}