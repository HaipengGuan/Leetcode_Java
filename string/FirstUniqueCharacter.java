package string;

/**
 * 387. First Unique Character in a String
 * 
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Examples:
 * 
 * s = "leetcode"
 * return 0.
 * 
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * 
 * @see https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 */
public class FirstUniqueCharacter {
	
    public int firstUniqChar(String s) {
    	int n = s.length();
        if (n == 0) return -1;
        if (n == 1) return 0;
        int[] map = new int[26];
        for (int i = 0; i < n; i++) {
        	map[s.charAt(i) - 'a']++;
		}
        for (int i = 0; i < n; i++) {
			if (map[s.charAt(i) - 'a'] == 1) return i;
		}
        return -1;
    }
    
    
    private static FirstUniqueCharacter uniqueCharacter = new FirstUniqueCharacter();
    
    public static void main(String[] args) {
		System.out.println(uniqueCharacter.firstUniqChar(""));
		System.out.println(uniqueCharacter.firstUniqChar("x"));
		System.out.println(uniqueCharacter.firstUniqChar("aaaaa"));
		System.out.println(uniqueCharacter.firstUniqChar("leetcode"));
		System.out.println(uniqueCharacter.firstUniqChar("loveleetcode"));
		System.out.println(uniqueCharacter.firstUniqChar("abcde"));
	}
}
