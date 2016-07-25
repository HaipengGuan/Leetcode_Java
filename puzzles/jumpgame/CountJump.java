package puzzles.jumpgame;

/**
 * 45. Jump Game II
 * 
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * Note: 
 * You can assume that you can always reach the last index.
 * 
 * @author hpguan
 *
 */
public class CountJump {
	
    public int jump(int[] nums) {
        int n = nums.length;
        int min = 0, current = 0, next = 0;
        for (int i = 0; i < n - 1; i++) {
			next = Math.max(next, nums[i] + i);
			if (i == current) {
				min++;
				current = next;
			}
		}
        return min;
    }

    
    public static void main(String[] args) {
		CountJump jump = new CountJump();
		System.out.println(jump.jump(new int[] {0})); 			// 0
		System.out.println(jump.jump(new int[] {1,0})); 		// 1
		System.out.println(jump.jump(new int[] {1,1,1,1,1})); 	// 4
		System.out.println(jump.jump(new int[] {2,3,1,1,4})); 	// 2
	}
}
