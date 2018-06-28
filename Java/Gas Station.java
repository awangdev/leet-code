M
1530150074
tags: Greedy

给一串gas station array, 每个index里面有一定数量gas.

给一串cost array, 每个index有一个值, 是reach下一个gas station的油耗.

array的结尾地方, 再下一个点是开头, 形成一个circle route.

找一个index, 作为starting point: 让车子从这个点, 拿上油, 开出去, 还能开回到这个starting point

#### Greedy
- 不论从哪一个点开始, 都可以记录总油耗, `total = {gas[i] - cost[i]}`. 最后如果total < 0, 无论从哪开始, 必然都不能走回来
- 可以记录每一步的油耗积累, `remain += gas[i] - cost[i]`
- 一旦 remain < 0, 说明之前的starting point 不合适, 也就是说, 初始点肯定在后面的index. 重设: start = i + 1
- single for loop. Time: O(n)

#### NOT DP
- 看似有点像 House Robber II, 但是问题要求的是: 一个起始点的index
- 而不是求: 最后点可否走完/最值/计数

```
/*
There are N gas stations along a circular route, 
where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas 
to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Example
Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.

Note
The solution is guaranteed to be unique.

Challenge
O(n) time and O(1) extra space

Tags Expand 
Greedy

*/

/**

Previous Notes:
Thoughts:
Loop through the gas station, and track the possible starting index.
Start from i = 0 ~ gas.length, and use a second pointer move to track how far we are travelling
	calculate: remain += gas[i] - cost[i]. (remain + gas[i] - cost[i]: the remaining gas plus i's gas, can we make it to i+1 gas station?)
	if remain < 0, fail. Note: if from i ~ j can't work, even it's possible that i can make it to i+1's station, but i+1 ~ j won't work still.
		Thus, once i's station failed to get to x, set index = x + 1: we are moving on to next possible starting point.

'total':simply indicates if we can make it a circle
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
    		return -1;
    	}
    	int start = 0;
    	int remain = 0; // remained gas
    	int total = 0;
    	for (int i = 0; i < gas.length; i++) {
    		remain += gas[i] - cost[i];
    		if (remain < 0) {
    			remain = 0;
    			start = i + 1; // restart
    		} 
    		total += gas[i] - cost[i];
    	}
    	if (total < 0) {
    		return -1;
    	}
    	return start;
    }
}

```