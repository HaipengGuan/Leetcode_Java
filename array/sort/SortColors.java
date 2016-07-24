package array.sort;

import java.util.Arrays;

import lib.Lib;

/**
 * 75. Sort Colors
 * 
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up:
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * @see https://leetcode.com/problems/sort-colors/
 */
public class SortColors {

    public void sortColors(int[] nums) {
    	int zero = 0, one = 0, two = 0, ind = 0;
    	for (int n : nums) {
			if (n == 0) zero++;
			else if (n == 1) one++;
			else two++;
		}
    	while (zero-- > 0) nums[ind++] = 0;
    	while (one-- > 0) nums[ind++] = 1;
    	while (two-- > 0) nums[ind++] = 2;
    }
	
	public static void main(String[] args) {
		int[] data = {1,2,0};
		Lib.shuffleArray(data);
		SortColors sort = new OnePassSolution();
		sort.sortColors(data);
		System.out.println(Arrays.toString(data));
	}

}

/**
 * It is like maintain two stacks
 * One is start from index=0, keep adding 0
 * another start from index=n-1, keep adding 2
 */
class OnePassSolution extends SortColors {
	
	@Override
	public void sortColors(int[] nums) {
		int n = nums.length;
		int zeroInd = 0, twoInd = n - 1;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0 && i > zeroInd) {
				swap(nums, zeroInd++, i--);
			} else if (nums[i] == 2 && i < twoInd) {
				swap(nums, twoInd--, i--);
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
}
