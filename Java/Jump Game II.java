/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Tags Expand 
Greedy Array

Thanks to Yu’s Garden blog
Thinking process:
0.   Use two pointers pStart and pEnd to track the potential locations we can move to.
Consider a range from current spot to the farthest spot: try to find a max value from this range, and see if the max can reach the tail of array. 
If no max can read the tail of array, that means we need to move on. At this point, let pStart = pEnd + 1. At same time, move pEnd to the max spot we can go to. Since pEnd moves forward, we could step++
If max reach the tail of array, return the steps.
*/

public class Solution {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int pStart = 0;
        int pEnd = 0;
        int steps = 0;
        while (pEnd < A.length - 1) {
            steps++;    //Cound step everytime when pEnd is moving to the farthest.
            int farthest = 0;
            //Find farest possible and see if reach the tail
            for (int i = pStart; i <= pEnd; i++) {
                farthest = Math.max(farthest, i + A[i]);
                if (farthest >= A.length - 1) {
                    return steps;
                }
            }
            //Re-select pointer position for start and end
            pStart = pEnd + 1;
            pEnd = farthest;
        }
        return -1;  //This is the case where no solution can be found.
    }
}


//Also DP from nineChapter:
http://www.ninechapter.com/solutions/jump-game-ii/

