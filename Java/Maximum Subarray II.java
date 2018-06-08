M
1525363049
tags: Greedy, Array, DP, Sequence DP, PreSum

给一串数组, 找数组中间 两个不交互的 subarray 数字之和的最大值

#### DP
- 考虑两个方向的dp[i]: 包括i在内的subarray max sum. 
- dp[i] 的特点是: 如果上一个 dp[i - 1] + nums[i - 1] 小于 nums[i-1], 那么就舍弃之前, 从头再来:
- dp[i] = Math.max(dp[i - 1] + nums.get(i - 1), nums.get(i - 1));
- 缺点: 无法track全局max, 需要记录max.
- 因为我们现在要考虑从左边/右边来的所有max, 所以要记录maxLeft[] 和 maxRight[] 
- maxLeft[i]: 前i个元素的最大sum是多少 (不断递增); maxRight反之, 从右边向左边
- 最后比较maxLeft[i] + maxRight[i] 最大值
- Space, Time O(n)
- Rolling array, reduce some space, but can not reduce maxLeft/maxRight

#### preSum, minPreSum
- preSum是[0, i] 每个数字一次加起来的值
- 如果维持一个minPreSum, 就是记录[0, i]sum的最小值(因为有可能有负数)
- preSum - minPreSum 就是在 [0, i]里, subarray的最大sum值
- 把这个最大subarray sum 记录在array, left[] 里面
- right[] 是一样的道理
- enumerate一下元素的排列顺位, 最后 max = Math.max(max, left[i] + right[i + 1])

```
/*
LintCode: https://lintcode.com/en/problem/maximum-subarray-ii/#
Given an array of integers, find two non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Note
The subarray should contain at least one number

Example
For given [1, 3, -1, 2, -1, 2], 
the two subarrays are [1, 3] and [2, -1, 2] 
or [1, 3, -1, 2] and [2], they both have the largest sum 7.

Challenge
Can you do it in time complexity O(n) ?

Tags Expand 
Greedy Enumeration Array LintCode Copyright SubArray Forward-Backward Traversal

*/

/*
Thoughts:
Similar to Maximum Subarray, from one side:
dp[i]: for first i items, the max subarray sum containing nums[i - 1]

Should process the dp from both left and right side, 
with index i being the division point

Note that we need to track the max for left and right, 
so we need maxLeft[], maxRight[]
*/
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int n = nums.size();
        int[] dpLeft = new int[n + 1];
        int[] dpRight = new int[n + 1];
        dpLeft[0] = 0;
        dpRight[n] = 0;

        int[] maxLeft = new int[n + 1];;
        int[] maxRight = new int[n + 1];
        maxLeft[0] = Integer.MIN_VALUE;
        maxRight[n] = Integer.MIN_VALUE;
        
        // Left
        for (int i = 1; i <= n; i++) {
            dpLeft[i] = Math.max(dpLeft[i - 1] + nums.get(i - 1), nums.get(i - 1));
            maxLeft[i] = Math.max(maxLeft[i - 1], dpLeft[i]);
        }

        // Right
        for (int j = n - 1; j >= 0; j--) {
            dpRight[j] = Math.max(dpRight[j + 1] + nums.get(j), nums.get(j));
            maxRight[j] = Math.max(maxRight[j + 1], dpRight[j]);
        }
        
        // Combine
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, maxLeft[i] + maxRight[i]);
        }
        
        return max;
    }
}


// Rolling array
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int n = nums.size();
        int[] dpLeft = new int[2];
        int[] dpRight = new int[2];
        dpLeft[0] = 0;
        dpRight[n % 2] = 0;

        int[] maxLeft = new int[n + 1];
        int[] maxRight = new int[n + 1];
        maxLeft[0] = Integer.MIN_VALUE;
        maxRight[n] = Integer.MIN_VALUE;
        
        // Left
        for (int i = 1; i <= n; i++) {
            dpLeft[i % 2] = Math.max(dpLeft[(i - 1) % 2] + nums.get(i - 1), nums.get(i - 1));
            maxLeft[i] = Math.max(maxLeft[i - 1], dpLeft[i % 2]);
        }

        // Right
        for (int j = n - 1; j >= 0; j--) {
            dpRight[j % 2] = Math.max(dpRight[(j + 1) % 2] + nums.get(j), nums.get(j));
            maxRight[j] = Math.max(maxRight[j + 1], dpRight[j % 2]);
        }
        
        // Combine
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, maxLeft[i] + maxRight[i]);
        }
        
        return max;
    }
}

// Futher simplify: 
// use 1 for loop for both left and right
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int n = nums.size();
        int[] dpLeft = new int[2];
        int[] dpRight = new int[2];
        dpLeft[0] = 0;
        dpRight[n % 2] = 0;

        int[] maxLeft = new int[n + 1];;
        int[] maxRight = new int[n + 1];
        maxLeft[0] = Integer.MIN_VALUE;
        maxRight[n] = Integer.MIN_VALUE;
        
        
        for (int i = 1; i <= n; i++) {
            // Left
            dpLeft[i % 2] = Math.max(dpLeft[(i - 1) % 2] + nums.get(i - 1), nums.get(i - 1));
            maxLeft[i] = Math.max(maxLeft[i - 1], dpLeft[i % 2]);
            
            // Right
            int j = n - i;
            dpRight[j % 2] = Math.max(dpRight[(j + 1) % 2] + nums.get(j), nums.get(j));
            maxRight[j] = Math.max(maxRight[j + 1], dpRight[j % 2]);
        }
        
        // Combine
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, maxLeft[i] + maxRight[i]);
        }
        
        return max;
    }
}


/*
    Thoughts： 11.23.2015
    Similar to Maximum Subarray。 Now just try to build 2 maximum subbary, from left/right.
    Meetpoint i, will give largest possible sum
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int preSum = 0;
        int minPreSum = 0;
        int max = Integer.MIN_VALUE;
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            preSum += nums.get(i);
            max = Math.max(max, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
            left[i] = max;
        }
        preSum = 0;
        minPreSum = 0;
        max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            preSum += nums.get(i);
            max = Math.max(max, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
            right[i] = max;
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int rst = left[i] + right[i + 1];
            max = Math.max(max, rst);
        }

        return max;
    }
}





/*

Thinking process:
Find frontSum: largest sum from index 0 till current at each index.
Find endSum: largest sum from end(endSum.length - 1) to current at each index.
Add them up: at any point i, leftSum + rightSum = largest 2 non-overlap sum.
            i
        i       i
    i               i
i                       i

*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int[] frontSum = new int[nums.size()];
        int[] endSum = new int[nums.size()];
        int maxSum = 0;
        frontSum[0] = nums.get(0);
        //Init frontSum
        for (int i = 1; i < frontSum.length; i++) {
            if (frontSum[i - 1] < 0) {
                frontSum[i] = nums.get(i);
            } else {
                frontSum[i] = frontSum[i - 1] + nums.get(i);
            }
        }
        maxSum = frontSum[0];
        //Find max
        for (int i = 1; i < frontSum.length; i++) {
            if (frontSum[i] < maxSum) {
                frontSum[i] = maxSum;
            } else {
                maxSum = frontSum[i];
            }
        }
        
        //Init endSum
        endSum[endSum.length - 1] = nums.get(nums.size() - 1);
        for (int i = endSum.length - 2; i >= 0; i--) {
            if (endSum[i + 1] < 0) {
                endSum[i] = nums.get(i);
            } else {
                endSum[i] = endSum[i + 1] + nums.get(i);
            }
        }
        //Find max
        maxSum = endSum[endSum.length - 1];
        for (int i = endSum.length - 2; i >= 0; i--) {
            if (endSum[i] < maxSum) {
                endSum[i] = maxSum;
            } else {
                maxSum = endSum[i];
            }
        }
        //Calculate max Sum
        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            maxSum = Math.max(maxSum, frontSum[i] + endSum[i + 1]);
        }
        return maxSum;
    }
}





```