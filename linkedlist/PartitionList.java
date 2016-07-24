package linkedlist;

import lib.Lib;

/**
 * 86. Partition List
 * 
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example,
 * 
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * 
 * @author guanhaipeng
 *
 */
public class PartitionList {
	
    public ListNode partition(ListNode head, int x) {
    	ListNode d1 = new ListNode(0), d2 = new ListNode(0);
    	ListNode p1 = d1, p2 = d2, p = head;
    	while (p != null) {
			if (p.val < x) {
				p1.next = p;
				p1 = p1.next;
			} else {
				p2.next = p;
				p2 = p2.next;
			}
			p = p.next;
		}
    	p2.next = null;
    	p1.next = d2.next;
    	return d1.next;
    }

	public static void main(String[] args) {
		int[] date = {1,4,3,2,5,2};
		Lib.shuffleArray(date);
		ListNode head = ListNode.toList(date);
		PartitionList partition = new PartitionList();
		for (int i = 0; i <= 5; i++) {
			head = partition.partition(head, i);
			System.out.println(head);
		}
	}

}
