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
// best approach
class PushGap extends MinStack {
	
	private Stack<Long> stack = new Stack<>();
	private long min = Integer.MAX_VALUE;
	
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
		return (top > 0) ? (int)(top + min) : (int)min;
	}

	@Override
	public int getMin() {
		return (int)min;
	}
	
}

//------------------------------
// simulate re-ensure capacity process
class NoCollection extends MinStack {
	
	private long[] stack = new long[100];
	private int capacity = 100;
	private int length = 0;
	private long min = Integer.MAX_VALUE;
	
	@Override
	public void push(int x) {
		if (length + 1 > capacity) {
			long[] stack2 = new long[2*capacity];
			System.arraycopy(stack, 0, stack2, 0, length);
			capacity *= 2;
			stack = stack2;
		}
		if (length == 0) {
			stack[length++] = 0L;
			min = x;
		} else {
			stack[length++] = (long)(x - min);
			if (x < min) min = x;
		}
	}

	@Override
	public void pop() {
		long top = stack[--length];
		if (top < 0) min -= top;
	}

	@Override
	public int top() {
		long top = stack[length-1];
		return (top > 0) ? (int)(top + min) : (int)min;
	}

	@Override
	public int getMin() {
		return (int)min;
	}
	
}