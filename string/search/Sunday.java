package string.search;

import string.StrStr;

public class Sunday extends StrStr {

	@Override
	public int strStr(String haystack, String needle) {
		return strStr(haystack.toCharArray(), needle.toCharArray());
	}
	
	public int strStr(char[] source, char[] target) {
		int n = source.length, m = target.length;
		if (n < m) return -1;
		if (m == 0) return 0;
		int idx1 = m-1, idx2, k;
		while (idx1 < n) {
			for (k = idx1, idx2 = m-1; idx2 >= 0 && source[k] == target[idx2]; k--, idx2--);
			if (idx2 < 0) return idx1 - m + 1;
			if (idx1 == n-1) return -1;
			for (idx2 = m-1; idx2 >= 0 && target[idx2] != source[idx1+1]; idx2--);
			idx1 += m - idx2;
		}
		return -1;
	}

	// ----------------------
	private static StrStr sunday = new Sunday();
	
	private static void test(String haystack, String needle) {
		System.out.println(sunday.strStr(haystack, needle) == haystack.indexOf(needle));
	}
	
	public static void main(String[] args) {
		test("baa", "aa");
		test("ABC ABCDAB ABCDABCDABDE", "ABCDABD");
	}
	
}
