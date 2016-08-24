package string;

/**
 * 383. Ransom Note
 * 
 * Given an arbitrary ransom note string and another string containing letters
 * from all the magazines, write a function that will return true if the ransom
 * note can be constructed from the magazines ; otherwise, it will return false.
 * 
 * Each letter in the magazine string can only be used once in your ransom note.
 * 
 * Note: You may assume that both strings contain only lowercase letters.
 * 
 * canConstruct("a", "b") -> false canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * 
 * @see https://leetcode.com/problems/ransom-note/
 *
 */
public class RansomNote {
	
    public boolean canConstruct(String ransomNote, String magazine) {
        int count = ransomNote.length();
        if (count == 0) return true;
        if (magazine.length() == 0) return false;
        int[] map = new int[26];
        for (char c : ransomNote.toCharArray()) {
			map[c - 'a']--;
		}
        for (char c : magazine.toCharArray()) {
			if (++map[c - 'a'] <= 0) count--;
        	if (count == 0) return true;
        }
        return false;
    }
    
    // ------------
    private static final RansomNote RANSOM_NOTE = new RansomNote();
    
    public static void main(String[] args) {
    	System.out.println(RANSOM_NOTE.canConstruct("", "b")); 		// true
    	System.out.println(RANSOM_NOTE.canConstruct("a", "")); 		// false
    	System.out.println(RANSOM_NOTE.canConstruct("a", "b")); 	// false
    	System.out.println(RANSOM_NOTE.canConstruct("aa", "ab")); 	// false
    	System.out.println(RANSOM_NOTE.canConstruct("aa", "aab")); 	// true
	}
    
}
