package linkedlist;

import lib.Lib;

/**
 * 147. Insertion Sort List
 * 
 * Sort a linked list using insertion sort.
 * 
 * @see https://leetcode.com/problems/insertion-sort-list/
 * 
 *
 */
public class InsertionSort {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode temp = dummy, p1 = head, p2;
        while (p1 != null) {
        	p2 = p1.next;
        	p1.next = null;
        	if (p1.val >= temp.val) {
    			temp = insert(temp, p1);
			} else {
				temp = insert(dummy, p1);
			}
        	p1 = p2;
		}
        return dummy.next;
    }
    
    private ListNode insert(ListNode dummy, ListNode node) {
    	ListNode p = dummy, temp;
    	while (p.next != null && p.next.val < node.val) {
			p = p.next;
		}
    	temp = p.next;
    	p.next = node;
    	node.next = temp;
    	return node;
	}
    
	public static void main(String[] args) {
		InsertionSort sort = new InsertionSort();
		int[] list = {1,2,3,4,5,6};
		ListNode head = null;
		for (int i = 0; i < 10; i++) {
			Lib.shuffleArray(list);
			head = ListNode.toList(list);
			head = sort.insertionSortList(head);
			System.out.println(head);
		}
	}
}
