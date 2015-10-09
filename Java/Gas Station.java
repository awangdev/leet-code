/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Example
Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.

Note
The solution is guaranteed to be unique.

Challenge
O(n) time and O(1) extra space

Tags Expand 
Greedy

Thoughts:
Loop through the gas station, and track the possible starting index.
Start from i = 0 ~ gas.length, and use a second pointer move to track how far we are travelling
	calculate: remain += gas[i] - cost[i]. (remain + gas[i] - cost[i]: the remaining gas plus i's gas, can we make it to i+1 gas station?)
	if remain < 0, fail. Note: if from i ~ j can't work, even it's possible that i can make it to i+1's station, but i+1 ~ j won't work still.
		Thus, once i's station failed to get to x, set index = x + 1: we are moving on to next possible starting point.

'total':simply indicates if we can make it a circle
*/

public class Solution {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
    		return -1;
    	}
    	int start = 0;
    	int remain = 0;
    	int total = 0;
    	for (int i = 0; i < gas.length; i++) {
    		remain += gas[i] - cost[i];
    		if (remain < 0) {
    			remain = 0;
    			start = i + 1;
    		} 
    		total += gas[i] - cost[i];
    	}
    	if (total < 0) {
    		return -1;
    	}
    	return start;
    }
}
