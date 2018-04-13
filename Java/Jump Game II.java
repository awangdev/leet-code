H
1523601576
tags: Array, Greedy, DP, Coordinate DP

Greedy, 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html

维护一个range, 是最远我们能走的. 

index/i 是一步一步往前, 每次当 i <= range, 做一个while loop， 在其中找最远能到的地方 maxRange

然后更新 range = maxRange

其中step也是跟index是一样, 一步一步走.

最后check的condition是，我们最远你能走的range >= nums.length - 1, 说明以最少的Step就到达了重点。Good.


```
/*
Given an array of non-negative integers, 
you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Tags Expand 
Greedy Array

*/

// DP, coordinate DP, timeout, O(n^2)
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}

/*
    3.18 recap. 
    Wihtin farest we can go, renew farest we can go, renew number of steps.
    http://blog.csdn.net/havenoidea/article/details/11853301

    图解：
    http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
*/
//Greedy. Within max range we can jump to, do another loop to renew the max we can go.
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int index = 0;
        int step = 0;
        int range = 0;
        int maxRange = 0;
        
        while (index < nums.length) {
            if (range >= nums.length - 1) {
                break;
            }
            while (index <= range) {
                maxRange = Math.max(maxRange, index + nums[index]);
                index++;
            }
            range = maxRange;
            step++;
        }
        return step;
    }
}


/*

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


```