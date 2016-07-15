package design;

import java.util.HashMap;

/**
 * 146. LRU Cache
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. It
 * should support the following operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. set(key, value) - Set or insert the
 * value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 * 
 * @see https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {
    
    private class DLinkedNode {
    	int key, val;
    	DLinkedNode prev, next;
    	public DLinkedNode() {}
    	public DLinkedNode(int key, int val) {
    		this.key = key;
    		this.val = val;
    	}
    }
    
	HashMap<Integer, DLinkedNode> cache;
	DLinkedNode head, tail;
	int totalCapacity;
	
	// ----------------------------------------
    public LRUCache(int capacity) {
    	totalCapacity = capacity;
    	cache = new HashMap<>();
    	head = new DLinkedNode();
    	tail = new DLinkedNode();
    	head.next = tail;
    	tail.prev = head;
    }
    
    public int get(int key) {
    	DLinkedNode node = cache.get(key);
    	if (node == null) {
			return -1;
		} else {
	    	touch(node);
	    	return node.val;	
		}
    }
    
    public void set(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
        	node = new DLinkedNode(key, value);
        	if (cache.size() == totalCapacity) {
				DLinkedNode last = removeLast();
				cache.remove(last.key);
			}
			addFirst(node);
			cache.put(key, node);
		} else {
			node.val = value;
			touch(node);
		}
    }
    
	// ----------------------------------------
    private void touch(DLinkedNode node) {
		remove(node);
		addFirst(node);
	}
    
    private void remove(DLinkedNode node) {
		DLinkedNode prevNode = node.prev;
		DLinkedNode nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
    
    private void addFirst(DLinkedNode node) {
    	node.next = head.next;
    	head.next.prev = node;
    	head.next = node;
    	node.prev = head;
	}
    
    private DLinkedNode removeLast() {
		DLinkedNode last = tail.prev;
		remove(last);
		return last;
	}

}
