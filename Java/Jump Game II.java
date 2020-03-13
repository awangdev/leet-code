H
tags: Array, Greedy, DP, Coordinate DP
time: O(n)
space: O(1)

给一串数字 是可以跳的距离. goal: 跳到最后的index 所可能用的最少次数.

#### Method1: Greedy
- maintain the `farest can go`, and use it the target for i increse towards. Why?
    - 1) when I know the `farest can go`, in fact it is just currently 1 step away.
    - 2) why to iterate from curr `i to farset`? In range [i, farest], we will calc the new `maxRange`
    - 3) once `i` reaches `farset`, update `farest = maxRange`
- greedy concept: once we know the farest we can reach at the moment, it is just 1 step away :)
- On average should be jumpping through the array without looking back
- time: average O(n)
- Impl:
    - 图解 http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    - track the farest point
    - whenver curr index reachest the farest point, that means we are making a nother move, so count++
    - there seems to have one assumption: must have a solution. Otherwise, count will be wrong number. 
    - 其实跟第一个greedy的思维模式是一模一样的.


#### Method2: DP
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

// Method1: Greedy: tracking farest we can go . O(n)
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int count = 0, farest = 0, maxRange = 0, n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (i == farest) {
                count++;
                farest = maxRange;
            }
        }
        return count;
    }
}

// Method2: DP, timeout, O(n^2)
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n - 1];
    }
}




//// Other Greedy impls, similar concepts
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
```