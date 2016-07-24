package linkedlist;

/**
 * 143. Reorder List
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, 
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * @see https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
	
	private static ListNode dummy = new ListNode(0);
	
    public void reorderList(ListNode head) {
    	if (head == null || head.next == null) return;
    	ListNode head1 = head, head2 = head.next;
    	ListNode p1 = head, p2 = head;
    	while (p2.next != null && p2.next.next != null) {
			p2 = p2.next.next;
			p1 = p1.next;
		}
    	head2 = p1.next;
    	p1.next = null;
    	head2 = reverse(head2);
    	head = intertwine(head1, head2);
    }
	
    private ListNode reverse(ListNode head) {
		ListNode p1 = null, p2 = head, p3;
		while (p2 != null) {
			p3 = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = p3;
		}
		return p1;
	}
    
    private ListNode intertwine(ListNode l1, ListNode l2) {
		ListNode p = dummy;
		dummy.next = null;
		while (l1 != null && l2 != null) {
			p.next = l1;
			l1 = l1.next;
			p = p.next;
			p.next = l2;
			l2 = l2.next;
			p = p.next;
		}
		p.next = (l1 != null) ? l1 : l2;
		return dummy.next;
	}
    
	public static void main(String[] args) {
		int[] data = {1,2,3};
		ListNode head = ListNode.toList(data);
		ReorderList reorder = new ReorderList();
		reorder.reorderList(head);
		System.out.println(head);
	}
}
