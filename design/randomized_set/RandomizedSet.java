package design.randomized_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 380. Insert Delete GetRandom O(1)
 * 
 * Design a data structure that supports all following operations in average O(1) time.

 * 1. insert(val): Inserts an item val to the set if not already present.
 * 2. remove(val): Removes an item val from the set if present.
 * 3. getRandom: Returns a random element from current set of elements. 
 * Each element must have the same probability of being returned.
 * 
 * @see https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 */
public  class RandomizedSet {

	private HashMap<Integer, Integer> map = null;
	private ArrayList<Integer> list = null;
	
    /** Initialize your data structure here. */
    public RandomizedSet(){
    	map = new HashMap<>();
    	list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public  boolean insert(int val){
		if (!map.containsKey(val)) {
			map.put(val, list.size());
			list.add(val);
			return true;
		} else return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public  boolean remove(int val){
		if (map.containsKey(val)) {
			int idx = map.get(val), n = list.size();
			if (idx < n-1) {
				int last = list.get(n-1);
				list.set(idx, last);
				map.put(last, idx);
			}
			list.remove(n-1);
			map.remove(val);
			return true;
		} else return false;
    	
    }
    
    /** Get a random element from the set. */
    public  int getRandom(){
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
    
}