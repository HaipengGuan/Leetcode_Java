package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 332. Reconstruct Itinerary
 * 
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note:
 * 
 * 1. If there are multiple valid itineraries, you should return the itinerary
 * that has the smallest lexical order when read as a single string. For
 * example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"].
 * 2. All airports are represented by three capital letters (IATA code).
 * 3. You may assume all tickets form at least one valid itinerary.
 * 
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * 
 * @see https://leetcode.com/problems/reconstruct-itinerary/
 *
 */
public class ReconstructItinerary {
	

	HashMap<String, PriorityQueue<String>> graph = null;
	LinkedList<String> path = null;
	
    public List<String> findItinerary(String[][] tickets) {
    	graph = new HashMap<>();
    	path = new LinkedList<>();
    	for (String[] ticket : tickets) {
			String from = ticket[0], to = ticket[1];
			if (!graph.containsKey(from)) graph.put(from, new PriorityQueue<String>());
			graph.get(from).offer(to);
		}
    	hierholzer("JFK");
    	return path;
    }
    
    private void hierholzer(String pos) {
    	PriorityQueue<String> neighbors = graph.get(pos);
		while (neighbors != null && !neighbors.isEmpty()) 
			hierholzer(neighbors.poll());
    	path.addFirst(pos);
    }
    
    // ----------------
    private static ReconstructItinerary itinerary = new ReconstructItinerary();
    
    private static void test(String[][] tickets, String ans) {
    	String tmp = itinerary.findItinerary(tickets).toString();
    	if (tmp.equals(ans)) {
    		System.out.println("PASS");
    	} else {
    		System.out.println("----------------");
    		System.out.println(tmp);
    		System.out.println(ans);
    		System.out.println("----------------");
    	}
    }
    
    public static void main(String[] args) {
    	test(new String[][]{{"JFK","SFO"}}, "[JFK, SFO]");
    	test(new String[][]{{"JFK","SFO"}, {"SFO","JFK"}}, "[JFK, SFO, JFK]");
    	test(new String[][]{{"JFK","SFO"}, {"SFO","JFK"}, {"JFK","SFO"}}, "[JFK, SFO, JFK, SFO]");
    	test(new String[][]{{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}}, "[JFK, ATL, JFK, SFO, ATL, SFO]");
    	test(new String[][]{{"MUC","LHR"}, {"JFK","MUC"}, {"SFO","SJC"}, {"LHR","SFO"}}, "[JFK, MUC, LHR, SFO, SJC]");
    	test(new String[][]{{"JFK","KUL"}, {"JFK","NRT"}, {"NRT","JFK"}}, "[JFK, NRT, JFK, KUL]");
    	test(new String[][]{{"EZE","AXA"}, {"TIA","ANU"}, {"ANU","JFK"}, {"JFK","ANU"}, {"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}}, "[JFK, ANU, EZE, AXA, TIA, ANU, JFK, TIA, ANU, TIA, JFK]");
	}
}
