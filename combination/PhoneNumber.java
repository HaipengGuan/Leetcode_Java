package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 * 
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * @see https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class PhoneNumber {
	
	private final static char[][] MAP = {
			{},
			{},
			{'a','b','c'},
			{'d','e','f'},
			{'g','h','i'},
			{'j','k','l'},
			{'m','n','o'},
			{'p','q','r','s'},
			{'t','u','v'},
			{'w','x','y','z'}
	};
	
    public List<String> letterCombinations(String digits) {
    	int n = digits.length();
    	List<String> res = new ArrayList<>();
    	StringBuffer cur = new StringBuffer(n);
    	dfs(digits, n, 0, res, cur);
    	return res;
    }
    
    private void dfs(String digits, int len, int ind, List<String> res, StringBuffer cur) {
    	if (len == 0) return;
		if (ind == len) {
			res.add(new String(cur));
		} else {
			int digit = digits.charAt(ind) - '0';
			for (char c : MAP[digit]) {
				cur.append(c);
				dfs(digits, len, ind+1, res, cur);
				cur.deleteCharAt(cur.length()-1);
			}
		}
	}
}
