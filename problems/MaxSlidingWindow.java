package problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 239. Sliding Window Maximum
 * 
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * 
 * Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for
 * non-empty array.
 * 
 * Follow up: Could you solve it in linear time?
 * 
 * @see https://leetcode.com/problems/sliding-window-maximum/
 *
 */
public class MaxSlidingWindow {

	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0) return nums;
		int n = nums.length;
		int[] res = new int[n - k + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!deque.isEmpty() && deque.peek() < i - k + 1) deque.pollFirst();
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
			deque.offer(i);
			if (i - k + 1 >= 0)  res[i-k+1] = nums[deque.peek()];
		}
		return res;
	}
    
    
    // ----------------------------
    private static void print(int[] nums) {
		System.out.println(Arrays.toString(nums));
	}
    
    public static void main(String[] args) {
		MaxSlidingWindow window = new OnkApproach();
		print(window.maxSlidingWindow(new int[] {}, 0));
		print(window.maxSlidingWindow(new int[] {7,2,4}, 2));
		print(window.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 1));
		print(window.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3));
		print(window.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 7));
		print(window.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 8));
		print(window.maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3)); // [3,3,2,5]
		
	}
}

// ----------------------------
// O(klogn)
class HeapApproach extends MaxSlidingWindow {
	
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0) return nums;
		int n = nums.length;
		Node[] nodes = new Node[n];
		int[] res = new int[n - k + 1];
		Node root = buildHeap(nodes, nums, 0, n-1);
		for (int i = 0; i < n - k + 1; i++) {
			res[i] = lowestCommonAncestor(root, nodes[i], nodes[i+k-1]).val;
		}
		return res;
	}
	
	private Node lowestCommonAncestor(Node root, Node p, Node q) {
		if (root == null || root == p || root == q) return root;
		Node left = lowestCommonAncestor(root.left, p, q);
		Node right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) 
			return root;
		else
			return (left != null) ? left : right;
	}
	
	private Node buildHeap(Node[] nodes, int[] nums, int low, int high) {
		if (low > high) return null;
		if (low == high) {
			nodes[low] = new Node(nums[low]);
			return nodes[low];
		}
		int maxInd = low;
		for (int i = low+1; i <= high; i++) {
			if (nums[i] > nums[maxInd]) maxInd = i;
		}
		nodes[maxInd] = new Node(nums[maxInd]);
		nodes[maxInd].left = buildHeap(nodes, nums, low, maxInd-1);
		nodes[maxInd].right = buildHeap(nodes, nums, maxInd+1, high);
		return nodes[maxInd];
	}
	
	
	private class Node {
		int val;
		Node left = null, right = null;
		public Node(int val) {
			this.val = val;
		}
	}
}

// ----------------------------
// best O(n), worst O(nk)
class OnkApproach extends MaxSlidingWindow {
	@Override
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length == 0) return nums;
		int n = nums.length;
		int[] res = new int[n - k + 1];
		for (int i = 0, maxInd = -1; i <= n - k; i++) {
			if (i > 0 && nums[i+k-1] > nums[maxInd]) maxInd = i+k-1;
			if (maxInd < i) maxInd = findMaxInd(nums, i, i+k-1);
			res[i] = nums[maxInd];
		}
		return res;
	}
	
	private int findMaxInd(int[] nums, int low, int high) {
		int maxInd = low;
		for (int i = low+1; i <= high; i++) {
			if (nums[i] > nums[maxInd]) maxInd = i;
		}
		return maxInd;
	}
}