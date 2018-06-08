H
1528434667
tags: Divide and Conquer, Merge Sort, BST, PreSum

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- 算法非常厉害就是了: 先做presum[], 那么 sum range [i,j] 就等于是preSum[j+1] - preSum[i]
- 分治: 考虑[start, mid] range里面的结果, 再考虑[mid, end] range里面的结果. (分开来 mergeSort)
- 最后考虑[low,high]总体的结果
- 小技巧: PreSum 做成了 (n + 1) length, 那么求range sum [i,j] 就可以简化成 preSum[j] - preSum[i]
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)

##### 如何 count range?
- 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
- 即在右边数组找到两个边界, 设为`m, n`, 
- 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
- n是第一个使得`sum[n] - sum[i] > upper`的位置, 
- 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦国界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
- 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
- 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
- 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
- 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
- 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BST
- TODO?

```
/*
Given an integer array nums, return the number of range sums 
that lie in [lower, upper] inclusive.

Range sum S(i, j) is defined as the sum of the elements in nums 
between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
*/

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // edge case
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        // mergeSort()   
        long[] preSum = calcPreSum(nums);

        return mergeSort(preSum, lower, upper, 0, preSum.length);
    }

    private int mergeSort(long[] preSum, int lower, int upper, int start, int end) {
        if (start + 1 >= end) {
            return 0;
        }
        int mid = (start + end) / 2, m = mid, n = mid, count = 0;
        
        // sort two sides
        count += mergeSort(preSum, lower, upper, start, mid);
        count += mergeSort(preSum, lower, upper, mid, end);
        
        // calculate count in range [m, n]
        for (int i = start; i < mid; i++) {
            while (m < end && preSum[m] - preSum[i] < lower) {
                m++;
            }
            while (n < end && preSum[n] - preSum[i] <= upper) {
                n++;
            }
            count += n - m;
        }

        // merge two list for range [start, end]
        Arrays.sort(preSum, start, end);
        //merge(preSum, start, mid - 1, end - 1); SHOULD use a merge function
        return count;
    }

    private long[] calcPreSum(int[] nums) {
        long[] preSum = new long[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        return preSum;
    }
}



```