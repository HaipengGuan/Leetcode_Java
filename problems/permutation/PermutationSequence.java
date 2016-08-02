package problems.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 60. Permutation Sequence
 * 
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the
 * following sequence (ie, for n = 3):
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * @see https://leetcode.com/problems/permutation-sequence/
 *
 */
public class PermutationSequence {
	
    public String getPermutation(int n, int k) {
    	int[] factorial = new int[n+1];
    	factorial[0] = 1;
    	for (int i = 1; i <= n; i++) {
			factorial[i] = factorial[i-1] * i;
		}
    	char[] chars = new char[n];
    	for (int i = 0; i < n; i++) {
    		chars[i] = (char)('1' + i);
    	}
    	for (int i = 1; i <= n && k > 1; i++) {
    		int idx = (k-1) / factorial[n-i];
    		swap(chars, i-1, idx+i-1);
    		Arrays.sort(chars, i, n);
    		k -= idx * factorial[n-i];
    	}
    	return new String(chars);
    }

	private void swap(char[] chars, int i, int j) {
		if (chars[i] != chars[j]) {
			chars[i] ^= chars[j];
			chars[j] ^= chars[i];
			chars[i] ^= chars[j];
		}
	}
	        
    // ---------------------------------
	private static PermutationSequence sequence = new PermutationSequence();
    
	private static void test(int n) {
		int sum = 1;
		for (int i = 2; i <= n; i++) {
			sum *= i;
		}
		for (int i = 0; i < sum; i++) {
			System.out.println(sequence.getPermutation(n, i+1));
		}
		System.out.println("----------------");
	}
		
    public static void main(String[] args) {
    	test(3);
    	System.out.println(sequence.getPermutation(8, 8590).equals("26847351"));
	}
    
}

class UseList extends PermutationSequence {

	public String getPermutation(int n, int k) {
		int[] factorial = new int[n + 1];
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			factorial[i] = factorial[i - 1] * i;
		}
		List<Character> chars = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			chars.add((char) ('1' + i));
		}
		StringBuffer res = new StringBuffer(n);
		for (int i = 1; i <= n; i++) {
			int idx = (k - 1) / factorial[n - i];
			res.append(chars.get(idx));
			chars.remove(idx);
			k -= idx * factorial[n - i];
		}
		return res.toString();
	}

}
