package backtracking.word_search;

/**
 * 79. Word Search
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * @see https://leetcode.com/problems/word-search/
 * 
 */
public class WordExist {

	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs(board, i, j, word, 0)) return true;
			}
		}
		return false;
	}
	
	private boolean dfs(char[][] board, int i, int j, String word, int ind) {
		char c = board[i][j];
		if (c == '#' || c != word.charAt(ind)) return false;
		if (ind == word.length() - 1) return true;
		board[i][j] = '#';
		boolean found = false;
		if (!found && i > 0) found = dfs(board, i-1, j, word, ind+1); 
		if (!found && j > 0) found = dfs(board, i, j-1, word, ind+1); 
		if (!found && i < board.length-1) found = dfs(board, i+1, j, word, ind+1); 
		if (!found && j < board[0].length-1) found = dfs(board, i, j+1, word, ind+1); 
		board[i][j] = c;
		return found;
	}
    
	public static void main(String[] args) {
		char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
			};
		WordExist exist = new WordExist();
		System.out.println(exist.exist(board, "ESCCE"));
		System.out.println(exist.exist(board, "ABCCED"));
	}
}

