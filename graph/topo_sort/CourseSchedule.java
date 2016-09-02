package graph.topo_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 207. Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0, and to take course 0 you should also have finished course
 * 1. So it is impossible.
 * 
 * @see https://leetcode.com/problems/course-schedule/
 *
 */
public class CourseSchedule {
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int[] edge : prerequisites) {
			graph.get(edge[1]).add(edge[0]);
		}
		int[] visited = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0 && hasCycleDFS(i, graph, visited)) 
				return false;
		}
		return true;
	}
	
	public boolean hasCycleDFS(int start, List<List<Integer>> graph, int[] visited) {
		visited[start] = 1;
		for (Integer neighbor : graph.get(start)) {
			if (visited[neighbor] == 2) continue;
			if (visited[neighbor] == 1 || hasCycleDFS(neighbor, graph, visited)) return true;
		}
		visited[start] = 2;
		return false;
	}
	
	public static void main(String[] args) {
		CourseSchedule schedule = new CourseSchedule();
		System.out.println(schedule.canFinish(2, new int[][]{{1,0}}));
		System.out.println(schedule.canFinish(2, new int[][]{{1,0}, {0,1}}));
	}
}
