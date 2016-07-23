package backtracking.n_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
	
	private final static int[] MultiplyDeBruijnBitPosition = {
		0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 
		31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9
	};

	private int FULL_LINE = 0;
	private int TOTAL = 0;
	
	/**
	 * 51. N-Queens
	 * 
	 * The n-queens puzzle is the problem of placing n queens on an n×n
	 * chessboard such that no two queens attack each other.
	 * 
	 * Given an integer n, return all distinct solutions to the n-queens puzzle.
	 * 
	 * Each solution contains a distinct board configuration of the n-queens'
	 * placement, where 'Q' and '.' both indicate a queen and an empty space
	 * respectively.
	 * 
	 * tag: backtracking
	 * 
	 * @see https://leetcode.com/problems/n-queens/
	 * 
	 */
    public List<List<String>> solveNQueens(int n) {
		FULL_LINE = (1 << n) - 1;
		List<List<String>> res = new ArrayList<>();
		char[][] cur = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cur[i], '.');
		}
		dfs1(0, 0, 0, 0, res, cur);
    	return res;
    }
    
    private void dfs1(int line, int right, int left, int row, List<List<String>> res, char[][] cur) {
		if (line != FULL_LINE) {
			int avaiPos = FULL_LINE & (~(line | right | left));
			while (avaiPos != 0) {
				int curPos = avaiPos & -avaiPos;
				int col = MultiplyDeBruijnBitPosition[(curPos * 0x077CB531) >>> 27];
				cur[row][col] = 'Q';
				avaiPos -= curPos;
				dfs1((line | curPos), (right | curPos) << 1, (left | curPos) >>> 1, row + 1, res, cur);
				cur[row][col] = '.';
			}
		} else {
			List<String> ans = new ArrayList<>();
			for (char[] row1 : cur) {
				ans.add(new String(row1));
			}
			res.add(ans);
		}
	}
    
    
    /**
	 * 52. N-Queens II
	 * 
	 * Follow up for N-Queens problem.
	 * 
	 * Now, instead outputting board configurations, return the total number of
	 * distinct solutions.
	 * 
	 * @see https://leetcode.com/problems/n-queens-ii/
	 */

	public int totalNQueens(int n) {
		FULL_LINE = (1 << n) - 1;
		TOTAL = 0;
		dfs2(0, 0, 0);
		return TOTAL;
	}

	private void dfs2(int line, int right, int left) {
		if (line != FULL_LINE) {
			int avaiPos = FULL_LINE & (~(line | left | right));
			while (avaiPos != 0) {
				int currPos = avaiPos & (~avaiPos + 1);
				avaiPos -= currPos;
				dfs2(line | currPos, (right | currPos) << 1, (left | currPos) >>> 1);
			}
		} else {
			TOTAL++;
		}
	}
    
    public static void main(String[] args) {
    	NQueens queens = new NQueens();
    	List<List<String>> res = queens.solveNQueens(4);
    	for (List<String> list : res) {
			for (String string : list) {
				System.out.println(string);
			}
			System.out.println("------");
		}
	}
}
