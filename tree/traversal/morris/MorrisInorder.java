package tree.traversal.morris;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;
import tree.traversal.InorderTraversal;

public class MorrisInorder extends InorderTraversal {

	@Override
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> visited = new ArrayList<>();
    	TreeNode cur = root, ptr = null;
    	while (cur != null) {
    		if (cur.left == null) {
    			visited.add(cur.val);
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
    				visited.add(cur.val);
    				cur = cur.right;
    			}
    		}
    	}
    	return visited;
    }
    
}
