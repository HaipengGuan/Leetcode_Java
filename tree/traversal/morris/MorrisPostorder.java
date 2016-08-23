package tree.traversal.morris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tree.TreeNode;
import tree.traversal.PostorderTraversal;

public class MorrisPostorder extends PostorderTraversal {

	@Override
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> visited = new ArrayList<>();
		TreeNode cur = root, ptr = null;
		while (cur != null) {
			if (cur.right == null) {
				visited.add(cur.val);
				cur = cur.left;
			} else {
				ptr = cur.right;
				while (ptr.left != null && ptr.left != cur) {
					ptr = ptr.left;
				}
				if (ptr.left == null) {
					ptr.left = cur;
					visited.add(cur.val);
					cur = cur.right;
				} else {
					ptr.left = null;
					cur = cur.left;
				}
			}
		}
		Collections.reverse(visited);
		return visited;
	}
	
}

class MorrisPostorderII extends PostorderTraversal {
	
	@Override
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> visited = new ArrayList<>();
		TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
		dummy.left = root;
		TreeNode cur = dummy, ptr = null;
		while (cur != null) {
			if (cur.left == null) {
				cur = cur.right;
			} else {
				ptr = cur.left;
				while (ptr.right != null && ptr.right != cur) {
					ptr = ptr.right;
				}
				if (ptr.right == null) {
					ptr.right = cur;
					cur = cur.left;
				} else {
					ptr.right = null;
					revVisit(cur.left, ptr, visited);
					cur = cur.right;
				}
			}
		}
		return visited;
	}
	
	private void revVisit(TreeNode from, TreeNode to, List<Integer> visited) {
		reverse(from, to);
		for (TreeNode ptr = to; ptr != from.right; ptr = ptr.right){
			visited.add(ptr.val);
		}
		reverse(to, from);
	}
	
	private void reverse(TreeNode from, TreeNode to) {
		if (from == to) return;
		TreeNode ptr0 = null, ptr1 = from, ptr2;
		while (ptr0 != to) {
			ptr2 = ptr1.right;
			ptr1.right = ptr0;
			ptr0 = ptr1;
			ptr1 = ptr2;
		}
	}
	
}

