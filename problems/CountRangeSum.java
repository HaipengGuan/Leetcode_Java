package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 327. Count of Range Sum
 * 
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive. 
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 * 
 * Note: A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * 
 * Example: Given nums = [-2, 5, -1], lower = -2, upper = 2, 
 * Return 3.
 * The three ranges are : 
 * [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 * 
 * @see https://leetcode.com/problems/count-of-range-sum/
 *
 */
public abstract class CountRangeSum {
	
	public abstract int countRangeSum(int[] nums, int lower, int upper);
	
	public boolean vertify(int[] nums, int lower, int upper) {
		int count1 = countRangeSum(nums, lower, upper);
		int sum = 0, count2 = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[i];
				if (lower <= sum && sum <= upper) count2++;
			}
		}
		return count1 == count2;
	}
	
	public static void main(String[] args) {
		CountRangeSum rangeSum = new CountRangeSumMergeSort();
		System.out.println(rangeSum.countRangeSum(new int[] {-1, 1}, 0, 0)); // 1
		System.out.println(rangeSum.countRangeSum(new int[] {-2, 5, -1}, -2, 2)); // 3
		System.out.println(rangeSum.countRangeSum(new int[] {-2147483647,0,-2147483647,2147483647}, -564, 3864)); // 3
	}
}


class CountRangeSumFenwick extends CountRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length, totalCount = 0;
		long[] sums = new long[n + 1];
		List<Long> candidate = new ArrayList<>(3 * n);
		candidate.addAll(Arrays.asList((long) 0, (long) lower, (long) upper));
		for (int i = 0; i < n; i++) {
			sums[i + 1] = sums[i] + nums[i];
			candidate.add(sums[i + 1]);
			candidate.add(sums[i + 1] + lower);
			candidate.add(sums[i + 1] + upper);
		}
		Collections.sort(candidate);
		int[] bit = new int[candidate.size() + 1];
		for (long sum : sums) {
			int ind = Collections.binarySearch(candidate, sum);
			plus(bit, ind, 1);
		}
		for (long sum : sums) {
			int ind = Collections.binarySearch(candidate, sum);
			plus(bit, ind, -1);
			int lowerInd = Collections.binarySearch(candidate, sum + lower);
			int upperInd = Collections.binarySearch(candidate, sum + upper);
			totalCount += rangeSum(bit, lowerInd, upperInd);
		}
		return totalCount;
	}

	private void plus(int[] bit, int ind, int delta) {
		for (int i = ind + 1; i < bit.length; i += i & (~i + 1)) {
			bit[i] += delta;
		}
	}

	private int sum(int[] bit, int ind) {
		int ans = 0;
		for (int i = ind + 1; i > 0; i -= i & (~i + 1)) {
			ans += bit[i];
		}
		return ans;
	}

	private int rangeSum(int[] bit, int i, int j) {
		return sum(bit, j) - sum(bit, i - 1);
	}
}

// ----------------------------------------------------
class CountRangeSumFenwick2 extends CountRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length, totalCount = 0;
		long[] sums = new long[n + 1];
		HashSet<Long> set = new HashSet<>();
		set.addAll(Arrays.asList((long) 0, (long) lower, (long) upper));
		for (int i = 0; i < n; i++) {
			sums[i + 1] = sums[i] + nums[i];
			set.add(sums[i + 1]);
			set.add(sums[i + 1] + lower);
			set.add(sums[i + 1] + upper);
		}
		Long[] candidate = set.toArray(new Long[set.size()]);
		Arrays.sort(candidate);
		HashMap<Long, Integer> map = new HashMap<>();
		for (int i = 0; i < candidate.length; i++) {
			map.put(candidate[i], i);
		}
		int[] bit = new int[map.size() + 1];
		for (long sum : sums) {
			plus(bit, map.get(sum), 1);
		}
		for (long sum : sums) {
			plus(bit, map.get(sum), -1);
			totalCount += rangeSum(bit, map.get(sum + lower), map.get(sum + upper));
		}

		return totalCount;
	}

	private void plus(int[] bit, int ind, int delta) {
		for (int i = ind + 1; i < bit.length; i += i & (~i + 1)) {
			bit[i] += delta;
		}
	}

	private int sum(int[] bit, int ind) {
		int ans = 0;
		for (int i = ind + 1; i > 0; i -= i & (~i + 1)) {
			ans += bit[i];
		}
		return ans;
	}

	private int rangeSum(int[] bit, int i, int j) {
		return sum(bit, j) - sum(bit, i - 1);
	}
}

// ----------------------------------------------------

class CountRangeSumMergeSort extends CountRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] sums = new long[n+1];
		for (int i = 0; i < n; i++) {
			sums[i+1] = sums[i] + nums[i];
		}
		return countAndMergeSort(sums, 0, n+1, lower, upper);
	}
	
	private int countAndMergeSort(long[] sums, int low, int high, int lower, int upper) {
		if (high - low <= 1) return 0;
		int mid = (low + high) / 2, curCount = 0;
		curCount += countAndMergeSort(sums, low, mid, lower, upper);
		curCount += countAndMergeSort(sums, mid, high, lower, upper);
		long[] cache = new long[high - low];
		int left = low, right = mid, ind = 0;
		int lowerInd = mid, upperInd = mid;
		while (left < mid) {
			while (lowerInd < high && sums[lowerInd] - sums[left] < lower) lowerInd++;
			while (upperInd < high && sums[upperInd] - sums[left] <= upper) upperInd++;
			while (right < high && sums[right] < sums[left]) cache[ind++] = sums[right++];
			cache[ind++] = sums[left++];
			curCount += upperInd - lowerInd;
		}
		System.arraycopy(cache, 0, sums, low, right - low);
		return curCount;
	}
}