package puzzles;

/**
 * 134. Gas Station
 * 
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * Note: 
 * The solution is guaranteed to be unique.
 * 
 * @see https://leetcode.com/problems/gas-station/
 *
 */
public class GasStation {
	
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	int n = gas.length, sum = 0, start = 0, cur = 0;
    	for (int i = 0; i < n; i++) {
    		int temp = gas[i] - cost[i];
    		sum += temp;
			if ((cur += temp) < 0) {
				start = i+1;
				cur = 0;
			}
		}
    	return (sum < 0) ? -1 : start;
    }
    
    // ------------------
    public static void main(String[] args) {
		GasStation gas = new GasStation();
		System.out.println(gas.canCompleteCircuit(new int[]{1,5}, new int[]{2,4}));		// 1
		System.out.println(gas.canCompleteCircuit(new int[]{2,3,1}, new int[]{3,1,2}));	// 1
		System.out.println(gas.canCompleteCircuit(new int[]{6,1,4,3,5}, new int[]{3,8,2,4,2})); // 2
		System.out.println(gas.canCompleteCircuit(new int[]{2,0,1,2,3,4,0}, new int[]{0,1,0,0,0,0,11})); // 0
	}
	
}
