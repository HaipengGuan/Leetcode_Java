package array.majority_element;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 * 
 * Given an integer array of size n, find all elements that appear more than 
 * ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 * 
 * @see https://leetcode.com/problems/majority-element-ii/
 *
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> res = new ArrayList<>();
    	int n = nums.length;
    	if (n == 0) return res;
    	int cand1 = 0, cand2 = nums[0], count1 = 0, count2 = 0;
    	for (int i : nums) {
			if (i == cand1) {
				count1++;
			} else if (i == cand2) {
				count2++;
			} else if (count1 == 0) {
				cand1 = i;
				count1++;
			} else if (count2 == 0) {
				cand2 = i;
				count2++;
			} else {
				count1--; count2--;
			}
		}
    	count1 = count2 = 0;
    	for (int i : nums) {
			if (i == cand1) count1++;
			else if (i == cand2) count2++;
		}
    	if (count1 > n/3) res.add(cand1);
    	if (count2 > n/3) res.add(cand2);
    	return res;
    }
    
    // ------------------------
    private static MajorityElementII majorityElementII = new MajorityElementII();
    
    public static void main(String[] args) {
		System.out.println(majorityElementII.majorityElement(new int[]{}));
		System.out.println(majorityElementII.majorityElement(new int[]{1}));
		System.out.println(majorityElementII.majorityElement(new int[]{0,3,4,0}));
		System.out.println(majorityElementII.majorityElement(new int[]{0,0,1,1,1}));
		System.out.println(majorityElementII.majorityElement(new int[]{1,1,1,1,1,1}));
		System.out.println(majorityElementII.majorityElement(new int[]{1,1,2,2,3,3}));
		System.out.println(majorityElementII.majorityElement(new int[]{1,1,1,2,3,3}));
		System.out.println(majorityElementII.majorityElement(new int[]{1,1,1,3,3,3}));
	}
	
}
