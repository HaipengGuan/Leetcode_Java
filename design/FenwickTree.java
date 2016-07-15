package design;

public class FenwickTree {

	int[] bit = null;

	public FenwickTree(int capacity) {
		bit = new int[capacity + 1];
	}

	public void plus(int ind, int delta) {
		for (int i = ind + 1; i < bit.length; i += lowerbit(i)) {
			bit[i] += delta;
		}
	}

	public int sum(int ind) {
		int ans = 0;
		for (int i = ind + 1; i > 0; i -= lowerbit(i)) {
			ans += bit[i];
		}
		return ans;
	}

	public int sumRange(int i, int j) {
		return sum(j) - sum(i - 1);
	}

	private int lowerbit(int n) {
		return n & (~n + 1);
	}
}
