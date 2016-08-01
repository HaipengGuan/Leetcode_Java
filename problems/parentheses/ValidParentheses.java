package problems.parentheses;

/**
 * 20. Valid Parentheses
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * 
 * @see https://leetcode.com/problems/valid-parentheses/
 *
 */
public class ValidParentheses {

    public boolean isValid(String s) {
    	char[] stack = new char[s.length()];
    	int[] map = new int[128];
    	map[')'] = '(';
    	map[']'] = '[';
    	map['}'] = '{';
    	int top = -1;
    	for (char c : s.toCharArray()) {
    		if (top > -1 && stack[top] == map[c]) {
    			top--;
    		} else {
    			stack[++top] = c;
    		}
    	}
    	return top == -1;
    }
    
    public static void main(String[] args) {
		ValidParentheses valid = new ValidParentheses();
		System.out.println(valid.isValid(""));
		System.out.println(valid.isValid("["));
		System.out.println(valid.isValid("]"));
		System.out.println(valid.isValid("()"));
		System.out.println(valid.isValid("([)]")); // false
		System.out.println(valid.isValid("()[]{}"));
		System.out.println(valid.isValid("{[()]}"));
	}
}