package design;

import java.util.Stack;

public abstract class MinStack {
    
    public abstract void push(int x);
    
    public abstract void pop();
    
    public abstract int top();
    
    public abstract int getMin();
    
}

//------------------------------
// if incoming value is less or equals to min
// push min at first, then push the value
class DoublePush extends MinStack {

	private Stack<Integer> stack = new Stack<>();
	private int min = Integer.MAX_VALUE;
	
	@Override
	public void push(int x) {
		if (x <= min) {
			stack.push(min);
			min = x;
		}
		stack.push(x);
	}

	@Override
	public void pop() {
		if (min == stack.pop()){
			min = stack.pop();
		}
		
	}

	@Override
	public int top() {
		return stack.peek();
	}

	@Override
	public int getMin() {
		return min;
	}
	
}

// ------------------------------
// push the gap of current value and min
class PushGap extends MinStack {
	
	private Stack<Long> stack = new Stack<>();
	private int min = Integer.MAX_VALUE;
	
	@Override
	public void push(int x) {
		if(stack.isEmpty()) {
			stack.push(0L);
			min = x;
		} else {
			stack.push((long)(x - min));
			if (x < min) min = x;
		}
	}

	@Override
	public void pop() {
		long top = stack.pop();
		if (top < 0) min -= top;
	}

	@Override
	public int top() {
		long top = stack.peek();
		return (top > 0) ? (int)(top + min) : min;
	}

	@Override
	public int getMin() {
		return min;
	}
	
}