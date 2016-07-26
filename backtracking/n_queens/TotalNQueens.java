package backtracking.n_queens;

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
public class TotalNQueens {
	
	private static int FULL_LINE;
	private static int TOTAL;
	
	public int totalNQueens(int n) {
		FULL_LINE = (1 << n) - 1;
		TOTAL = 0;
		dfs(0, 0, 0);
		return TOTAL;
	}

	private void dfs(int line, int right, int left) {
		if (line != FULL_LINE) {
			int avaiPos = FULL_LINE & (~(line | left | right));
			while (avaiPos != 0) {
				int currPos = avaiPos & (~avaiPos + 1);
				avaiPos -= currPos;
				dfs(line | currPos, (right | currPos) << 1, (left | currPos) >>> 1);
			}
		} else {
			TOTAL++;
		}
	}
	
	public static void main(String[] args) {
		TotalNQueens nQueens = new TotalNQueens();
		System.out.println(nQueens.totalNQueens(8));
	}
}
