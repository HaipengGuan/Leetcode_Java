package linkedlist;

/**
 * 21. Merge Two Sorted Lists
 * 
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * @see https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSorted {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode dummy = new ListNode(0);
    	ListNode p = dummy;
    	while (l1 != null && l2 != null) {
			if (l2.val < l1.val) {
				p.next = l2;
				l2 = l2.next;
			} else {
				p.next = l1;
				l1 = l1.next;
			}
			p = p.next;
		}
    	p = (l1 != null) ? l1 : l2;
    	return dummy.next;
    }
}
