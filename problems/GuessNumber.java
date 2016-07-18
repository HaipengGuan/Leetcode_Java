package problems;

public class GuessNumber {

	public int getMoneyAmount(int n) {
		int[][] dp = new int[n + 1][n + 1];
		return getMoney(1, n, dp);
	}

	private int getMoney(int start, int end, int[][] dp) {
		if (start >= end)
			return 0;
		if (dp[start][end] == 0) {
			dp[start][end] = Integer.MAX_VALUE;
			for (int i = start; i <= end; i++) {
				dp[start][end] = Math.min(dp[start][end],
						i + Math.max(getMoney(start, i - 1, dp), getMoney(i + 1, end, dp)));
			}
		}
		return dp[start][end];
	}

	public static void main(String[] args) {
		GuessNumber guess = new GuessNumber();
		System.out.println(guess.getMoneyAmount(20));
	}
}
