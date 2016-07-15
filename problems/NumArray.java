package problems;


public abstract class NumArray {
	
	public abstract int sumRange(int i, int j);
	
}

/**
 * 303. Range Sum Query - Immutable
 * 
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1] sumRange(0, 2) -> 1 sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 
 * Note: You may assume that the array does not change. There are many calls to
 * sumRange function.
 * 
 * @see https://leetcode.com/problems/range-sum-query-immutable/
 *
 */
class Immutable extends NumArray{

	private int[] sum = null;

	public Immutable(int[] nums) {
		int n = nums.length;
		sum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
	}

	public int sumRange(int i, int j) {
		return sum[+1] - sum[i];
	}
}


/**
 * 307. Range Sum Query - Mutable
 * 
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example:
 * 
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * Note:
 * 
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is
 * distributed evenly.
 * 
 * @see https://leetcode.com/problems/range-sum-query-mutable/
 */
class FenwickTree extends NumArray {

	private int[] tree = null;
	private int[] value = null;

	public FenwickTree(int[] nums) {
		int n = nums.length;
		tree = new int[n + 1];
		value = new int[n];
		for (int i = 0; i < n; i++) {
			update(i, nums[i]);
		}
	}

	public void update(int i, int val) {
		int delta = val - value[i], n = tree.length;
		value[i] = val;
		for (int j = i + 1; j < n; j += lowerbit(j)) {
			tree[j] += delta;
		}
	}

	public int sumRange(int i, int j) {
		return sum(j) - sum(i - 1);
	}

	public int sum(int i) {
		int res = 0;
		for (int j = i + 1; j != 0; j -= lowerbit(j)) {
			res += tree[j];
		}
		return res;
	}

	private int lowerbit(int n) {
		return n & (~n + 1);
	}

}
