package lib;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Lib {
	
	public static void shuffleArray(int[] nums) {
		Random random = ThreadLocalRandom.current();
		int n = nums.length, index = 0;
		for (int i = n-1; i > 0; i--) {
			index = random.nextInt(i+1);
			swap(nums, index, i);
		}
	}
	
	public static void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}
	
}
