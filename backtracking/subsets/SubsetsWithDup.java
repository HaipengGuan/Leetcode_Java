package backtracking.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * 
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,2], a solution is:
 * 
 * [ [], [1], [2], [1,2], [2,2], [1,2,2] ]
 * 
 * @see	https://leetcode.com/problems/subsets-ii/
 *
 */
public class SubsetsWithDup {

	private static int LEN;
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		LEN = nums.length;
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		dfs(nums, 0, new ArrayList<Integer>(LEN), res);
		return res;
	}

	private void dfs(int[] nums, int pos, List<Integer> cur, List<List<Integer>> res) {
		if (pos <= LEN) 
			res.add(new ArrayList<>(cur));
		for (int i = pos; i < LEN; i++) {
			if (i > pos && nums[i] == nums[i-1]) continue;
			cur.add(nums[i]);
			dfs(nums, i+1, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		SubsetsWithDup subsets = new SubsetsWithDup();
		System.out.println(subsets.subsetsWithDup(new int[]{}));
		System.out.println(subsets.subsetsWithDup(new int[]{1}));
		System.out.println(subsets.subsetsWithDup(new int[]{1,1}));
		System.out.println(subsets.subsetsWithDup(new int[]{1,2}));
		System.out.println(subsets.subsetsWithDup(new int[]{1,2,2}));
		System.out.println(subsets.subsetsWithDup(new int[]{1,2,3}));
		System.out.println(subsets.subsetsWithDup(new int[]{1,1,1,1,1}));
	}

}
