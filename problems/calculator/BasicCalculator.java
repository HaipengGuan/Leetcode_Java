package problems.calculator;

/**
 * 224. Basic Calculator
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces .
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * 
 * @see https://leetcode.com/problems/basic-calculator/
 *
 */
public class BasicCalculator extends Calculator {

	@Override
	public int calculate(String s) {
		return calculate(s.toCharArray());
	}

	public int calculate(char[] s) {
		int n = s.length, top1 = -1, top2 = -1;
		int[] out = new int[n];
		char[] op = new char[n];
		for (int i = 0; i < n; i++) {
			char c = s[i];
			if (c == ' ') continue;
			if ('0' <= c && c <= '9') {
				for(out[++top2] = c - '0'; i+1 < n && '0' <= s[i+1] && s[i+1] <= '9'; i++)  {
					out[top2] = out[top2] * 10 + s[i + 1] - '0';
				}
			} else if (c == '+' || c == '-') {
				while (top1 >= 0 && (op[top1] == '+' || op[top1] == '-')) {
					top2 = calc(out, top2, op[top1--]);
				}
				op[++top1] = c;
			} else if (c == '(') {
				op[++top1] = c;
			} else if (c == ')') {
				while (top1 >= 0 && op[top1] != '(') {
					top2 = calc(out, top2, op[top1--]);
				}
				top1--;
			}
		}
		while (top1 >= 0) top2 = calc(out, top2, op[top1--]);
		return out[0];
	}
	
	private int calc(int[] stack, int top, char op) {
		if (op == '+') {
			stack[--top] = stack[top] + stack[top+1];
		} else if (op == '-') {
			stack[--top] = stack[top] - stack[top+1];
		}
		return top;
	}
	
	// --------------------
	private static BasicCalculator calculator = new BasicCalculator();
	
	public static void main(String[] args) {
		System.out.println(calculator.calculate(" 0  "));
		System.out.println(calculator.calculate(" 1 + 1 "));
		System.out.println(calculator.calculate(" 12 + 13 "));
		System.out.println(calculator.calculate(" 2-1 + 2 "));
		System.out.println(calculator.calculate(" 2-(1 + 2) "));
		System.out.println(calculator.calculate(" (1+(4+5+2)-3)+(6+8) "));
	}

}