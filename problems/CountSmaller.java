package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * 
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * Given nums = [5, 2, 6, 1]
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 * 
 * @see https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public abstract class CountSmaller {

    public abstract List<Integer> countSmaller(int[] nums);
    
    public static void main(String[] args) {
		CountSmaller cs = new MergeSortSolution2();
		System.out.println(cs.countSmaller(new int[] {}));
		System.out.println(cs.countSmaller(new int[] {1}));
		System.out.println(cs.countSmaller(new int[] {1,1}));
		System.out.println(cs.countSmaller(new int[] {1,2}));
		System.out.println(cs.countSmaller(new int[] {2,1}));
		System.out.println(cs.countSmaller(new int[] {1,2,3,4}));
		System.out.println(cs.countSmaller(new int[] {4,3,2,1}));
		System.out.println(cs.countSmaller(new int[] {5,2,6,1}));
		System.out.println(cs.countSmaller(new int[] {1,0,0}));
		System.out.println(cs.countSmaller(new int[] {0,0,1}));
		System.out.println(cs.countSmaller(new int[] {2,0,1}));
		System.out.println(cs.countSmaller(new int[] {3,0,2}));
		System.out.println(cs.countSmaller(new int[] {1,0,2}));
		System.out.println(cs.countSmaller(new int[] {3,2,4,3,3,2,0,1,3,2}));
		/**
			[]
			[0]
			[0, 0]
			[0, 0]
			[1, 0]
			[0, 0, 0, 0]
			[3, 2, 1, 0]
			[2, 1, 1, 0]
			[2, 0, 0]
			[0, 0, 0]
			[2, 0, 0]
			[2, 0, 0]
			[1, 0, 0]
			[5, 2, 7, 4, 4, 2, 0, 0, 1, 0]
		 */
    	
	}
}

// --------------------------------------------------

class BSTSolution extends CountSmaller {

	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		if (n == 0)
			return new ArrayList<>();
		Integer[] count = new Integer[n];
		Node root = null;
		for (int i = n - 1; i >= 0; i--) {
			root = update(root, i, 0, nums, count);
		}
		return Arrays.asList(count);
	}

	private class Node {
		public int val, smaller, count;
		public Node left, right;

		public Node(int value) {
			this.val = value;
			this.smaller = 0;
			count = 1;
			left = right = null;
		}
	}

	private Node update(Node root, int ind, int preSmall, int[] nums, Integer[] count) {
		if (root == null) {
			count[ind] = preSmall;
			root = new Node(nums[ind]);
		} else if (nums[ind] < root.val) {
			root.smaller++;
			root.right = update(root.right, ind, preSmall, nums, count);
		} else if (nums[ind] > root.val) {
			root.left = update(root.left, ind, preSmall + root.count + root.smaller, nums, count);
		} else {
			root.count++;
			count[ind] = preSmall + root.smaller;
		}
		return root;
	}
}

// --------------------------------------------------

class FenwickTreeSolution extends CountSmaller {

	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		// 
		int[] tmp = nums.clone();
		Arrays.sort(tmp);
		for (int i = 0; i < n; i++) {
			nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
		}
		Integer[] count = new Integer[n];
		int[] tree = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			count[i] = query(tree, nums[i] - 1);
			plus(tree, nums[i], 1);
		}
		return Arrays.asList(count);
	}

	private void plus(int[] tree, int ind, int delta) {
		for (int i = ind; i < tree.length; i += i & (~i + 1)) {
			tree[i] += delta;
		}
	}

	private int query(int[] tree, int ind) {
		int sum = 0;
		for (int i = ind; i > 0; i -= i & (~i + 1)) {
			sum += tree[i];
		}
		return sum;
	}
}

// --------------------------------------------------

class MergeSortSolution extends CountSmaller {

	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		Integer[] count = new Integer[n];
		if (n > 0) {
			Pair[] pairs = new Pair[n];
			for (int i = 0; i < n; i++) {
				pairs[i] = new Pair(nums[i], i);
			}
			mergeSort(pairs, 0, n - 1, count);
		}
		return Arrays.asList(count);
	}

	private void mergeSort(Pair[] pairs, int low, int high, Integer[] count) {
		if (low >= high) {
			count[high] = 0; // init
			return;
		}
		int mid = (low + high) / 2;
		mergeSort(pairs, low, mid, count);
		mergeSort(pairs, mid + 1, high, count);
		Pair[] tmp = new Pair[high - low + 1];
		int ind = 0, left = low, right = mid + 1, moveCount = 0;
		while (left <= mid || right <= high) {
			if (left > mid) { // move right
				tmp[ind++] = pairs[right++];
			} else if (right > high) { // move left
				count[pairs[left].ind] += moveCount;
				tmp[ind++] = pairs[left++];
			} else if (pairs[right].val < pairs[left].val) { // move right
				moveCount++;
				tmp[ind++] = pairs[right++];
			} else { // move left
				count[pairs[left].ind] += moveCount;
				tmp[ind++] = pairs[left++];
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			pairs[i + low] = tmp[i];
		}
	}

	private class Pair {
		public final int val, ind;

		public Pair(int val, int ind) {
			this.ind = ind;
			this.val = val;
		}
	}
}

//--------------------------------------------------

class MergeSortSolution2 extends CountSmaller {

	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		int[] indexes = new int[n];
		Integer[] count = new Integer[n];
		if (n > 0) {
			mergeSort(nums, indexes, 0, n-1, count);
		}
		return Arrays.asList(count);
	}
	
	private void mergeSort(int[] nums, int[] indexes, int low, int high, Integer[] count) {
		if (low >= high) {
			count[high] = 0; 
			indexes[high] = high;
			return;
		}
		int mid = (low + high) / 2;
		mergeSort(nums, indexes, low, mid, count);
		mergeSort(nums, indexes, mid+1, high, count);
		int[] tmpIndex = new int[high - low + 1];
		int left = low, right = mid+1, ind = 0, moveCount = 0;
		while (left <= mid || right <= high) {
			if (left > mid) { // move right
				tmpIndex[ind++] = indexes[right++];
			} else if (right > high) { // move left
				count[indexes[left]] += moveCount;
				tmpIndex[ind++] = indexes[left++];
			} else if (nums[indexes[right]] < nums[indexes[left]]) { // move right
				moveCount++;
				tmpIndex[ind++] = indexes[right++];	
			} else { // move left
				count[indexes[left]] += moveCount;
				tmpIndex[ind++] = indexes[left++];
			}
		}
		for (int i = 0; i < tmpIndex.length; i++) {
			indexes[low+i] = tmpIndex[i];
		}
	}
	
}

