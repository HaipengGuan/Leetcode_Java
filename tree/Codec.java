package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * 
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *     
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 * 
 * @see https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 */
public class Codec {
	
    // Encodes a tree to a single string.
    public static List<Integer> serialize(TreeNode root) {
    	List<Integer> values = new ArrayList<>();
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	int count = 0;
    	while (!queue.isEmpty()) {
    		TreeNode cur = queue.poll();
    		if (cur == null) {
    			count++;
    			values.add(null);
    		} else {
    			count = 0;
    			values.add(cur.val);
    			queue.add(cur.left);
    			queue.add(cur.right);
    		}
    	}
    	return values.subList(0, values.size() - count);
	}

    public static TreeNode deserialize(Integer[] values) {
    	if (values.length == 0) return null;
    	Queue<TreeNode> queue = new LinkedList<>();
    	int n = values.length, idx = -1;
    	TreeNode root = new TreeNode(values[++idx]);
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		TreeNode cur = queue.poll();
    		if (cur != null) {
    			cur.left = (++idx >= n || values[idx] == null) ? null : new TreeNode(values[idx]);
    			cur.right = (++idx >= n || values[idx] == null) ? null : new TreeNode(values[idx]);
    			queue.add(cur.left);
    			queue.add(cur.right);
    		}
    	}
    	return root;
	}
    
    // ------------------------
	public static void main(String[] args) {
		TreeNode root = Codec.deserialize(new Integer[]{1,2,3,null,null,4,5});
		System.out.println(Codec.serialize(root));
	}
    
}

class LeetcodeVersion {
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	StringBuffer buffer = new StringBuffer();
    	buildString(root, buffer);
    	return buffer.toString();
    }

    private void buildString(TreeNode root, StringBuffer buffer) {
		if (root == null) buffer.append("null,");
		else {
			buffer.append(root.val).append(',');
			buildString(root.left, buffer);
			buildString(root.right, buffer);
		}
	}
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	Deque<String> values = new LinkedList<>(Arrays.asList(data.split("\\s*,\\s*")));
    	return buildTree(values);
    }
    
    private TreeNode buildTree(Deque<String> values) {
    	if (values.isEmpty()) return null;
		String val = values.poll();
		if (val.equals("null")) return null;
		TreeNode node = new TreeNode(Integer.parseInt(val));
		node.left = buildTree(values);
		node.right = buildTree(values);
		return node;
	}
	
}
//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));
