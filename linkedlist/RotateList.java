package linkedlist;

/**
 * 61. Rotate List
 * 
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * For example:
 * 
 * Given 1->2->3->4->5->NULL and k = 2,
 * 
 * return 4->5->1->2->3->NULL.
 * 
 * @see https://leetcode.com/problems/rotate-list/
 *
 */
public class RotateList {
	
    public ListNode rotateRight(ListNode head, int k) {
    	if (head == null || head.next == null || k == 0) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode pointer = dummy, newTail = dummy;
    	for (int i = 0; i < k; i++) {
			if (pointer.next != null) {
				pointer = pointer.next;
			} else {
				return rotateRight(head, k % i);
			}
		}
    	while (pointer.next != null) {
			pointer = pointer.next;
			newTail = newTail.next;
		}
    	ListNode newHead = newTail.next;
    	newTail.next = null;
    	pointer.next = dummy.next;
    	dummy.next = newHead;
    	return dummy.next;
    }
    
    public static void main(String[] args) {
		RotateList rotate = new RotateList();
		int[] list = {1,2,3,4,5,6};
		for (int i = 0; i <= 10; i++) {
			ListNode head = ListNode.toList(list);
			head = rotate.rotateRight(head, i);
			System.out.println(head);
		}
	}
}
