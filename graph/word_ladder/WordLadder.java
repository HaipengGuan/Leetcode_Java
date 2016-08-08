package graph.word_ladder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 127. Word Ladder
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 1. Only one letter can be changed at a time 
 * 2. Each intermediate word must exist in the word list For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * - Return 0 if there is no such transformation sequence.
 * - All words have the same length.
 * - All words contain only lowercase alphabetic characters.
 * 
 * @see https://leetcode.com/problems/word-ladder/
 *
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(beginWord, 1));
        wordList.add(endWord);
        while (!queue.isEmpty()) {
			Node head = queue.poll();
			if (expand(head, endWord, queue, wordList)) return head.layer + 1;
		}
        return 0;
    }
    
    private boolean expand(Node node, String endWord, Queue<Node> queue, Set<String> wordList) {
		char[] charArray = node.string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c0 = charArray[i];
			for (char c = 'a'; c <= 'z'; c++) {
				charArray[i] = c;
				String next = new String(charArray);
				if (!wordList.contains(next)) continue;
				if (next.equals(endWord)) return true;
				queue.add(new Node(next, node.layer + 1));
				wordList.remove(next);
			}
			charArray[i] = c0;
		}
		return false;
	}

    private class Node {
    	String string;
    	int layer;
    	public Node(String string, int layer) {
    		this.string = string;
    		this.layer = layer;
		}
    }
    
    
    // -----------------------------
    private static WordLadder ladder = new DoubleBFS();
    
    private static Set<String> getSet(String[] strings) {
		return new HashSet<>(Arrays.asList(strings));
	}
    
    private static void test(String beginWord, String endWord, String[] words) {
		System.out.println(ladder.ladderLength(beginWord, endWord, getSet(words)));
	}
    
    public static void main(String[] args) {
		test("hit", "cog", new String[]{"hot","dot","dog","lot","log"}); // 5
		test("hot", "dog", new String[]{"hot","dot"}); // 3
		test("hot", "dog", new String[]{"hot","dog"}); // 0
		test("a", "c", new String[]{"a","b","c"}); // 2
	}
    
}


class DoubleBFS extends WordLadder {
	
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    	Queue<String> queue1 = new LinkedList<>(), queue2 = new LinkedList<>();
    	Map<String, Integer> reached1 = new HashMap<>(), reached2 = new HashMap<>();
    	queue1.add(beginWord); queue2.add(endWord);
    	reached1.put(beginWord, 1); reached2.put(endWord, 1);
    	wordList.remove(beginWord); wordList.remove(endWord);
    	int res = 0;
    	while (!queue1.isEmpty() && !queue2.isEmpty()) {
			if ((res = expand(queue1.poll(), reached1, reached2, queue1, wordList)) != 0) return res;
			if ((res = expand(queue2.poll(), reached2, reached1, queue2, wordList)) != 0) return res;
		}
        return 0;
    }
    
    private int expand(String word, Map<String, Integer> reached, Map<String, Integer> target, Queue<String> queue, Set<String> wordList) {
    	char[] chars = word.toCharArray();
    	for (int i = 0; i < chars.length; i++) {
    		char c0 = chars[i];
    		for (char c = 'a'; c <= 'z'; c++) {
    			chars[i] = c;
    			String next = new String(chars);
    			if (target.containsKey(next)) return target.get(next) + reached.get(word);
    			if (wordList.contains(next)) {
    				queue.add(next);
    				reached.put(next, reached.get(word)+1);
    				wordList.remove(next);
    			}
    		}
    		chars[i] = c0;
    	}
    	return 0;
	}
    
}
