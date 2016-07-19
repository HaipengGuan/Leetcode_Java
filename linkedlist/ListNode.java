package linkedlist;

import java.util.ArrayList;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
	
	@Override
	public String toString() {
		ArrayList<Integer> list = new ArrayList<>();
		ListNode pointer = this;
		while (pointer != null) {
			list.add(pointer.val);
			pointer = pointer.next;
		}
		return list.toString();
	}
	
	public static ListNode toList(int[] nums) {
		if (nums.length == 0) return null;
		ListNode head = new ListNode(nums[0]);
		ListNode pointer = head;
		for (int i = 1; i < nums.length; i++) {
			pointer.next = new ListNode(nums[i]);
			pointer = pointer.next;
		}
		return head;
	}
}