package backtracking.wordbreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 140. Word Break II
 * 
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * @see https://leetcode.com/problems/word-break-ii/
 *
 */
public class WordBreakII {
	
    public List<String> wordBreak(String s, Set<String> wordDict) {
    	List<String> res = new ArrayList<>();
    	if (s.length() == 0 || wordDict.size() == 0) return res;
    	int n = s.length();
    	boolean[] dp = new boolean[n+1];
    	boolean[][] dp2 = new boolean[n][n];
    	dp[0] = true;
    	for (int i = 1; i <= n; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					dp2[j][i-1] = true;
				}
			}
		}
    	if (dp[n] != false) dfs(s, dp2, 0, res, new StringBuffer(2*n));
    	return res;
    }
    
    private void dfs(String s, boolean[][] dp2, int begin, List<String> res, StringBuffer buffer) {
		if (begin >= s.length()) {
			res.add(buffer.toString().substring(0, buffer.length()-1));
		} else {
			for (int end = begin; end < s.length(); end++) {
				if (!dp2[begin][end]) continue;
				String word = s.substring(begin, end+1);
				buffer.append(word + " ");
				dfs(s, dp2, end+1, res, buffer);
				buffer.delete(buffer.length()-word.length()-1, buffer.length());
			}
		}
	}
    
    // ----------------
	private static HashSet<String> set = new HashSet<>();
    
    private static void test(WordBreakII word, String s, String[] strings) {
    	set.clear();
		set.addAll(Arrays.asList(strings));
		List<String> res = word.wordBreak(s, set);
		for (String string : res) {
			System.out.println(string);
		}
		System.out.println("----------");
	}
    
    public static void main(String[] args) {
    	WordBreakII word = new HashMapApproach();
    	test(word, "catsanddog", new String[] {"cat", "cats", "and", "sand", "dog"});
    	test(word, "bbbbbbbb", new String[] {"cat", "cats", "and", "sand", "dog"});
    	test(word, "aaaaaaaaaaaa", new String[] {"aa", "aaa"});
	}
}

class HashMapApproach extends WordBreakII {
	
	private HashMap<String, List<String>> map = new HashMap<>();
	
	public List<String> wordBreak(String s, Set<String> wordDict) {
		if (map.containsKey(s)) return map.get(s);
		List<String> res = new ArrayList<>();
		if (wordDict.contains(s)) res.add(s);
		for (int begin = 1; begin < s.length(); begin++) {
			String suffix = s.substring(begin);
			if (wordDict.contains(suffix)) {
				List<String> prev = wordBreak(s.substring(0, begin), wordDict);
				for (String string : prev) {
					res.add(string + " " + suffix);
				}
			}
		}
		map.put(s, res);
		return res;
	}
	
	
}
