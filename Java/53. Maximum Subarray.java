E
tags: DP, Sequence DP, Array, Divide and Conquer, DFS, PreSum, Subarray
time: O(n)
space: O(n), O(1) rolling array

给一串数组, unsorted, can have negative/positive num. 找数组中间 subarray 数字之和的最大值

#### PreSum
- 想着用一用prefix sum. 把值一个个叠加
- 然后presum[j] - presum[i- 1] 就是 (i,j)之间的和
- O(n^2), not as sufficient


#### Sequence DP
- dp[i]: last element(或包括前i个element), 可能组成的 subarray 的最大sum.
    - dp[i] = Math.max(dp[i-1]+lastElement, lastElement(drop dp[i-1]))
- init: 
    - dp = int[n + 1], 
    - dp[0]: first 0 items, does not have any sum
- 因为continous sequence, 所以不满足条件的时候, 会断. 
    - need to take curr num regardless => can drop prev max in dp[i]
- track overall max 
- init dp[0] = 0; max = MIN_VALUE 因为有负数
- Time, space O(n)
- Rolling array, space O(1)

#### Divide and Conquer, DFS
- 找一个mid piont, 考虑3种情况: 1) 只要左边, 2) 只要右边, 3) cross-mid
- left/rigth case: 直接 dfs
- corss-mid case: continuous sum max from left + continous sum max from right + mid
- continuous sum max from one direction:
- Worst case O(n^2): visit all nodes O(n); in dfs: calculates continuous sum (including mid), which is also O(n)
/*
    // handle cross-mid case
    int tempSum = 0, continuousLeftSumMax = 0;
    // find continuous max going towards left
    for (int i = mid - 1; i >= left; i--) {
        // always continous summing up
        tempSum += nums[i]; 
        // from one direction, take the best
        continuousLeftSumMax = Math.max(continuousLeftSumMax, tempSum); 
    }
*/


```
/**
LeetCode:
Given an integer array nums, find the contiguous subarray (containing at least one number) 
which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, 
try coding another solution using the divide and conquer approach, 
which is more subtle.

 */

// Despte the detailed dp[] solution, we have the light version:
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int preMaxSum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            preMaxSum = Math.max(num, preMaxSum + num);
            max = Math.max(max, preMaxSum);
        }
        return max;
    }
}

/*
    Use regular preSum - smallest preSum in earlier spots, which gives largest value.
    So maintain a regular preSum, and maintain a minPreSum. 
    Maintain a max to mark the difference between (preSum - minPreSum)
    Also, here, we dont' really care about index.So just skip index.
    O(n) here
*/
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int preSum = 0, minPreSum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            preSum += num;
            max = Math.max(max, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return max;
    }
}

/*
Thoughts:
sequence dp
continous subarray: cannot skip element
dp[i]: for first i items, what's the largest sum that containts nums[i]?
dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1])

record max globally

dp[i]: 0 items, max = 0
*/
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] dp = new int[n + 1]; // extra space to store dp[n]
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // continuous so always will use nums[i-1]
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
// Rolling array:
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[2];
        dp[0] = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i % 2] = Math.max(dp[(i - 1) % 2] + nums[i - 1], nums[i - 1]);
            max = Math.max(max, dp[i % 2]);
        }
        
        return max;
    }
}


/**
    Slight diff: checking dp[i-1] >= 0.
    Same as Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
 */
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // init dp, global max
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i - 1] + (dp[i - 1] >= 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
// Rolling array
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] dp = new int[2];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i % 2] = nums[i - 1] + (dp[(i - 1) % 2] >= 0 ? dp[(i - 1) % 2] : 0);
            max = Math.max(max, dp[i % 2]);
        }
        return max;
    }
}


/*
DFS, divide and conquer,
3 conditions: left of mid, right of mid, or cross-mid
use dfs to calculate left case, right case
carefully handle cross-mid case
*/

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return dfs(nums, 0, nums.length - 1, Integer.MIN_VALUE);
    }

    private int dfs(int[] nums, int left, int right, int sum) {
        if (left > right) return Integer.MIN_VALUE;

        // dfs on left, right range. Mid point is skipped
        int mid = left + (right - left) / 2;
        int leftMax = dfs(nums, left, mid - 1, sum);
        int rightMax = dfs(nums, mid + 1, right, sum);
        int maxSum = Math.max(sum, Math.max(leftMax, rightMax));

        // handle cross-mid case
        // find continuous max going towards left
        int continuousLeftSumMax = findContinuousSum(mid-1, left, right, -1, nums);
        // find continuous max going towards right
        int continuousRightSumMax = findContinuousSum(mid+1, left, right, 1, nums);
        maxSum = Math.max(maxSum, nums[mid] + continuousLeftSumMax + continuousRightSumMax);
        return maxSum;
    }
    
    // set up left/right bound, and use offset to customize for loop: avoid redundant code
    private int findContinuousSum(int start, int left, int right, int offset, int[] nums) {
        int continuousSum = 0, max = 0;
        for (int i = start; i >= left && i <= right; i+=offset) {
            continuousSum += nums[i];
            max = Math.max(max, continuousSum);
        }
        return max;
    }
}



/*
LintCode
Maximum Subarray Show Result My Submissions

35% Accepted
Given an array of integers, find a contiguous subarray which has the largest sum.

Note
The subarray should contain at least one number

Example
For example, given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Challenge
Can you do it in time complexity O(n)?

Tags Expand 
Array SubArray Greedy Enumeration LintCode Copyright


*/
/*
Thoughts:
1. Move end see how far it can go which keeps sum increasing
2. sum[i] = sum[i - 1] + nums[i]. We need to decide if sum[i] will take sum[i-1] depending on if sum[i-1] is positive or negative.
3. maintain a maxSum
*/
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int maxSum = sums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (sums[i - 1] < 0) {// sums[i-1] only reduces maxSum, therefore skip it in sums[i]
                sums[i] = nums[i];
            } else {
                sums[i] = sums[i - 1] + nums[i];
            }
            maxSum = Math.max(maxSum, sums[i]);
        }
        return maxSum;
    }
}

/*
    Same as above, but with list as input
    Thinking proces:
    Store the sum in a array.
    Normally, sum[i] = sum[i - 1] + nums[i].
    However, if sum[i - 1] is a nagetive number, that means sum[i - 1] won't do any good for later sum but only decrease the sum. 
    In this case, when sums[i - 1] < 0, we don't add it.

    When sum[i-1], it actaully starts from nums.get(i) again.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] sums = new int[nums.size()];
        sums[0] = nums.get(0);
        int maxSum = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sums[i - 1] < 0) {
                sums[i] = nums.get(i);
            } else {
                sums[i] = sums[i - 1] + nums.get(i);
            }
             maxSum = Math.max(maxSum, sums[i]);
        }
        return maxSum;
    }
}

/*
    To further extend the prefix sum idea, we are really trying:
    Use regular preSum - smallest preSum in earlier spots, which gives largest value.
    So maintain a regular preSum, and maintain a minPreSum. 
    Maintain a max to mark the difference between (preSum - minPreSum)
    Also, here, we dont' really care about index.So just skip index.
    O(n) here
*/

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int preSum = 0, minPreSum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            preSum += num;
            max = Math.max(max, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return max;
    }
}

```