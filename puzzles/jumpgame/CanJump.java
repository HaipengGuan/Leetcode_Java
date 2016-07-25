package puzzles.jumpgame;

/**
 * 55. Jump Game
 * 
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * 
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * 
 * @see https://leetcode.com/problems/jump-game/
 *
 */
public class CanJump {

    public boolean canJump(int[] nums) {
		int n = nums.length, maxReach = 0;
		for (int i = 0; i < n; i++) {
			if (i > maxReach) return false;
			maxReach = Math.max(maxReach, nums[i] + i);
		}
		return true;
    }
    
	public static void main(String[] args) {
		CanJump jump = new CanJump();
		System.out.println(jump.canJump(new int[]{0}));		// true
		System.out.println(jump.canJump(new int[]{0,1}));	// false
		System.out.println(jump.canJump(new int[]{1,1}));	// true
		System.out.println(jump.canJump(new int[]{1,0}));	// true
		System.out.println(jump.canJump(new int[]{2,3,1,1,4}));	// true
		System.out.println(jump.canJump(new int[]{3,2,1,0,4})); // false
	}
}



