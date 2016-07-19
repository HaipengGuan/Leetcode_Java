package problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 3. Longest Substring Without Repeating Characters
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * @see https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class NoRepeatingSubstring {
	
    public int lengthOfLongestSubstring(String s) {
    	int n = s.length();
    	if (n == 0) return 0;
    	HashMap<Character, Integer> map = new HashMap<>();
    	int maxLen = 1;
    	for (int i = 0, start = 0; i < n; i++) {
    		char c = s.charAt(i);
    		if (map.containsKey(c)) {
				start = Math.max(start, map.get(c) + 1);
			}
    		map.put(c, i);
    		maxLen = Math.max(maxLen, i - start + 1);
		}
        return maxLen;
    }
    
    public static void main(String[] args) {
//		NoRepeatingSubstring substring = new NoRepeatingSubstring();
		NoRepeatingSubstring substring = new ImprovedMapSolution();
		System.out.println(substring.lengthOfLongestSubstring("")); 		// 0
		System.out.println(substring.lengthOfLongestSubstring("a")); 		// 1
		System.out.println(substring.lengthOfLongestSubstring("aaaa")); 	// 1
		System.out.println(substring.lengthOfLongestSubstring("abc")); 		// 3
		System.out.println(substring.lengthOfLongestSubstring("aba")); 		// 2
		System.out.println(substring.lengthOfLongestSubstring("abcabcbb")); // 3
		System.out.println(substring.lengthOfLongestSubstring("pwwkew")); 	// 3
		System.out.println(substring.lengthOfLongestSubstring("abb")); 		// 2
	}
}

class ImprovedMapSolution extends NoRepeatingSubstring {

	public int lengthOfLongestSubstring(String s) {
    	int n = s.length();
    	if (n == 0) return 0;
    	int[] map = new int[256];
    	Arrays.fill(map, -1);
    	int max = 1, start = 0;
    	for (int i = 0; i < n; i++) {
    		char c = s.charAt(i);
			if (map[c] >= start) {
				max = Math.max(max, i - start);
				start = map[c] + 1;
			}
			map[c] = i;
		}
    	return Math.max(max, n - start);
	}
}