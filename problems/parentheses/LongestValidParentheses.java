package problems.parentheses;

/**
 * 32. Longest Valid Parentheses
 * 
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is
 * "()()", which has length = 4.
 * 
 * @see https://leetcode.com/problems/longest-valid-parentheses/
 *
 */
public class LongestValidParentheses {

	public int longestValidParentheses(String s) {
		int n = s.length(), top = -1, ind;
		int[] stack = new int[n], dp = new int[n];
		char[] charArray = s.toCharArray();
		int maxLen = 0;
		for (int i = 0; i < n; i++) {
			if (top > -1 && charArray[stack[top]] == '(' && charArray[i] == ')') {
				ind = stack[top--];
				if (ind == 0) { 
					dp[i] = i + 1;
				} else {
					dp[i] = i - ind + 1 + dp[ind-1];
				}
				maxLen = Math.max(maxLen, dp[i]);
			} else {
				stack[++top] = i;
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestValidParentheses valid = new LongestValidParentheses();
		System.out.println(valid.longestValidParentheses("")); 		// 0
		System.out.println(valid.longestValidParentheses("(")); 	// 0
		System.out.println(valid.longestValidParentheses(")")); 	// 0
		System.out.println(valid.longestValidParentheses("()")); 	// 2
		System.out.println(valid.longestValidParentheses("(()")); 	// 2
		System.out.println(valid.longestValidParentheses("())")); 	// 2
		System.out.println(valid.longestValidParentheses(")()())")); // 4
		System.out.println(valid.longestValidParentheses(")()())()(()(()")); // 4
		System.out.println(valid.longestValidParentheses(")()())()(()((()))")); // 8

	}

}
