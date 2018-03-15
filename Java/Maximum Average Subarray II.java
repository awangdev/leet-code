R
1521096472
tags: Array, Binary Search

给int[] nums 和 window min size k. window size可以大于K. 找最大的连续数列average value.

- Binary Search的思想, 用在所要找的这个 average sum 上面. 大小是在[min, max]之中
- 找k的时候, 是>=k都可以, 巧用一个 min(preSum)的概念.
- 找k的时候, 画图, 可以看出来, 其实要的是 k window 里面的sum [x, i], 所以要用 sum[0, i] - sum[0, x]

需要仔细去读下面的notes.

```

/*
Given an array consisting of n integers, find the contiguous subarray
whose length is greater than or equal to k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.

*/

/*
Thoughts:
If trying brutly, it'll be O(n^2). How about O(nLogN)?

Try binary serach on the final result: the average number, which will range from [min, max] of nums.
Should recognize that when seeing '1e-15'
http://bookshadow.com/weblog/2017/07/16/leetcode-maximum-average-subarray-ii/

Key idea:
0. start=mid, end=max.
1. Give the midAvg[min, max] as the arbitrary avg, see if we can find possible k [k ~ n], that gives average sum >= midAvg
2. If it does exist, maybe we can find larger midAvg? so set start=midAvg
3. If not exist, we should set end=midAvg

Problem:
How to effectively check we can beat given midAvg? Simply sum up k items => sum, then check sum/k >= midAvg.
Well, a bit more to it:
1. If using nums[0, k], it's easy: sum[0, k] / k will be it.
2. If using [x, i], where i - x = k. The sum will be sum[0, i] - preSum[0, x].
   Here is the core triky part: we don't have to try O(n^2) here to check all possible k in [k ~ n]
   Rather, we record preSum as the minimal value of all possible preSum as k moves.
   `sum[x, i] = sum[0 ~ i] - min(preSum's) >= 0` basically tells: for all possible k's, is there a split where preSum is smallest and makes the sum[x, i] greater than 0?
   That's the beauty to the problem: no need to calculate all possible k's: just need 1 valid solution, and we don't care which k that will be.
*/
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        
        double start = min;
        double end = max;
        double midAvg = max;
        while (end - start > 1e-5) {
            midAvg = start + (end - start) / 2.0;
            if (check(nums, k, midAvg)) {
                start = midAvg;
            } else {
                end = midAvg;
            }
        }
        return midAvg;
    }
    
    /**
        Return true, if there is a window with size >= k,
        such that the average sum will >= given midAvg.
     */
    private boolean check(int[] nums, int k, double midAvg) {
        double preSum = 0;
        double sum = 0;
        double minPreSum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i] - midAvg;
        }
        if (sum >= 0) {
            return true;
        }
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - midAvg;
            preSum += nums[i - k] - midAvg;
            minPreSum = Math.min(preSum, minPreSum);
            if (sum >= minPreSum) {
                return true;
            }
        }
        return false;
    }
}


```