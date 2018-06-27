M
1522684902
tags: Array, Greedy, DP

给出步数，看能不能jump to end.

#### Greedy - start from index = 0
- Keep track of farest can go
- 一旦 farest >= nums.length - 1, 也就是到了头, 就可以停止, return true.
- 一旦 farest <= i, 也就是说, 在i点上, 已经走过了步数, 不能再往前跳, 于是 return false
- This can be done using DP. However, greedy algorithm is fast in this particular problem.

#### Greedy - start from index = n - 1
- greedy: start from end, and mark last index
- loop from i = [n - 2 -> 0], where i + nums[i] should always >= last index
- check if last == 0 when returning. It means: can we jump from index=0 to the end?
- Time: O(n), beat 100%

#### DP
- DP[i]: 在i点记录，i点之前的步数是否可以走到i点？ True of false.
- 其实j in [0~i)中间只需要一个能到达i 就好了
- Function: DP[i] = DP[j] && (A[j] >= i - j), for all j in [0 ~ i)
- Return: DP[dp.length - 1];
- Time: O(n^2)


```
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.


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

// even simpler, check from end. beat 100%
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int last = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last == 0;
    }
}

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



```