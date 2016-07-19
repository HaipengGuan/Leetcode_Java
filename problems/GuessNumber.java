package problems;

public class GuessNumber {
	
	private int MY_ANS = 77;

	/**
	 * The guess API is defined in the parent class GuessGame.
	 * 
	 * @param num, your guess
	 * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
	 */
	private int guess(int num) {
		if (MY_ANS < num) return -1;
		if (num < MY_ANS) return 1;
		return 0;
	}

	/**
	 * 374. Guess Number Higher or Lower
	 * 
	 * We are playing the Guess Game. The game is as follows:
	 * I pick a number from 1 to n. You have to guess which number I picked.
	 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
	 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
	 * 
	 * -1 : My number is lower
	 * 1 : My number is higher
	 * 0 : Congrats! You got it!
	 * 
	 * Example:
	 * n = 10, I pick 6.
	 * Return 6.
	 * 
	 * @see https://leetcode.com/problems/guess-number-higher-or-lower/
	 */
    public int guessNumber(int n) {
		int low = 1, high = n;
		int mid = low + ((high - low) >>> 1);
		int res = guess(mid);
		while (res != 0) {
			if (res == 1) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
			mid = low + ((high - low) >>> 1);
			res = guess(mid);
		}
		return mid;
    }
    
    /**
	 * 375. Guess Number Higher or Lower II
	 * 
	 * We are playing the Guess Game. The game is as follows:
	 * - I pick a number from 1 to n. You have to guess which number I picked.
	 * - Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
	 * - However, when you guess a particular number x, and you guess wrong, you pay $x. 
	 * - You win the game when you guess the number I picked.
	 * 
	 * Example:
	 * n = 10, I pick 8.
	 * 
	 * First round: You guess 5, I tell you that it's higher. You pay $5.
	 * Second round: You guess 7, I tell you that it's higher. You pay $7.
	 * Third round: You guess 9, I tell you that it's lower. You pay $9.
	 * 
	 * Game over. 8 is the number I picked.
	 * You end up paying $5 + $7 + $9 = $21.
	 * 
	 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
	 * 
	 * @see https://leetcode.com/problems/guess-number-higher-or-lower-ii/
	 */
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
