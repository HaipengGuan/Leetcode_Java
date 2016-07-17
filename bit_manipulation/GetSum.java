package bit_manipulation;

/**
 * 371. Sum of Two Integers
 * 
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 * 
 * Example:
 * Given a = 1 and b = 2, return 3.
 * 
 * @see https://leetcode.com/problems/sum-of-two-integers/
 *
 */
public class GetSum {
	
    public int getSum(int a, int b) {
        int carry = 0, bitSum = 0, ans = 0, tail = 1;
        while (a != 0 || b != 0) {
        	int lowerbit_a = a & 0x01, lowerbit_b = b & 0x01;
			bitSum = lowerbit_a ^ lowerbit_b ^ carry;
			carry = (lowerbit_a & lowerbit_b) | (lowerbit_a ^ lowerbit_b) & carry;
        	if (bitSum == 1) ans |= tail;
        	a >>>= 1;
        	b >>>= 1;
        	tail <<= 1;
        }
        if (carry != 0) {
        	ans |= tail;
		}
        return ans;
    }
    
    public static void main(String[] args) {
    	GetSum sum = new GetSumNoLoop();
		System.out.println(sum.getSum(7, 7));
	}
}

class GetSumNoLoop extends GetSum {
	
	public int getSum(int a, int b) {
		if (b == 0) return a;
		else if (a == 0) return b;
		else return getSum(a ^ b, (a & b) << 1);
	}
}
