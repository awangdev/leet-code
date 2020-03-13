M
tags: Greedy
time: O(n)
space: O(1)

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
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i 
to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel 
around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

*/

/**

Thoughts: Loop through the gas station, and track the possible starting index.
Start from i = 0, use a second pointer move to track how far we are travelling
calculate: remain += gas[i] - cost[i].
if remain < 0, fail. This mean: with all remain gas before index i + gas[i], we won't make it to index i+1
Therefore, reset startIndex = i + 1: moving on to next possible starting point.

'total':simply indicates if we can make it a circle
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
    		return -1;
    	}
    	int startIndex = 0, remain = 0, total = 0;
    	for (int i = 0; i < gas.length; i++) {
			total += gas[i] - cost[i];
    		remain += gas[i] - cost[i];
    		if (remain < 0) {
    			remain = 0;
    			startIndex = i + 1; // restart
    		}
    	}
		return total < 0 ? -1 : startIndex;
    }
}

```