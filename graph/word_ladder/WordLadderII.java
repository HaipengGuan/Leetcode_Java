package graph.word_ladder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 126. Word Ladder II
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * 1. Only one letter can be changed at a time
 * 2. Each intermediate word must exist in the word list
 * 
 * For example,
 * 
 * Given: 
 * beginWord = "hit" 
 * endWord = "cog" 
 * wordList = ["hot","dot","dog","lot","log"] 
 * 
 * Return:
 * 
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * 
 * Note:
 * - All words have the same length. 
 * - All words contain only lowercase alphabetic characters.
 * 
 * @see https://leetcode.com/problems/word-ladder-ii/
 *
 */
public class WordLadderII {
	
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {    	
    	Map<String, List<String>> map = new HashMap<>();
    	List<List<String>> res = new ArrayList<>();
    	Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
    	set1.add(beginWord);
    	set2.add(endWord);
    	boolean found = doubleBFS(set1, set2, map, wordList, false);
    	if (found) dfs(beginWord, endWord, map, new ArrayList<>(Arrays.asList(beginWord)), res);
    	return res;
    }
    
    private boolean doubleBFS(Set<String> reached, Set<String> target, Map<String, List<String>> map, Set<String> wordList, boolean reversed) {
		if (reached.isEmpty()) return false;
		if (reached.size() > target.size()) return doubleBFS(target, reached, map, wordList, !reversed);
		wordList.removeAll(reached);
    	boolean found = false;
    	Set<String> current = new HashSet<>();
    	for (String word : reached) {
    		char[] cs = word.toCharArray();
    		for (int i = 0; i < cs.length; i++) {
    			char c0 = cs[i];
    			for (char c = 'a'; c <= 'z'; c++) {
    				cs[i] = c;
    				String candidate = new String(cs);
    				if (wordList.contains(candidate)) {
    					if (!found && target.contains(candidate)) found = true;
    					current.add(candidate);
    					updateMap(word, candidate, map, reversed);
    				}
    			}
    			cs[i] = c0;
    		}
    	}
    	return found || doubleBFS(target, current, map, wordList, !reversed);
	}
    
    private void updateMap(String word1, String word2, Map<String, List<String>> map, boolean reversed) {
    	String key = reversed ? word2 : word1;
    	String val = reversed ? word1 : word2;
    	if (map.containsKey(key)) {
    		map.get(key).add(val);
    	} else {
    		map.put(key, new ArrayList<>(Arrays.asList(val)));
    	}
	}
    
    private void dfs(String beginWord, String endWord,Map<String, List<String>> map, List<String> cur, List<List<String>> res) {
		if (beginWord.equals(endWord)) {
			res.add(new ArrayList<>(cur));
		} else if (map.containsKey(beginWord)){
			for (String neighbor : map.get(beginWord)) {
				cur.add(neighbor);
				dfs(neighbor, endWord, map, cur, res);
				cur.remove(cur.size()-1);
			}
		}
	}
    
    // ------------------------------
	private static WordLadderII ladderII = new SingleBFS();
	
    private static Set<String> getSet(String[] strings) {
		return new HashSet<>(Arrays.asList(strings));
	}
    
    private static void test(String beginWord, String endWord, String[] words) {
		List<List<String>> res = ladderII.findLadders(beginWord, endWord, getSet(words));
		for (List<String> list : res) {
			System.out.println(list);
		}
		System.out.println("----------------");
	}
    
    public static void main(String[] args) {
		test("hit", "cog", new String[]{"hot","dot","dog","lot","log"}); // 5
		test("hot", "dog", new String[]{"hot","dot"}); // 3
		test("hot", "dog", new String[]{"hot","dog"}); // 0
		test("a", "c", new String[]{"a","b","c"}); // 2
		test("red", "tax", new String[]{"ted","tex","red","tax","tad","den","rex","pee"}); // 4
		test("kite", "ashy", new String[]{"ante","does","jive","achy","kids","kits","cart","ache","ands","ashe","acne","aunt","aids","kite","ants","anne","ashy"}); // 4
	}
}

class SingleBFS extends WordLadderII {
	
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {    	
    	Map<String, List<String>> map = new HashMap<>();
    	List<List<String>> res = new ArrayList<>();
    	wordList.add(endWord);
    	bfs(new HashSet<>(Arrays.asList(beginWord)), map, wordList);
    	dfs(beginWord, endWord, map, new ArrayList<>(Arrays.asList(beginWord)), res);
    	return res;
    }    
    
    private void bfs(Set<String> reached, Map<String, List<String>> map, Set<String> wordList) {
    	if (reached.isEmpty()) return;
    	wordList.removeAll(reached);
    	Set<String> current = new HashSet<>();
    	for (String word : reached) {
			List<String> neighbors = getNeighbor(word, wordList);
			map.put(word, neighbors);
			for (String neighbor : neighbors) {
				current.add(neighbor);
			}
		}
    	bfs(current, map, wordList);
	}
    
    private void dfs(String beginWord, String endWord,Map<String, List<String>> map, List<String> cur, List<List<String>> res) {
		if (beginWord.equals(endWord)) {
			res.add(new ArrayList<>(cur));
		} else if (map.containsKey(beginWord)){
			for (String neighbor : map.get(beginWord)) {
				cur.add(neighbor);
				dfs(neighbor, endWord, map, cur, res);
				cur.remove(cur.size()-1);
			}
		}
	}
    
    private List<String> getNeighbor(String word, Set<String> wordList) {
    	List<String> res = new ArrayList<>();
		char[] cs = word.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			char c0 = cs[i];
			for (char c = 'a'; c <= 'z'; c++) {
				cs[i] = c;
				String candidate = new String(cs);
				if (wordList.contains(candidate)) 
					res.add(candidate);
			}
			cs[i] = c0;
		}
		return res;
	}
}