package array.majority_element;

import java.util.Arrays;

/**
 * 169. Majority Element
 * 
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 * 
 * @see https://leetcode.com/problems/majority-element/
 *
 */
public class MajorityElement {
	
	// Boyer–Moore majority vote algorithm
	// https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
	public int majorityElement(int[] nums) {
		int major = nums[0], count = 0;
		for (int n : nums) {
			if (count == 0) {
				major = n;
				count++;
			} else if (n == major) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}
    
}

class Sorting extends MajorityElement {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}