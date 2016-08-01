package puzzles.skyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 218. The Skyline Problem
 * 
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are given
 * the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings
 * collectively (Figure B).
 * 
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You
 * may assume all buildings are perfect rectangles grounded on an absolutely
 * flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [
 * [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in
 * between any two adjacent buildings should be considered part of the skyline
 * contour.
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3
 * 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 * Notes:
 * - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * - The input list is already sorted in ascending order by the left x position Li.
 * - The output list must be sorted by the x position.
 * - There must be no consecutive horizontal lines of equal height in the output
 * skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not
 * acceptable; the three lines of height 5 should be merged into one in the
 * final output as such: [...[2 3], [4 5], [12 7], ...]
 * 
 * @see https://leetcode.com/problems/the-skyline-problem/
 *
 */
public abstract class Skyline {

    public abstract List<int[]> getSkyline(int[][] buildings);
    
    private static final Skyline skyline = new DivideAndConquer();
    
    private static void test(int[][] buildings) {
    	List<int[]> res = skyline.getSkyline(buildings);
    	for (int[] pair : res) {
			System.out.println(Arrays.toString(pair));
		}
    	System.out.println("----------------------");
	}
    
    public static void main(String[] args) {
    	test(new int[][]{}); // []
    	test(new int[][] {
			{2, 9, 10},
			{3, 7, 15},
			{5, 12, 12},
			{5, 13, 10},
			{15, 20, 10},
			{19, 24, 8}
    	}); // [[2, 10],[3, 15],[7, 12],[12, 10],[13, 0],[15, 10],[20, 8],[24, 0]]
    	test(new int[][] {
    		{0,2,3},
    		{2,5,3}
    	}); //[[0, 3], [5, 0]] 
    	test(new int[][] {
    		{1,2,1},
    		{1,2,2},
    		{1,2,3},
    	}); // [[1, 3], [2, 0]]
	}
}

/**
 * Original Approach
 * 
 * change input [ [2, 9, 10], [3, 7, 15], [5, 12, 12], [15, 20, 10], [19, 24, 8] ]
 * into [[2: 10], [3: 15], [5: 12], [7: -15], [9: -10], [12: -12], [15: 10], [19: 8], [20: -10], [24: -8]]
 * then maintain tree map to keep adding/removing the input height
 * when heap.peek changed, update the result list with current x position and current height (last Key)
 * NOTE: be careful of merging the output
 * NOTE: TreeMap provide a much better complexity for remove()
 */
class Heap extends Skyline {
	
	@Override
    public List<int[]> getSkyline(int[][] buildings) {
    	List<int[]> res = new ArrayList<>();
    	if (buildings == null || buildings.length == 0) return res;
    	Node[] nodes = getNodes(buildings);
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	map.put(0, 1);
    	int curHeight = 0, last = 0;
    	for (Node node : nodes) {
    		if (hasChanged(map, node)) {
				if ((last = map.lastKey()) != curHeight) {
					curHeight = last;
					res.add(new int[] {node.x, curHeight});
				}
			}
		}
    	return res;
    }
    
    private boolean hasChanged(TreeMap<Integer, Integer> map, Node node) {
    	if (node.isStart) {
    		if (map.containsKey(node.height)) {
    			map.put(node.height, map.get(node.height) + 1);
    			return false;
    		} else {
    			map.put(node.height, 1);
    			return true;
    		}
    	} else {
			int count = map.get(node.height);
			if (count > 1) {
				map.put(node.height, count-1);
				return false;
			} else {
				map.remove(node.height);
				return true;
			}
    	}
    }
    
    private Node[] getNodes(int[][] buildings) {
    	Node[] nodes = new Node[buildings.length * 2];
    	int ind = 0;
    	for (int[] building : buildings) {
			nodes[ind++] = new Node(building[0], building[2], true);
			nodes[ind++] = new Node(building[1], building[2], false);
		}
    	Arrays.sort(nodes);
    	return nodes;
	}
    
    private class Node implements Comparable<Node> {
    	int x, height;
    	boolean isStart;
    	public Node(int x, int val, boolean isStart) { this.height = val; this.x = x; this.isStart = isStart; }
    	@Override
    	public String toString() {
    		return String.format("[%d: %d]", x, (isStart) ? height : -height);
    	}
		@Override
		public int compareTo(Node o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
		}
    }
}

/**
 * Divide and Conquer approach
 * Reference: https://discuss.leetcode.com/topic/38738/10ms-divide-and-conquer-java-solution
 * Reference: http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
 */
class DivideAndConquer extends Skyline {

	@Override
	public List<int[]> getSkyline(int[][] buildings) {
	    return divide(buildings, 0, buildings.length-1);
	}
	
	private List<int[]> divide(int[][] buildings, int low, int high) {
		if (low > high) { 
			return new ArrayList<>();
		} else if (low == high) {
			List<int[]> res = new ArrayList<>(2);
			res.add(new int[]{buildings[low][0], buildings[low][2]});
			res.add(new int[]{buildings[low][1], 0});
			return res;
		} else {
			int mid = low + (high - low) / 2;
			return merge(divide(buildings, low, mid), divide(buildings, mid+1, high));
		}
	}
	
	private List<int[]> merge(List<int[]> left, List<int[]> right) {
		int n = left.size(), m = right.size();
		List<int[]> res = new ArrayList<>(n + m);
		int h0 = 0, h1 = 0, l0 = 0, l1 = 0;
		int curHeight = 0, xPosition = 0, max = 0;
		int[] cur0 = null, cur1 = null;
		while (l0 < n && l1 < m) {
			cur0 = left.get(l0);
			cur1 = right.get(l1);
			if (cur0[0] < cur1[0]) {
				xPosition = cur0[0];
				h0 = cur0[1];
				l0++;
			} else if (cur0[0] > cur1[0]) {
				xPosition = cur1[0];
				h1 = cur1[1];
				l1++;
			} else {
				xPosition = cur0[0];
				h0 = cur0[1];
				h1 = cur1[1];
				l0++;
				l1++;
			}
			if (curHeight != (max = Math.max(h0, h1))) {
				curHeight = max;
				res.add(new int[]{xPosition, curHeight});
			}
		}
		while (l0 < n) res.add(left.get(l0++));
		while (l1 < m) res.add(right.get(l1++));
		return res;
	}
}




