package problems.sub_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 30. Substring with Concatenation of All Words
 * 
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * @see https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 */
public class ConcatenationSubstring {
	
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> res = new ArrayList<>();
    	if (s.length() == 0 || words.length == 0) return res;
    	int n = s.length(), m = words.length, wordLen = words[0].length(), totalLen = m * wordLen;
    	HashMap<String, Integer> dict = new HashMap<>(), dict2 = new HashMap<>();
    	for (String word : words) {
    		if (dict.containsKey(word)) {
    			dict.put(word, dict.get(word) + 1);
    		} else {
    			dict.put(word, 1);
    		}
		}
    	String prefix, suffix;
    	for (int i = 0; i < wordLen && i + totalLen <= n; i++) {
			int begin = i;
			dict2.clear();
			for (int next = i; next + wordLen <= n; next += wordLen) {
				suffix = s.substring(next, next+wordLen);
				int curCount = 0;
				if (!dict.containsKey(suffix)) {
					begin = next + wordLen;
					if (begin + totalLen > n) break;
					dict2.clear();
				} else if (!dict2.containsKey(suffix) || (curCount = dict2.get(suffix)) < dict.get(suffix)) {
					dict2.put(suffix, curCount + 1);
					if (begin + totalLen == next + wordLen) {
						res.add(begin);
						decrease(dict2, s.substring(begin, begin+wordLen));
						begin += wordLen;
					}
				} else {
					while (!(prefix = s.substring(begin, begin+wordLen)).equals(suffix)) {
						decrease(dict2, prefix);
						begin += wordLen;
					}
					begin += wordLen;
					if (begin + totalLen > n) break;
				}
			}
		}
        return res;
    }
    
    private void decrease(HashMap<String, Integer> map, String key) {
		int val = map.get(key);
		if (val == 1) map.remove(key);
		else map.put(key, val - 1);
	}
    
	private static ConcatenationSubstring concatenation = new ConcatenationSubstring();
	
    private static void test(String s, String[] words) {
    	List<Integer> res = concatenation.findSubstring(s, words);
    	System.out.println(res);
    	System.out.println("--------");
	}
   
	public static void main(String[] args) {
		test("0123", new String[]{"1", "2", "3"}); 				// [1]
		test("0123212300123", new String[]{"1", "2", "3"}); 	// [1,3,5,10]
		test("barfoothefoobarman", new String[]{"foo", "bar"}); // [0, 9]
		test("aaa", new String[]{"aa", "aa"}); 					// []
		test("aaaa", new String[]{"aa", "aa"}); 				// [0]
		test("aaaaaaaa", new String[]{"aa", "aa", "aa"}); 		// [0, 1, 2]
		test("a", new String[]{"a"}); 							// [0]
		test("barfoofoobarthefoobarmanz", new String[]{"foo", "bar", "the"}); // [6 ,9, 12]
		test("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}); // [8]
		
	}
}