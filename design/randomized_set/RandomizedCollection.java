package design.randomized_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * 
 * Design a data structure that supports all following operations in average O(1) time.
 * 
 * Note: Duplicate elements are allowed.
 * 
 * 1. insert(val): Inserts an item val to the collection.
 * 2. remove(val): Removes an item val from the collection if present.
 * 3. getRandom: Returns a random element from current collection of elements. 
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 * 
 * @see https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 *
 */
public class RandomizedCollection {
	
	private HashMap<Integer, HashSet<Integer>> map = null;
	private ArrayList<Integer> list = null;
	
    /** Initialize your data structure here. */
    public RandomizedCollection() {
    	map = new HashMap<>();
    	list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
		if (!map.containsKey(val)) {
			map.put(val, new HashSet<>(Arrays.asList(list.size())));
			list.add(val);
			return true;
		} else {
			map.get(val).add(list.size());
			list.add(val);
			return false;
		}
        
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
    	if (map.containsKey(val)) {
    		HashSet<Integer> set = map.get(val);
    		int idx = set.iterator().next(), n = list.size();
    		set.remove(idx);
    		if (idx < n-1) {
    			int lasdVal = list.get(n-1);
    			list.set(idx, lasdVal);
    			HashSet<Integer> lastSet = map.get(lasdVal);
    			lastSet.remove(n-1);
    			lastSet.add(idx);
    		}
    		list.remove(n-1);
    		if (set.isEmpty()) map.remove(val);
    		return true;
    	} else return false;
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
		int idx = ThreadLocalRandom.current().nextInt(list.size());
		return list.get(idx);
    }
    
}
