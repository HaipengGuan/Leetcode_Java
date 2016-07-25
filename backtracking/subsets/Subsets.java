package backtracking.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,3], a solution is:
 * [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
 * 
 * @see https://leetcode.com/problems/subsets/
 *
 */
public class Subsets {
    
    private static int LEN;
	
    public List<List<Integer>> subsets(int[] nums) {
        LEN = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(LEN), res);
        return res;
    }
    
	private void dfs(int[] nums, int pos, List<Integer> cur, List<List<Integer>> res) {
		if (pos <= LEN) res.add(new ArrayList<>(cur));
		for (int i = pos; i < LEN; i++) {
			cur.add(nums[i]);
			dfs(nums, i+1, cur, res);
			cur.remove(cur.size()-1);
		}
	}
	
	private void dfs(int[] nums, int pos, int len, List<Integer> cur, List<List<Integer>> res) {
		int m = cur.size();
		if (m == len) {
			res.add(new ArrayList<>(cur));
		} else {
			int limitInd = LEN - (len - m);
			for (int i = pos; i <= limitInd; i++) {
				cur.add(nums[i]);
				dfs(nums, i+1, len, cur, res);
				cur.remove(m);
			}
		}
	}
    
	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		System.out.println(subsets.subsets(new int[] {}));
		System.out.println(subsets.subsets(new int[] {1}));
		System.out.println(subsets.subsets(new int[] {1,2}));
		System.out.println(subsets.subsets(new int[] {1,2,3}));
	}
}

class BitManipulation extends Subsets {
	
	private final static int[] MultiplyDeBruijnBitPosition = { 
		0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8,
		31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 
	};

	public List<List<Integer>> subsets(int[] nums) {
		int n = nums.length;
		int FULL = (1 << n);
		List<List<Integer>> res = new ArrayList<>(FULL);
		for (int bits = 0; bits < FULL; bits++) {
			res.add(getSubset(nums, bits));
		}
		return res;
	}

	private List<Integer> getSubset(int[] nums, int bits) {
		List<Integer> res = new ArrayList<>();
		int lower = 0, ind;
		while (bits != 0) {
			lower = bits & (~bits + 1);
			ind = MultiplyDeBruijnBitPosition[(lower * 0x077CB531) >>> 27];
			res.add(nums[ind]);
			bits -= lower;
		}
		return res;
	}
	
//	private int lowerbit(int n) {
//		return n & (~n + 1);
//	}
//	
//	private int tailingZeros(int n) {
//		return MultiplyDeBruijnBitPosition[((n & (~n + 1)) * 0x077CB531) >>> 27];
//	}
}
