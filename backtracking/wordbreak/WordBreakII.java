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
    	int n = s.length(), maxLen = getMaxLen(wordDict);
    	boolean[] possible = new boolean[n+1];
    	boolean[][] dp = new boolean[n][n];
    	int[] lastInds = new int[n];
    	possible[0] = true;
    	for (int end = 1; end <= n; end++) {
			for (int begin = end-1; begin >= 0 && begin >= end-maxLen; begin--) {
				if (possible[begin] && wordDict.contains(s.substring(begin, end))){
					possible[end] = true;
					dp[begin][end-1] = true;
					lastInds[begin] = end-1;
				}
			}
		}
    	
    	if (possible[n]) dfs(s, 0, dp, lastInds, res, "");
    	return res;
	}
	
    private int getMaxLen(Set<String> wordDict) {
		int max = 0;
		for (String string : wordDict) {
			max = Math.max(max, string.length());
		}
		return max;
	}
    
    
	private void dfs(String s, int begin, boolean[][] dp, int[] lastInds, List<String> res, String cur) {
		if (begin >= s.length()) {
			res.add(cur.substring(0, cur.length()-1));
		} else {
			for (int end = begin; end <= lastInds[begin]; end++) {
				if (dp[begin][end]) dfs(s, end+1, dp, lastInds, res, cur + s.substring(begin, end+1) + " ");
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
		System.out.println(res.size());
		System.out.println("----------");
	}
    
    public static void main(String[] args) {
    	WordBreakII word = new HashMapApproach();
    	test(word, "catsanddog", new String[] {"cat", "cats", "and", "sand", "dog"});
    	test(word, "bbbbbbbb", new String[] {"cat", "cats", "and", "sand", "dog"});
    	test(word, "aaaaaaaaaaaaaaaaaa", new String[] {"aa", "aaa"});
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
