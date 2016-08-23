package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. Largest Number
 * 
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * @see https://leetcode.com/problems/largest-number/
 *
 */
public class LargestNumber {
	
    public String largestNumber(int[] nums) {
    	int n = nums.length;
    	if (n == 0) return "";
    	String[] numStrs = new String[n];
    	for (int i = 0; i < n; i++) {
			numStrs[i] = Integer.toString(nums[i]);
		}
    	Arrays.sort(numStrs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1+o2);
			}
		});
    	if (numStrs[0].charAt(0) == '0') return "0";
    	StringBuffer buffer = new StringBuffer();
    	for (String number : numStrs) {
			buffer.append(number);
		}
    	return buffer.toString();
    }
    
    // -----------------------------------
	private static final LargestNumber number = new LargestNumber();
	
    private static void test(int[] nums, String ans) {
		if (number.largestNumber(nums).equals(ans)) {
			System.out.println("PASS");
		} else {
			System.out.println(Arrays.toString(nums));
		}
	}
    
    public static void main(String[] args) {
    	test(new int[]{}, "");
    	test(new int[]{1}, "1");
    	test(new int[]{0, 0}, "0");
    	test(new int[]{3, 30, 34, 5, 9}, "9534330");
    	test(new int[]{3, 31}, "331");
    	test(new int[]{31, 31}, "3131");
    	test(new int[]{20, 1}, "201");
    	test(new int[]{1,2,3,4,5,6,7,8,9,0}, "9876543210");
    	test(new int[]{121,12}, "12121");
    	test(new int[]{883,8}, "8883");
    	test(new int[]{824,938,1399,5607,6973,5703,9609,4398,8247}, "9609938824824769735703560743981399");
	}
    
}
