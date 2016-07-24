package array.sort;

/**
 * Normal merge sort
 */
public class MergeSort {
	
	public void mergeSort(int[] nums, int low, int high) {
		if (high - low <= 1) return;
		int mid = low + ((high - low) >>> 1);
		mergeSort(nums, low, mid);
		mergeSort(nums, mid, high);
		int[] cache = new int[high - low];
		int left = low, right = mid, ind = 0;
		while (left < mid) {
			while (right < high && nums[right] < nums[left]) cache[ind++] = nums[right++];
			cache[ind++] = cache[left++];
		}
		System.arraycopy(cache, 0, nums, low, right - low);
	}
}
