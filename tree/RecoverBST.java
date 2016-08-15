package tree;

/**
 * 99. Recover Binary Search Tree
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a
 * constant space solution?
 * 
 * @see https://leetcode.com/problems/recover-binary-search-tree/
 *
 */
public class RecoverBST {
	
	private TreeNode a = null, b = null, prev = new TreeNode(Integer.MIN_VALUE);
	
	public void recoverTree(TreeNode root) {
		morrisInorder(root);
		final int val = a.val;
		a.val = b.val;
		b.val = val;
	}
	
	private void morrisInorder(TreeNode root) {
		TreeNode cur = root, ptr = null;
		while (cur != null) {
			if (cur.left == null) {
				visit(cur);
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
					visit(cur);
					cur = cur.right;
				}
			}
		}
	}
	
	private void visit(TreeNode root) {
		if (a == null && prev.val >= root.val)
			a = prev;
		if (a != null && prev.val >= root.val)
			b = root;
		prev = root;
	}

}

class RecursionInorder extends RecoverBST {
	
	private static TreeNode prev = null, a = null, b = null;
	
	public void recoverTree(TreeNode root) {
		prev = new TreeNode(Integer.MIN_VALUE);
		inorderTraversal(root);
		int val = a.val;
		a.val = b.val;
		b.val = val;
	}

	private void inorderTraversal(TreeNode root) {
		if (root == null) return;
		inorderTraversal(root.left);
		if (a == null && prev.val >= root.val)
			a = prev;
		if (a != null && prev.val >= root.val)
			b = root;
		prev = root;
		inorderTraversal(root.right);
	}

}