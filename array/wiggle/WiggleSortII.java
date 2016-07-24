package array.wiggle;

import java.util.Arrays;
import java.util.Collections;

/**
 * 324. Wiggle Sort II
 * 
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] >
 * nums[2] < nums[3]....
 * 
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * 
 * Note:
 * You may assume all input has valid answer.
 * 
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * 
 * @see https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSortII {

    public void wiggleSort(int[] nums) {
    	int n = nums.length;
    	Arrays.sort(nums);
    	int[] cache = new int[n];
    	int ind = 0;
		for (int i = (n % 2 == 0) ? n - 2 : n - 1; i >= 0; i -= 2) {
			cache[i] = nums[ind++];
		}
		for (int i = (n % 2 == 0) ? n - 1 : n - 2; i > 0; i -= 2) {
			cache[i] = nums[ind++];
		}
		System.arraycopy(cache, 0, nums, 0, n);
    }
	
	public static void main(String[] args) {
		int[] data = {1, 3, 2, 2, 3};
		WiggleSortII sort = new WiggleSortII();
		sort.wiggleSort(data);
		System.out.println(Arrays.toString(data));
	}

}

class OOneSpeace extends WiggleSortII {
	
	private int LEN;
	
	public void wiggleSort(int[] nums) {
		LEN = nums.length;
		if (LEN <= 1) return;
		Arrays.sort(nums);
		int mid = nums[LEN / 2], highInd = 0, lowInd = LEN - 1;
		for (int i = 0; i <= lowInd; i++) {
			if (nums[mapped(i)] > mid && i > highInd) {
				swap(nums, mapped(highInd++), mapped(i--));
			} else if (nums[mapped(i)] < mid && i < lowInd) {
				swap(nums, mapped(lowInd--), mapped(i--));
			}
		}
	}
	
	
	private int mapped(int i) {
		return (1 + 2*i) % (LEN|0x01);
	}
	
	private void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}
}
