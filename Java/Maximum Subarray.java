E

方法1
比较像DP, 维持一个sums[i]: 从i向前位数, 所有正数的和. 一旦sums[i - 1]<0, 意味着sums[i-1]对maxSum没有好处,
那么就assign: sums[i]=nums[i]
这个做法比较中规中矩, makes sense

方法2(better)
想着用一用prefix sum. 把值一个个叠加。
然后presum[j] - presum[i- 1] 就是 (i,j)之间的和。

```
/*
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
    Thoughts: 11.23.2015
    Originally, the method is:When sum[i-1], it actaully starts from nums.get(i) again. 
    Calculate the max
    New way of using preSum.
    sum[i ~ j] = preSum[j] - preSum[i - 1];
    Calculate the max

    Though this helps further problems, but it's actaully O(n^2)
*/


public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        int max = Math.max(Integer.MIN_VALUE, preSum[0]);
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i <= j; i++) {
                int sum = 0;
                if (i == 0) {
                    sum = preSum[j];
                } else {
                    sum = preSum[j] - preSum[i - 1];
                } 
                max = Math.max(sum, max);
            }
        }//end for
        return max;
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
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = 0;
        int minPreSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            max = Math.max(max, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return max;
    }
}

```