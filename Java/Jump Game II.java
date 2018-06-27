H
1530083174
tags: Array, Greedy, DP, Coordinate DP

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Greedy
- always aiming for the `farest can go`
- if the `farest can go` breaches the end, return steps
- otherwise, send `start=end+1`, `end=farest` and keep stepping from here
- though trying with 2 loops, worst case [1,1,1,...1,1] could have O(n^2)
- But on average should be jumpping through the array without looking back
- time: average O(n)

#### Previous Notes, Greedy
- 维护一个range, 是最远我们能走的. 
- index/i 是一步一步往前, 每次当 i <= range, 做一个while loop， 在其中找最远能到的地方 maxRange
- 然后更新 range = maxRange
- 其中step也是跟index是一样, 一步一步走.
- 最后check的condition是，我们最远你能走的range >= nums.length - 1, 说明以最少的Step就到达了重点。Good.

#### Even simpler Greedy
- 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
- track the farest point
- whenver curr index reachest the farest point, that means we are making a nother move, so count++
- there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
- 其实跟第一个greedy的思维模式是一模一样的.


#### DP 
- DP[i]: 在i点记录，走到i点上的最少jump次数
- dp[i] = Math.min(dp[i], dp[j] + 1);
- condition (j + nums[j] >= i)
- 注意使用 dp[i] = Integer.MAX_VALUE做起始值, 来找min
- time: O(n^2), slow, and timesout

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



/*

Thanks to Yu’s Garden blog
Thinking process:
0.   Use two pointers pStart and pEnd to track the potential locations we can move to.
Consider a range from current spot to the farthest spot: try to find a max value from this range, and see if the max can reach the tail of array. 
If no max can read the tail of array, that means we need to move on. At this point, let pStart = pEnd + 1. At same time, move pEnd to the max spot we can go to. Since pEnd moves forward, we could step++
If max reach the tail of array, return the steps.
*/
// 95%
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int start = 0, end = 0, steps = 0;
        while (end < n - 1) {
            steps++; //Cound step everytime when pEnd is moving to the farthest.
            int farthest = 0;
            //Find farest possible and see if reach the tail
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n - 1) {
                    return steps;
                }
            }
            //Re-select pointer position for start and end
            start = end + 1;
            end = farthest;
        }
        return 0;  //This is the case where no solution can be found.
    }
}


/*
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

// even simpler, 95%, O(n)
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int count = 0, farest = 0, maxRange = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == farest) {
                count++;
                farest = max;
            }
        }
        return count;
    }
}


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

```