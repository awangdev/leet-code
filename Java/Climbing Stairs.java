/*
40% Accepted
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example
Tags Expand 

Thinking process:
State: at i level, f[i] is the ways to climb to i position.
Function: f[i] = f[i-1] + f[i-2]. 
	f[i] is constructed from 2 branches: 
		Last step is 1 from f[i-1]
		Last step is 2 from f[i-2]
	This idea can be presented using a tree. However we don’t need to do recursive. We just need to use two pointers to withhold 2 level’s values.
Init: The for loop starts at level2, so before level 2 there are 2 init states:
	f[0] == 1. This means we jump 2 steps from level0 to level2. 
	f[i] == 1.  This means we jump 1 steps to level1, then jump another step to level2
Answer: f[n]
*/

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int last = 1;   //Init f[1]
        int lastlast = 1;   //Init f[0]
        int now = 0;
        for (int i = 2; i <= n; i++) {  //Start from level2
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}


