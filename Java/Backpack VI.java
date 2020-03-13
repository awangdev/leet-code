M
1518420788
tags: DP, Backpack DP

给一个数组nums, 全正数, 无重复数字; 找: # of 拼出m的方法.

nums 里的数字, 可以重复使用. 不同的order可以算作不同的拼法.

#### Backpack DP
- dp[i] 表示: # of ways to fill weight i
- 1维: dp[w]: fill weigth w 有多少种方法. 前面有多少种可能性, 就sum多少个:
- dp[w] = sum{dp[w - nums[i]]}, i = 0~n

##### 分析
- 拼背包时, 可以有重复item, 所以考虑'最后被放入的哪个unique item' 就没有意义了.
- 背包问题, 永远和weight分不开关系.
- 这里很像coin chagne: 考虑最后被放入的东西的value/weigth, 而不考虑是哪个.




```
/*
Given an integer array nums with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Notice
A number in the array can be used multiple times in the combination. 
Different orders are counted as different combinations.
*/

/*
Thoughts:
All backpack problems should have a status of weight. However, now the items can be reused, so there is no
unique last item. We can't do [w - nums[i]].
Instead of banking on last unique item, we should consider what's the last weight being added?
Very similar to Coin Change.

dp[w] = how many combinations to form weight w? 
Cound all ways:
dp[w] = dp[w - nums[0]] + dp[w - nums[1]] + dp[w - nums[2]] + .... dp[w - nums[ n - 1]]
*/
public class Solution {
    public int backPackVI(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1; // fill 0 weight, always possible.
        
        for (int i = 1; i <= target; i++) { // calc for all weights
            for (int j = 0; j < n; j++) { // iterate over all nums
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        
        return dp[target];
    }
}
```