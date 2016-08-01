package problems.parentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 301. Remove Invalid Parentheses
 * 
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * 
 * @see https://leetcode.com/problems/remove-invalid-parentheses/
 * 
 */
public class RemoveInvalidParentheses {
	
	private static int LEN = 0;
	
	/**
	 * Reference: https://discuss.leetcode.com/topic/30743/easiest-9ms-java-solution
	 * Reference: https://discuss.leetcode.com/topic/28859/java-optimized-dfs-solution-3-ms
	 */
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();
		int removeLeft = 0, removeRight = 0;
		LEN = s.length();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (c == '(') {
				removeLeft++;
			} else if (c == ')') {
				if (removeLeft > 0 ) removeLeft--;
				else removeRight++;
			}
		}
		if (removeLeft == 0 && removeRight == 0) {
			return Arrays.asList(s);
		} else {
			dfs(charArray, 0, removeLeft, removeRight, 0, new StringBuffer(), res);
			return res;
		}
	}
	
	private void dfs(char[] s, int pos, int left, int right, int open, StringBuffer cur, List<String> res) {
		if (pos >= LEN) {
			if (left == 0 && right == 0 && open == 0) {
				res.add(cur.toString());
			}
		} else {
			char c = s[pos];
			int len = cur.length(), i = 1;
			if (c != '(' && c != ')') {
				dfs(s, pos+1, left, right, open, cur.append(c), res);
			} else if (c == '(') {
				while (pos + i < LEN && s[pos + i] == c) i++; // merge duplicate
				if (left > 0) dfs(s, pos + 1, left - 1, right, open, cur, res);
				dfs(s, pos + i, left, right, open + i, cur.append(s, pos, i), res);
			} else if (c == ')') {
				while (pos + i < LEN && s[pos + i] == c) i++; // merge duplicate
				if (right > 0) dfs(s, pos + 1, left, right - 1, open, cur, res);
				if (open - i >= 0) dfs(s, pos+i, left, right, open - i, cur.append(s, pos, i), res);
			}
			cur.setLength(len);
		}
	}
    
    // ---------------------------
	private static final RemoveInvalidParentheses remove = new RemoveInvalidParentheses();
	
	private static void test(String s) {
		List<String> res = remove.removeInvalidParentheses(s);
		for (String string : res) {
			System.out.println(String.format("\"%s\"", string));
		}
		System.out.println("----------------");
	}
	
	private static void test1() {
		test("");
		test(")(");
		test("a");
		test("(a");
		test("a)");
		test(")))))a");
		test("a)))))");
		test("))a))");
		test("a((((");
		test("((((a");
		test("((a((");
		test("))a((");
		test("a)b)c(d(e");
	}
	
	public static void main(String[] args) {
		test1();
		test(")()()(a)()()");
		test("a)b)c)d)()()(a)()()b(c(d(e");
		test("()())()");
	}
	
}
