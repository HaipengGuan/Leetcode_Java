package dynamic_programming;

/**
 * 87. Scramble String
 * 
 * Given a string s1, we may represent it as a binary tree by partitioning it to
 * two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 * To scramble the string, we may choose any non-leaf node and swap its two
 * children.
 * 
 * For example, if we choose the node "gr" and swap its two children, it
 * produces a scrambled string "rgeat".
 * 
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it
 * produces a scrambled string "rgtae".
 * 
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a
 * scrambled string of s1.
 * 
 * @see https://leetcode.com/problems/scramble-string/
 *
 */
public class ScrambleString {
	
    public boolean isScramble(String s1, String s2) {
    	if (s1.length() != s2.length()) return false;
    	return isScramble(s1.toCharArray(), 0, s2.toCharArray(), 0, s1.length());
	}
    
    private boolean isScramble(char[] s1, int i, char[] s2, int j, int len) {
		if (len == 0) return true;
		if (len == 1) return s1[i] == s2[j];
		boolean allSame = true;
		int[] count = new int[26];
		for (int i1 = i, j1 = j; i1 < i+len; i1++, j1++) {
			count[s1[i1] - 'a']++;
			count[s2[j1] - 'a']--;
			if (allSame) allSame &= (s1[i1] == s2[j1]);
		}
		if (allSame) return true;
		for (int c : count) {
			if (c != 0) return false;
		}
		for (int subLen = 1; subLen < len; subLen++) {
			if ((isScramble(s1, i, s2, j, subLen) && isScramble(s1, i+subLen, s2, j+subLen, len - subLen)
					||(isScramble(s1, i, s2, j+len-subLen, subLen) && isScramble(s1, i+subLen, s2, j, len - subLen)))) {
				return true;
			}
		}
		return false;
	}
    
    // ------------
    private static ScrambleString scramble = new ScrambleString();
    
    private static void test(String s1, String s2) {
		boolean res1 = scramble.isScramble(s1, s2);
		boolean res2 = scramble.isScramble(s2, s1);
		if (res1 != res2) {
			System.out.println("ERROE!");
			System.out.println(s1);
			System.out.println(s2);
		} else {
			System.out.println(res1);
		}
	}
    
    public static void main(String[] args) {
    	test("", "");
		test("great", "rgeat");
		test("aaab", "abaa");
		test("cba", "bca");
		test("abc", "acb");
		test("abc", "bca");
		test("dcba", "acdb");
		test("phlvandlvyupcq", "paplyvvdhnulcq");
	}
}
