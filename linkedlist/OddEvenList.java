package linkedlist;

public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
    	if (head == null || head.next == null) return head;
		ListNode oddHead = head, evenHead = head.next;
		ListNode odd = oddHead, even = evenHead;
		while (even != null && even.next != null) {
			ListNode tmp = even.next;
			odd.next = tmp;
			even.next = tmp.next;
			odd = odd.next;
			even = even.next;
		}		
		odd.next = evenHead;
        return head;
    }
    
    public static void main(String[] args) {
		OddEvenList oddEvenList = new OddEvenList();
		int[] date = {1,3,5,7,9,2,4,6,8};
		ListNode head = ListNode.toList(date);
		oddEvenList.oddEvenList(head);
		System.out.println(head);
	}
}
