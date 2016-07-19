package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 * 
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj %
 * Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 * 
 * @see https://leetcode.com/problems/largest-divisible-subset/
 *
 */
public class LargestDivisibleSubset {

	public List<Integer> largestDivisibleSubset(int[] nums) {
		int n = nums.length;
		List<Integer> res = new ArrayList<>();
		if (n == 0) return res;
		Arrays.sort(nums);
		int[] len = new int[n];
		int[] last = new int[n];
		int maxInd = 0;
		len[0] = 1;
		for (int i = 1; i < n; i++) {
			len[i] = 1; last[i] = i;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0 && len[j] + 1 > len[i]) {
					len[i] = len[j] + 1;
					last[i] = j;
					if (len[i] > len[maxInd]) maxInd = i;					
				}				
			}
		}
		res.add(nums[maxInd]);
		while (maxInd != last[maxInd]) {
			maxInd = last[maxInd];
			res.add(nums[maxInd]);
		}
		return res;
	}

	public static void main(String[] args) {
		LargestDivisibleSubset subset = new LargestDivisibleSubset();
		System.out.println(subset.largestDivisibleSubset(new int[] {}));
		System.out.println(subset.largestDivisibleSubset(new int[] {1}));
		System.out.println(subset.largestDivisibleSubset(new int[] {4,3,2,1}));
		System.out.println(subset.largestDivisibleSubset(new int[] {30,8,6,4,3,2,1}));
		System.out.println(subset.largestDivisibleSubset(new int[] {1,2,3,4,5,6,7,8, 30, 60}));
	}

}
