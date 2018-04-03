M
1522684902
tags: Array, Greedy, DP

给出步数，看能不能jump to end.

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
- Return: DP[dp.length - 1];

#### Greedy
- Keep track of farest can go
- 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
- 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false

```
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

This can be done using DP. However, greedy algorithm is fast in this particular problem.
Consider both solutions.

DP

*/

/*
Thoughts:
Can/Cannot -> DP.
dp[x] = if able to reach dp[x], store true/false
if (dp[x-j] >= 1), then able to reach dp[x]
becomes: if able to jump to dp[x-1].
equation:
dp[x] = dp[j] && A[j] >= x - j
dp[0] = true
*/
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        boolean[] dp = new boolean[n];
        
        dp[0] = true;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] >= (i - j)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n - 1];
    }
}

/*
Same solution as above.
Thinking Process:
We have array A, that stores the # of steps for each index.
State: dp[i] means if previous steps can reach to i. True/False
Function: dp[i] = dp[j] && (j + A[j] >= i)
Init: dp[0] = true
Answer: dp[n-1], if n is the length of A
 */

/*

Greedy. Ideas from Yu’s Garden
At each index, check how far we can jump, store this forest-can-jump position in variable ‘farest’. 
Take max of current farest and (index + A[index]), store is in farest

At each index, compare if ‘farest’ is greater than the end of array, if so, found solution, return true.

At each index, also check if ‘farest == current index’, 
that means the farest we can move is to current index and we cannot move forward. 
Then return false.
*/

/*
Thoughts:
Greedy.
Find a farest position at current position. 
If farest <= i, that means it can't jump forward, false.
*/
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            farest = Math.max(farest, i + nums[i]);
            if (farest >= nums.length - 1) {
                return true;
            }
            if (farest <= i) {
                return false;
            }
        }
        return true;
    }
}


```