package string.matching;

/**
 * 10. Regular Expression Matching
 * 
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * - '.' Matches any single character. 
 * - '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: 
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples: isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * 
 * @author guanhaipeng
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
    	int n = s.length(), m = p.length();
		boolean[][] dp = new boolean[n+1][m+1];
		dp[0][0] = true;
		for (int j = 1; j < m + 1; j++) {
			if (p.charAt(j-1) == '*')
				dp[0][j] = (j - 2 >= 0) ? dp[0][j - 2] : false;
			else dp[0][j] = false;
		}
		for (int i = 1; i < n + 1; i++) {
			dp[i][0] = false;
			for (int j = 1; j < m + 1; j++) {
				if (p.charAt(j-1) == '*') {
					dp[i][j] = dp[i][j-2] || ((p.charAt(j-2) == '.' || s.charAt(i-1) == p.charAt(j-2)) && dp[i-1][j]);
				} else {
					dp[i][j] = (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) && dp[i-1][j-1];
				}
			}
		}
		return dp[n][m];
    }
    
    public void test(String s, String p) {
    	System.out.print(String.format("[%s <- %s] \t", s, p));
    	System.out.println(isMatch(s, p) == s.matches(p));
	}

	public static void main(String[] args) {
		RegularExpressionMatching matching = new ImprovedDP();	
		matching.test("aa", "aa");		// true
		matching.test("aaa", "aa");		// false	
		matching.test("aa", "aaa");		// false
		matching.test("", "");			// true
		matching.test("a", "");			// false
		matching.test("", "a*");		// true
		matching.test("", ".*");		// true
		matching.test("aa", "a*");		// true
		matching.test("a", ".*");		// true
		matching.test("a", "a*a");		// true
		matching.test("aa", "aa*");		// true
		matching.test("aa", ".*");		// true
		matching.test("ab", ".*");		// true
		matching.test("aab", "c*a*b");	// true
		matching.test("aab", ".*c");	// false
		matching.test("aab", ".*c*");	// false
		matching.test("abcd", "d*");	// false
	}
}


class TwoPointerSolution extends RegularExpressionMatching {
	@Override
	public boolean isMatch(String s, String p) {
		return isMatch(s, 0, s.length(), p, 0, p.length());
	}
	
	private boolean isMatch(String s, int i, int n, String p, int j, int m) {
		if (j == m) return i == n;
		if (p.charAt(j) == '*') return false;
		if (j == m - 1 || p.charAt(j+1) != '*') {
			return i < n && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i) ) && isMatch(s, i+1, n, p, j+1, m);
		} else {
			while (i < n && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
				if (isMatch(s, i++, n, p, j+2, m)) return true;
			}
			return isMatch(s, i, n, p, j+2, m);
		}
	}
}

class ImprovedDP extends RegularExpressionMatching {
	@Override
	public boolean isMatch(String s, String p) {
    	int n = s.length(), m = p.length();
		boolean[] dp = new boolean[m+1];
		dp[0] = true;
		for (int j = 0; j < m; j++) {
			dp[j+1] = (p.charAt(j) == '*' && dp[j-1]); 
		}
		char s1, p1, p0;
		boolean pre, cur;
		for (int i = 0; i < n; i++) {
			pre = dp[0];
			dp[0] = false;
			s1 = s.charAt(i);
			for (int j = 0; j < m; j++) {
				cur = dp[j+1];
				p1 = p.charAt(j);
				if (p1 == '*') {
					p0 = p.charAt(j-1);
					dp[j+1] = dp[j-1] || ((p0 == '.' || p0 == s1) && dp[j+1]);
				} else {
					dp[j+1] = pre && (p1 == '.' || p1 == s1);
				}
				pre = cur;
			}
		}
		return dp[m];
	}
}
