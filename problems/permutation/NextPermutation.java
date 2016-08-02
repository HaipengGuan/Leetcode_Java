package problems.permutation;

import java.util.Arrays;

/**
 * 31. Next Permutation
 * 
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2 
 * 3,2,1 → 1,2,3 
 * 1,1,5 → 1,5,1
 * 
 * @see https://leetcode.com/problems/next-permutation/
 *
 */
public class NextPermutation {

	public void nextPermutation(int[] nums) {
		int n = nums.length, idx1, idx2;
		for (idx2 = n - 1; idx2 - 1 >= 0 && nums[idx2 - 1] >= nums[idx2]; idx2--);
		if (idx2 > 0) {
			for (idx1 = idx2; idx1 + 1 < n && nums[idx2 - 1] < nums[idx1 + 1]; idx1++);
			swap(nums, idx1, idx2 - 1);
		}
		reverse(nums, idx2, nums.length - 1);
	}

	private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}

	public void reverse(int[] nums, int low, int high) {
		while (low < high) {
			swap(nums, low++, high--);
		}
	}

	// ---------------------------------
	private static NextPermutation next = new NextPermutation();

	private static void test(int[] nums) {
		int sum = 1;
		for (int i = 2; i <= nums.length; i++) {
			sum *= i;
		}
		for (int i = 0; i < sum; i++) {
			System.out.println(Arrays.toString(nums));
			next.nextPermutation(nums);
		}
		System.out.println("----------------------");
	}

	public static void main(String[] args) {
		test(new int[] { 1, 2, 3 });
		test(new int[] { 1, 1, 5 });
	}
}
