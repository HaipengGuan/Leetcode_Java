package problems.sub_window;

/**
 * 76. Minimum Window Substring
 * 
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * 
 * Note:
 * - If there is no such window in S that covers all characters in T, return the
 * empty string "".
 * - If there are multiple such windows, you are guaranteed that there will always
 * be only one unique minimum window in S.
 * 
 * 
 * @see https://leetcode.com/problems/minimum-window-substring/
 *
 */
public class MinWindowSubstring {
	
	public String minWindow(String s, String t) {
		int n = s.length(), m = t.length();
		if (n == 0 || m == 0 || n < m) return "";
		int[] map = new int[128];
		for (int i = 0; i < m; i++) {
			map[t.charAt(i)]++;
		}
		int count = m, curLen = 0, minLen = Integer.MAX_VALUE, start = 0;
		for (int begin = 0, end = 0; end < n; end++) {
			if (map[s.charAt(end)]-- > 0) count--;
			for (; count == 0; begin++) {
				curLen = end - begin + 1;
				if (curLen < minLen) {
					minLen = curLen;
					start = begin;
				}
				if (map[s.charAt(begin)]++ == 0) count++;
			}

		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
	}
    
    public static void main(String[] args) {
		MinWindowSubstring window = new MinWindowSubstring();
		System.out.println(window.minWindow("a", ""));
		System.out.println(window.minWindow("", "a"));
		System.out.println(window.minWindow("a", "a"));
		System.out.println(window.minWindow("DAOBECODEBANC", "ABC"));
		System.out.println(window.minWindow("DAOBECODEBANC", "AABC"));
	}
}
