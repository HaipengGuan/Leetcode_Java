package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * 
 * @see https://leetcode.com/problems/generate-parentheses/
 *
 */
public class GenerateParentheses {

	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfs(n, 0, res, new char[2 * n], 0);
		return res;
	}

	private void dfs(int left, int right, List<String> res, char[] cur, int ind) {
		if (left == 0 && right == 0) {
			res.add(new String(cur));
		} else {
			if (right > 0) {
				cur[ind] = ')';
				dfs(left, right - 1, res, cur, ind + 1);
			}
			if (left > 0) {
				cur[ind] = '(';
				dfs(left - 1, right + 1, res, cur, ind + 1);
			}
		}
	}

	public static void main(String[] args) {
		GenerateParentheses generate = new GenerateParentheses();
		System.out.println(generate.generateParenthesis(0));
		System.out.println(generate.generateParenthesis(1));
		System.out.println(generate.generateParenthesis(2));
		System.out.println(generate.generateParenthesis(3));
	}
}
