package problems.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 * 
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example,
 * 
 * [1,1,2] have the following unique permutations:
 * 
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * 
 * @see https://leetcode.com/problems/permutations-ii/
 *
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	dfs(nums, 0, res);
    	return res;
    }
    
    private void dfs(int[] nums, int pos, List<List<Integer>> res) {
		if (pos >= nums.length - 1) {
			List<Integer> cur = new ArrayList<>(nums.length);
			for (int n : nums) {
				cur.add(n);
			}
			res.add(cur);
		} else {
			for (int i = pos; i < nums.length; i++) {
				if (hasDuplicate(nums, pos, i)) continue;
				swap(nums, i, pos);
				dfs(nums, pos + 1, res);
				swap(nums, i, pos);
			}
		}
	}
    
    private boolean hasDuplicate(int[] nums, int begin, int idx) {
		for (int i = begin; i < idx; i++) {
			if (nums[i] == nums[idx]) return true;
		}
		return false;
	}
    
    private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}
    
    // -------------------
    private static PermutationsII permutations = new PermutationsII();
    
    private static void test(int[] nums) {
		List<List<Integer>> res = permutations.permuteUnique(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
		System.out.println(res.size());
		System.out.println("-------------------");
	}
    
    public static void main(String[] args) {
		test(new int[]{});
		test(new int[]{1});
		test(new int[]{1,2});
		test(new int[]{1,1});
		test(new int[]{1,2,3});
		test(new int[]{1,1,3});
		test(new int[]{1,2,2});
		test(new int[]{3,3,0,3});
		test(new int[]{0,1,0,0,9});
		test(new int[]{-1,2,0,-1,1,0,1});
	}
    
}

class ExtraSpace extends PermutationsII {
	
	public List<List<Integer>> permuteUnique(int[] nums) {
		int n = nums.length;
    	List<List<Integer>> res = new ArrayList<>();
    	Arrays.sort(nums);
    	dfs(nums, new boolean[n], new ArrayList<Integer>(n), res);
    	return res;
	}
	
	private void dfs(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> res) {
		if (cur.size() == nums.length) {
			res.add(new ArrayList<>(cur));
		} else {
			for (int i = 0; i < used.length; i++) {
				if (used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1])) continue;
				used[i] = true;
				cur.add(nums[i]);
				dfs(nums, used, cur, res);
				cur.remove(cur.size()-1);
				used[i] = false;
			}
		}
	}
	
}
