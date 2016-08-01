package problems.parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public List<String> removeInvalidParentheses(String s) {
		Set<String> res = new HashSet<>();
		int removeLeft = 0, removeRight = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				removeLeft++;
			} else if (c == ')') {
				if (removeLeft > 0) removeLeft--;
				else removeRight++;
			}
		}
		dfs(s, 0, removeLeft, removeRight, 0, new StringBuffer(), res);
		return new ArrayList<>(res);
	}
	
	private void dfs(String s, int pos, int left, int right, int open, StringBuffer cur, Set<String> res) {
		if (pos >= s.length() && left == 0 && right == 0 && open == 0) {
			res.add(new String(cur));
		} else if (pos >= s.length() || left < 0 || right < 0 || open < 0) {
			return;
		} else {
			char c = s.charAt(pos);
			int len = cur.length();
			if (c == '(') {
				dfs(s, pos+1, left-1, right, open, cur, res);
				dfs(s, pos+1, left, right, open+1, cur.append(c), res);
			} else if (c == ')') {
				dfs(s, pos+1, left, right-1, open, cur, res);
				dfs(s, pos+1, left, right, open-1, cur.append(c), res);
			} else {
				dfs(s, pos+1, left, right, open, cur.append(c), res);
			}
			cur.deleteCharAt(len);
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
