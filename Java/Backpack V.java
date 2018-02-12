M
1518416934

与背包1不同: 这里不是check可能性(OR)或者最多能装的size是多少; 而是计算有多少种正好fill的可能性.

对于末尾, 还是两种情况:
1. i-1位置没有加bag
2. i-1位置加了bag

两种情况可以fill满w的情况加起来, 就是我们要的结果.

如常: dp[n + 1][w + 1]

方法1:
Space: O(MN)
Time: O(MN)

方法2:
空间优化, 滚动数组
Space: O(M) * 2 = O(M)
Time: O(MN)

方法3:
降维打击, 终极优化: 分析row(i-1)的规律, 发现所有row(i)的值, 都跟row(i-1)的左边element相关, 而右边element是没用的.
所以可以被override.
Space: O(M), 真*一维啊!
Time: O(MN)

```
/*
Given n items with size nums[i] which an integer array and all positive numbers. 
An integer target denotes the size of a backpack. 
Find the number of possible ways of filling the backpack.

Each item may only be used once
*/


/*
Thoughts:
We want to know with i items, how many ways can we add up to equal target?
dp[i][w]: the # of ways to add up to w with i items.
track back to i - 1:
1. nums[i] was not picked : dp[i][w] = dp[i - 1][w]
2. nums[i] was picked: dp[i][w] = dp[i - 1][w - nums[i]];

initialization:
dp[0][0~w] = 0;
dp[0][0] = 1;

Space: O(MN)
Time: O(MN)
*/
public class Solution {
    public int backPackV(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1; // 0 items to form 0 weight
        for (int j = 1; j <= target; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for  (int j = 0; j <= target; j++) {// (int j = target; j >= 0; j--) works as well, it doesn't matter
                // Condition1:
                dp[i][j] = dp[i - 1][j];
                // Condition2:
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];    
                }
            }
        }
        return dp[n][target];
    }
}


// Improvement1: rolling array
// Space O(M)
public class Solution {
    public int backPackV(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[2][target + 1];
        dp[0][0] = 1; // 0 items to form 0 weight
        for (int j = 1; j <= target; j++) {
            dp[0][j] = 0;
        }
        int curr = 0, prev = 1;
        for (int i = 1; i <= n; i++) {
            prev = curr;
            curr = 1 - curr;
            for  (int j = 0; j <= target; j++) {// (int j = target; j >= 0; j--) works as well, it doesn't matter
                // Condition1:
                dp[curr][j] = dp[prev][j];
                // Condition2:
                if (j - nums[i - 1] >= 0) {
                    dp[curr][j] += dp[prev][j - nums[i - 1]];    
                }
            }
        }
        return dp[curr][target];
    }
}

/*
Improvement 降维
Inner loop of weight needs to iterate from j = M -> 0
We always use dp[i-1][w] or dp[i - 1][w - nums[i - 1]]: 
always using old values from last row right above at w index, or on the left side of the w index.

All indexes on right side of w is not needed. 

Therefore, we can reduce the dp[][] into 1-D array.
Note: j has to iterate from M -> 0, because we know on i - 1 row all indexes
on right side of  w on the right side can be overriden.

if j = 0 -> M, it will override useful indexes.

Space: O(n)
Time: O(MN)
*/

public class Solution {
    public int backPackV(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int [target + 1];
        dp[0] = 1; // 0 items to form 0 weight
        for (int j = 1; j <= target; j++) {
            dp[j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {// have to loop from M -> 0, for optimization
                // Condition1: dp[j] = dp[j];
                // Condition2:
                if (j - nums[i - 1] >= 0) {
                    dp[j] += dp[j - nums[i - 1]];    
                }
            }
            // further simplify
            //for (int j = target; j >= nums[i - 1]; j--) {
            //    dp[j] += dp[j - nums[i - 1]];
            //}
        }
        return dp[target];
    }
}

```