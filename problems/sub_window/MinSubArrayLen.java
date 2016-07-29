package problems.sub_window;

/**
 * 209. Minimum Size Subarray Sum
 * 
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return
 * 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has
 * the minimal length under the problem constraint.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 * 
 * @see https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 */
public class MinSubArrayLen {
	
    public int minSubArrayLen(int s, int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int minLen = Integer.MAX_VALUE, sum = 0;
        for (int begin = 0, end = 0; end < n; end++) {
        	sum += nums[end];
        	for (; sum >= s; begin++) {
        		minLen = Math.min(minLen, end - begin + 1);
        		sum -= nums[begin];
        	}
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    public static void main(String[] args) {
		MinSubArrayLen subArrayLen = new MinSubArrayLen();
		System.out.println(subArrayLen.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
		System.out.println(subArrayLen.minSubArrayLen(100, new int[]{2,3,1,2,4,3}));
	}
}
