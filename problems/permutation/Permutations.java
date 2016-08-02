package problems.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 * 
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * For example, 
 * [1,2,3] have the following permutations:
 * 
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 
 * @see https://leetcode.com/problems/permutations/
 *
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
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
				swap(nums, i, pos);
				dfs(nums, pos + 1, res);
				swap(nums, i, pos);
			}
		}
	}
    
    private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}
    
    // ---------------------
	private static Permutations permutations = new Permutations();
    
	private static void test(int[] nums) {
		List<List<Integer>> res = permutations.permute(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
		System.out.println("-------------------");
	}
    
    public static void main(String[] args) {
		test(new int[]{});
		test(new int[]{1});
		test(new int[]{1,2});
		test(new int[]{1,2,3});
	}
    
}
