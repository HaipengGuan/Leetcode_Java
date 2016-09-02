package graph.topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.TabStop;

/**
 * 210. Course Schedule II
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If
 * it is impossible to finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * 
 * There are a total of 4 courses to take. To take course 3 you should have
 * finished both courses 1 and 2. Both courses 1 and 2 should be taken after you
 * finished course 0. So one correct course order is [0,1,2,3]. Another correct
 * ordering is[0,2,1,3].
 * 
 * @see https://leetcode.com/problems/course-schedule-ii/
 *
 */
public class CourseScheduleII {

	int[] ordered = null;
	int top = -1;
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int[] edge : prerequisites) {
			graph.get(edge[1]).add(edge[0]);
		}
		int[] visited = new int[numCourses];
		ordered = new int[numCourses];
		top = numCourses-1;
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0 && !dfs(i, graph, visited)) {
				return new int[0];
			}
		}
		return ordered;
	}
	
	private boolean dfs(int start, List<List<Integer>> graph, int[] visited) {
		visited[start] = 1;
		for (int neighbor : graph.get(start)) {
			if (visited[neighbor] == 2) continue;
			if (visited[neighbor] == 1 || !dfs(neighbor, graph, visited)) return false;
		}
		visited[start] = 2;
		ordered[top--] = start;
		return true;
	}
	
	
	private static void print(int[] data) {
		System.out.println(Arrays.toString(data));
	}

	public static void main(String[] args) {
		CourseScheduleII scheduleII = new CourseScheduleII();
		print(scheduleII.findOrder(2, new int[][]{{0,1}}));
		print(scheduleII.findOrder(2, new int[][]{{1,0}}));
		print(scheduleII.findOrder(2, new int[][]{{1,0}, {0,1}}));
		print(scheduleII.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
	}

}
