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
	
	public static void mergeSort(int[] nums, int low, int high) { // low: inclusive; high: exclusive
		if (high - low <= 1) return;
		int mid = (low + high) / 2;
		mergeSort(nums, low, mid);
		mergeSort(nums, mid, high);
		int[] cahce = new int[high - low];
		int left = low, right = mid, ind = 0;
		while (left < mid) {
			while (right < high && nums[right] < nums[left]) cahce[ind++] = nums[right++];
			cahce[ind++] = nums[left++];
		}
		System.arraycopy(cahce, 0, nums, low, right - low);
	}

	public static void swap(int[] nums, int i, int j) {
		if (nums[i] != nums[j]) {
			nums[i] ^= nums[j];
			nums[j] ^= nums[i];
			nums[i] ^= nums[j];
		}
	}
	
}
