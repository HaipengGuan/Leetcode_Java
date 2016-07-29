package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 68. Text Justification
 * 
 * Given an array of words and a length L, format the text such that each line
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * For example,
 * 
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * 
 * L: 16.
 * 
 * Return the formatted lines as:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 
 * Note: Each word is guaranteed not to exceed L in length.
 * 
 * @see https://leetcode.com/problems/text-justification/
 *
 */
public class TextJustificationGreedy {

    public List<String> fullJustify(String[] words, int maxWidth) {
    	List<String> res = new ArrayList<>();
    	if (maxWidth == 0) return Arrays.asList("");
    	int n = words.length;
    	int curWordLen = words[0].length();
    	int begin = 0;
    	for (int i = 1; i < n; i++) {
    		int m = words[i].length();
    		if (curWordLen + i - begin + m > maxWidth) {
    			res.add(combine(words, begin, i-1, curWordLen, maxWidth));
    			begin = i;
    			curWordLen = m;
    		} else {
    			curWordLen += m;
    		}
		}
    	res.add(combine(words, begin, n-1, curWordLen, maxWidth));
    	return res;
    }
    
    private String combine(String[] words, int begin, int end, int wordLen, int maxWidth) {
    	int spaceLen = 1, extra = 0;
    	if (end < words.length - 1) {
    		spaceLen = (maxWidth - wordLen) / (end - begin);
    		extra = (maxWidth - wordLen) % (end - begin);
    	}
    	String spaces = getSpace(spaceLen);
    	StringBuffer res = new StringBuffer(words[begin]);
    	for (int i = begin+1; i <= end; i++) {
    		if (extra-- > 0) res.append(' ');
    		res.append(spaces + words[i]);
		}
    	if (res.length() < maxWidth) res.append(getSpace(maxWidth-res.length()));
    	return res.toString();
	}
    
    private String getSpace(int len) {
		return new String(new char[len]).replace('\0', ' ');
	}
    
    // --------------------------------

    private static TextJustificationGreedy justification = new TextJustificationGreedy();
    
    private static void test(String[] words, int maxWidth) {
    	List<String> res = justification.fullJustify(words, maxWidth);
    	for (String string : res) {
			System.out.println(String.format("\"%s\"", string));
		}
    	System.out.println("-------------");
	}

    public static void main(String[] args) {
    	test(new String[] {}, 0);
    	test(new String[] {""}, 0);
    	test(new String[] {"haha"}, 5);
    	test(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16);
    	test(new String[] {"What","must","be","shall","be.", "HP"}, 14);
	}
    


}
