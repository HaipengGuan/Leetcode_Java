package backtracking.word_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * 
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * @see https://leetcode.com/problems/word-search-ii/
 *
 */
public class FindWords {
	
	private static int N, M;
	
    public List<String> findWords(char[][] board, String[] words) {
    	List<String> res = new ArrayList<>();
    	N = board.length; M = board[0].length; 
    	TrieNode root = buildTree(words);
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(board, i, j, root, res);
			}
		}
    	return res;
    }
    
    private TrieNode buildTree(String[] words) {
    	TrieNode root = new TrieNode();
    	TrieNode p = root;
    	for (String word : words) {
        	p = root;
        	for (char c : word.toCharArray()) {
				int i = c - 'a';
				if (p.next[i] == null) p.next[i] = new TrieNode();
				p = p.next[i];
			}
        	p.word = word;
		}
    	return root;
	}
    
    private void dfs(char[][] board, int i, int j, TrieNode root, List<String> res) {
		char c = board[i][j];
		if (c == '#' || root.next[c - 'a'] == null) return;
		root = root.next[c - 'a'];
		if (root.word != null) {
			res.add(root.word);
			root.word = null;
		}
		board[i][j] = '#';
		if (i > 0) dfs(board, i - 1, j, root, res);
		if (j > 0) dfs(board, i, j - 1, root, res);
		if (i < N - 1) dfs(board, i + 1, j, root, res);
		if (j < M - 1) dfs(board, i, j + 1, root, res);
		board[i][j] = c;
	}

    // ----------
    private static char[][] toBoard(String[] words) {
		char[][] res = new char[words.length][words[0].length()];
		for (int i = 0; i < words.length; i++) {
			res[i] = words[i].toCharArray();
		}
		return res;
	}
    
    public static void main(String[] args) {
		String[] b = {
				"oaan",
				"etae",
				"ihkr",
				"iflv"
			};
		char[][] board = toBoard(b);
		String[] words = {
				"oath",
				"pea",
				"eat",
				"rain",
				"etao"
			};
		FindWords find = new FindWords();
		System.out.println(find.findWords(board, words));
	}
}

class TrieNode {
	TrieNode[] next = new TrieNode[26];
	String word = null;
}