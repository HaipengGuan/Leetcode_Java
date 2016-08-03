package array;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 * 
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 * 
 * Note:
 * - You must do this in-place without making a copy of the array.
 * - Minimize the total number of operations.
 * 
 * @see https://leetcode.com/problems/move-zeroes/
 *
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
		int idx = 0, n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] != 0) nums[idx++] = nums[i];
		}
		for (; idx < n; idx++) {
			nums[idx] = 0;
		}
	}
	
	// --------------------------------------------
	private static MoveZeroes move = new MoveZeroes();
	
	private static void test(int[] nums) {
		System.out.println(Arrays.toString(nums));
		move.moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println("------------------");
	}
	
	public static void main(String[] args) {
		test(new int[]{});
		test(new int[]{0});
		test(new int[]{1});
		test(new int[]{1,0});
		test(new int[]{0,1});
		test(new int[]{1,0,3,0,5,0,0,6});
		test(new int[]{0,0,0,0,0,1,2,3,4});
	}
}
