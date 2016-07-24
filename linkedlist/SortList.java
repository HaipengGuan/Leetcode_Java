package linkedlist;

import lib.Lib;

/**
 * 148. Sort List
 * 
 * Sort a linked list in O(n log n) time using constant space complexity.
 *  
 * @see https://leetcode.com/problems/sort-list/
 */
public class SortList {

    private static ListNode dummy = new ListNode(0);
    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head, p2 = head;
        while (p2.next != null && p2.next.next != null) {
			p2 = p2.next.next;
			p1 = p1.next;
		}
        ListNode head2 = p1.next;
        p1.next = null;
        return merge(sortList(head), sortList(head2));
    }
    
    
    private ListNode merge(ListNode l1, ListNode l2) {
    	ListNode p = dummy, p1 = l1, p2 = l2;
    	while (p1 != null && p2 != null) {
    		if (p2.val < p1.val) {
				p.next = p2;
				p2 = p2.next;
			} else {
				p.next = p1;
				p1 = p1.next;
			}
    		p = p.next;
		}
    	p.next = (p1 != null) ? p1 : p2;
    	return dummy.next;
	}
	
	public static void main(String[] args) {
		SortList sort = new SortList();
		int[] list = {1,2,3,4,5,6,7,8};
		ListNode head = null;
		for (int i = 0; i < 10; i++) {
			Lib.shuffleArray(list);
			head = ListNode.toList(list);
			head = sort.sortList(head);
			System.out.println(head);
		}
	}

}
