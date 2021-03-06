package array;

/**
 * 167. Two Sum II - Input array is sorted
 * 
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * @see https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 */
public class SortedTwoSum {
	
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        int sum = numbers[low] + numbers[high];
        while (sum != target) {
			if (sum < target) {
				sum = numbers[++low] + numbers[high];
			} else {
				sum = numbers[low] + numbers[--high];
			}
		}
        return new int[]{low+1, high+1};
    }

}
