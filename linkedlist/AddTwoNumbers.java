package linkedlist;

/**
 * 2. Add Two Numbers
 * 
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * @see https://leetcode.com/problems/add-two-numbers/
 *
 */
public class AddTwoNumbers {
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return (l1 != null) ? l1 : l2;
        ListNode dummy = new ListNode(0), p = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
        	p.next = new ListNode(carry);
        	p = p.next;
        	if (l1 != null) {
        		p.val += l1.val;
        		l1 = l1.next;
        	}
        	if (l2 != null) {
        		p.val += l2.val;
        		l2 = l2.next;
        	}
        	carry = p.val / 10;
        	p.val %= 10;
        }
        return dummy.next;
    }
    
    // --------------------------------
    private static AddTwoNumbers add = new AddTwoNumbers();
    
    public static void main(String[] args) {
		ListNode l1 = ListNode.toList(new int[]{});
		ListNode l2 = ListNode.toList(new int[]{5,6,4});
		System.out.println(add.addTwoNumbers(l1, l2));
	}
}
