package design;

/**
 * 208. Implement Trie (Prefix Tree)
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 * @see https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 */
public class Trie {
	
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	TrieNode p = root;
    	int n = word.length(), ind = 0;
    	for (int i = 0; i < n; i++) {
			ind = word.charAt(i) - 'a';
			if (p.children[ind] == null) p.children[ind] = new TrieNode();
			p = p.children[ind];
		}
    	p.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	TrieNode p = root;
    	int n = word.length(), ind = 0;
    	for (int i = 0; i < n; i++) {
			ind = word.charAt(i) - 'a';
			if (p.children[ind] == null) return false;
			p = p.children[ind];
		}
        return p.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	TrieNode p = root;
    	int n = prefix.length(), ind = 0;
    	for (int i = 0; i < n; i++) {
			ind = prefix.charAt(i) - 'a';
			if (p.children[ind] == null) return false;
			p = p.children[ind];
    	}
    	return true;
    }
}


class TrieNode {
	
	public TrieNode[] children = null;
	public boolean isLeaf = false;
	
    public TrieNode() {
    	children = new TrieNode[26];
    }
}