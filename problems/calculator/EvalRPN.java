package problems.calculator;

/**
 * 150. Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * Some examples:
 * 
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @see 
 *
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = -1;
        for (String t : tokens) {
        	switch (t) {
    		case "+":
    			stack[--top] = stack[top] + stack[top+1];
				break;
    		case "-":
    			stack[--top] = stack[top] - stack[top+1];
				break;
    		case "*":
    			stack[--top] = stack[top] * stack[top+1];
				break;
    		case "/":
    			stack[--top] = stack[top] / stack[top+1];
				break;
			default:
				stack[++top] = Integer.parseInt(t);
			}
		}
        return stack[0];
    }
    
    // ------------------------
    private static EvalRPN eval = new EvalRPN();
    
    public static void main(String[] args) {
		System.out.println(eval.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
		System.out.println(eval.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
	}
    
}
