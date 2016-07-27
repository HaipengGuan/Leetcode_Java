package backtracking.wordbreak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 139. Word Break
 * 
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given
 * 
 * s = "leetcode",
 * 
 * dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @see https://leetcode.com/problems/word-break/
 *
 */
public class WordBreak {

    public boolean wordBreak(String s, Set<String> wordDict) {
    	if (s.length() == 0 || wordDict.size() == 0) return false;
    	int n = s.length();
    	boolean[] dp = new boolean[n+1];
    	dp[0] = true;
    	for (int i = 1; i <= n; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
    	return dp[n];
    }
    
    
    // ------------------------
	private static HashSet<String> set = new HashSet<>();
    
    private static void test(WordBreak word, String s, String[] strings) {
    	set.clear();
		set.addAll(Arrays.asList(strings));
    	System.out.println(word.wordBreak(s, set));
	}
    
    public static void main(String[] args) {
		WordBreak word = new WordBreak();
		test(word, "leetcode", new String[]{"leet", "code"});
		test(word, "leetcodeleet", new String[]{"leet", "code"});
		test(word, "aaaaaaaaaaa", new String[]{"a", "aa"});
    	
	}
}
