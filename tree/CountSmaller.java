package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSmaller {
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
    public List<Integer> countSmaller(int[] nums) {
    	int n = nums.length;
    	if (n == 0) return new ArrayList<>();
    	Integer[] count = new Integer[n]; 
    	Node root = null;
    	for (int i = n-1; i >= 0; i--) {
			root = update(root, i, 0, nums, count);
    	}
    	return Arrays.asList(count);
    	
    }

    class Node {
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
			root.left = update(root.left, ind, preSmall+root.count+root.smaller, nums, count);
		} else {
			root.count++;
			count[ind] = preSmall + root.smaller;
		}
		return root;
	}
    
    public static void main(String[] args) {
		CountSmaller cs = new CountSmaller();
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
