package linkedlist;

import java.util.HashMap;
import java.util.Map;

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x) { this.label = x; }
}

/**
 * 138. Copy List with Random Pointer
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * @see https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 */
public class CopyRandomList {

	public RandomListNode copyRandomList(RandomListNode head) {
	    if (head == null) return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode p1 = head, p2 = null;
		while (p1 != null) {
			map.put(p1, new RandomListNode(p1.label));
			p1 = p1.next;
		}
		for (p1 = head; p1 != null; p1 = p1.next) {
			p2 = map.get(p1);
			p2.next = map.get(p1.next);
			p2.random = map.get(p1.random);
		}
		return map.get(head);
	}
}
/**
 * loop 1: 	create new node p2 and make p1.next = p2
 * loop 2: 	p1.next.random = p1.random.next;
 * loop 3:	rebuild p1 and p2 
 * 
 * @see https://discuss.leetcode.com/topic/7594/a-solution-with-constant-space-complexity-o-1-and-linear-time-complexity-o-n/5
 * @see https://discuss.leetcode.com/topic/5831/2-clean-c-algorithms-without-using-extra-array-hash-table-algorithms-are-explained-step-by-step
 */
class NoHashmapSolution extends CopyRandomList {
	@Override
	public RandomListNode copyRandomList(RandomListNode head) {
	    if (head == null) return null;
		RandomListNode head2, p1, p2;
		for (p1 = head; p1 != null; p1 = p1.next.next) {
			p2 = new RandomListNode(p1.label);
			p2.next = p1.next;
			p1.next = p2;
		}
		head2 = head.next;
		for (p1 = head; p1 != null; p1 = p1.next.next) {
			if (p1.random != null) p1.next.random = p1.random.next;
		}
		for (p1 = head; p1 != null; p1 = p1.next) {
			p2 = p1.next;
			p1.next = p2.next;
			if (p2.next != null) p2.next = p2.next.next;
		}
		return head2;
	}
}
