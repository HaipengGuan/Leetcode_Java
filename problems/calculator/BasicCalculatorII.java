package problems.calculator;

/**
 * 227. Basic Calculator II
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward
 * zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * 
 * @see https://leetcode.com/problems/basic-calculator-ii/
 *
 */
public class BasicCalculatorII extends Calculator {

	@Override
	public int calculate(String s) {
		return calculate(s.toCharArray());
	}

	private int calculate(char[] s) {
		int n = s.length, top1 = -1, top2 = -1;
		int[] out = new int[n];
		char[] op = new char[n];
		for (int i = 0; i < n; i++) {
			char c = s[i];
			if (c == ' ') continue;
			if ('0' <= c && c <= '9') {
				for (out[++top2] = c - '0'; i + 1 < n && '0' <= s[i+1] && s[i+1] <= '9'; i++) {
					out[top2] = out[top2] * 10 + s[i+1] - '0';
				}
			} else if (c == '-' || c == '+') {
				while (top1 >= 0) top2 = calc(out, top2, op[top1--]);
				op[++top1] = c;
			} else if (c == '*' || c == '/') {
				while (top1 >= 0 && (op[top1] == '*' || op[top1] == '/')) {
					top2 = calc(out, top2, op[top1--]);
				}
				op[++top1] = c;
			}
		}
		while (top1 >= 0) top2 = calc(out, top2, op[top1--]);
		return out[0];
	}
	
	private int calc(int[] stack, int top, char op) {
		switch (op) {
		case '+':
			stack[--top] = stack[top] + stack[top+1];
			break;
		case '-':
			stack[--top] = stack[top] - stack[top+1];
			break;
		case '*':
			stack[--top] = stack[top] * stack[top+1];
			break;
		case '/':
			stack[--top] = stack[top] / stack[top+1];
			break;
		default:
			break;
		}
		return top;
	}
	
	private static BasicCalculatorII calculatorII = new BasicCalculatorII();
	
	public static void main(String[] args) {
		System.out.println(calculatorII.calculate(" 3+2*2 "));
		System.out.println(calculatorII.calculate(" 3/2 "));
		System.out.println(calculatorII.calculate(" 3+5 / 2 "));
	}

}
