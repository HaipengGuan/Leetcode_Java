package math;

/**
 * 69. Sqrt(x)
 * 
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * @see https://leetcode.com/problems/sqrtx/
 */
public class Sqrt {

	/**
	 * Newton's method
	 * 
	 * X_n+1 = 1/2 * (X_n + S / X_n)
	 * 
	 * @see https://en.wikipedia.org/wiki/Newton%27s_method
	 * @see https://en.wikipedia.org/wiki/Methods_of_computing_square_roots
	 */
    public int mySqrt(int s) {
    	if (s == 0) return 0;
    	double x0 = s / 2.0;
    	double x1 = (x0 + s / x0) / 2.0;
    	while (Math.abs(x1 - x0) > 0.01) {
			x0 = x1;
			x1 = (x0 + s / x0) / 2.0;
		}
    	return (int)x1;
    }
}
