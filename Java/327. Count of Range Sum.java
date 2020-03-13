H
tags: Divide and Conquer, Merge Sort, BIT, PreSum, Segment Tree
time: O(nlogn)
space: O(n)

TODO: Write the code + merge function

#### Divide and Conquer + PreSum + MergeSort
- https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
- 1) build preSum[n+1]: then sum range [i,j]= preSum[j+1] - preSum[i]
- 2) Divide and Conquer: 
    - 先考虑[start, mid] range里的 ran sum result
    - 再考虑[mid, end] range里面的结果
    - 最后考虑[low, high]总体的结果
- NOTE: should write merge() function, but that is minor, just use `Arrays.sort(nums, start, end)`, OJ passed
- Every mergeSort() has a for loop => O(n log n)
- 如何 count range?
    - 这里比较特别的一个做法: 找一个 [low, mid]里面的i, mid 之后的preSum作比较 (解释源自: https://blog.csdn.net/qq508618087/article/details/51435944)
    - 即在右边数组找到两个边界, 设为`m, n`, 
    - 其中m是在右边数组中第一个使得`sum[m] - sum[i] >= lower`的位置, 
    - n是第一个使得`sum[n] - sum[i] > upper`的位置, 
    - 这样`n-m`就是与左边元素i所构成的位于`[lower, upper]`范围的区间个数. 

##### 神奇的重点: 为什么要 merge and sort
- 边界[lower, higher] 在 sorted array 好作比较, 一旦过界, 就可以停止计算, 减少不必要计算.
- 上面这个n,m的做法可行的前提: preSum[]里面前后两个 range[low, mid], [mid, high]已经sorted了
    - 也就是说, 在recursively mergeSort()的时候, 真的需要merge sorted 2 partitions
    - 也许会问: 能不能sort呢, sort不久打乱了顺序? 对,打乱的是preSum[]的顺序.
    - 但是不要紧: 很巧妙的, 分治的时候, 前半段/后半段 都在原顺序保留的情况下 分开process完了, 最后才merge
- 在做m,n 的range的时候, 原理如下, 比如preSum被分成这么两段: `[A,B,C]`, `[D,E,F]`
    - 每一个preSum value `A` 在跟 preSum[i] 作比较的时候 `A - preSum < lower`, 都是单一作比较, 不牵扯到 B, C
    - 因此, `[A, B, C]` 是否保留一开始 preSum的顺序在此时不重要
- 此时最重要的是, `[A,B,C]`以及排序好, 那么在于 `lower` boundary 作比较的时候, 一旦过界, 就可以停止计算(减少不必要的计算)


#### BIT
- TODO?

#### Segment Tree
- This segment tree approach(https://leetcode.com/problems/count-of-range-sum/discuss/77987/Java-SegmentTree-Solution-36ms) 
    - does not build segment tree based on given nums index
    - it is built on sorted preSum array.
- regular segment tree based on nums array does not work:
    - segment tree based on input array is good for: search/query by index
    - is NOT good at: given range sum/value, find indexes
    - why? segment tree is built based on index division, not by range value division.

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
        if (nums == null || nums.length <= 0) return 0;
        long[] preSum = calcPreSum(nums);

        return mergeSort(preSum, lower, upper, 0, preSum.length);
    }

    private int mergeSort(long[] preSum, int lower, int upper, int start, int end) {
        if (start + 1 >= end) return 0;
        int mid = (start + end) / 2, count = 0;
        
        // sort two sides
        count += mergeSort(preSum, lower, upper, start, mid);
        count += mergeSort(preSum, lower, upper, mid, end);
        
        // find the lower/upper bound index range. we know range[start, mid] and [mid, end] has been sorted earlier
        int lo = mid, hi = mid; 
        for (int i = start; i < mid; i++) {
            while (lo < end && preSum[lo] - preSum[i] < lower) lo++;
            while (hi < end && preSum[hi] - preSum[i] <= upper) hi++;
            count += hi - lo;
        }

        // merge two list for range [start, end]
        Arrays.sort(preSum, start, end);
        //merge(preSum, start, mid - 1, end - 1); SHOULD use a merge function
        return count;
    }
}



// Same impl, but wtih a customized merge function. Reference. [tool].MergeSort.java
class Solution {
    long[] cache;
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length <= 0) return 0;
        cache = new long[nums.length + 1];
        long[] preSum = calcPreSum(nums);
        

        return mergeSort(preSum, lower, upper, 0, preSum.length);
    }

    private int mergeSort(long[] preSum, int lower, int upper, int start, int end) {
        if (start + 1 >= end) return 0;
        int mid = (start + end) / 2, count = 0;
        
        // sort two sides
        count += mergeSort(preSum, lower, upper, start, mid);
        count += mergeSort(preSum, lower, upper, mid, end);
        
        // find the lower/upper bound index range. we know range[start, mid] and [mid, end] has been sorted earlier
        int lo = mid, hi = mid; 
        for (int i = start; i < mid; i++) {
            while (lo < end && preSum[lo] - preSum[i] < lower) lo++;
            while (hi < end && preSum[hi] - preSum[i] <= upper) hi++;
            count += hi - lo;
        }

        // merge two list for range [start, end)
        merge(preSum, mid - 1, start, end - 1); //SHOULD use a merge function
        return count;
    }

    private long[] calcPreSum(int[] nums) {
        long[] preSum = new long[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        return preSum;
    }

    private void merge(long[] nums, int mid, int start, int end) {
        int i = start, j = mid + 1, index = start;
        
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) cache[index++] = nums[i++];
            else cache[index++] = nums[j++];
        }
        
        // append the remaining array.
        // arraycopy: copy from array[i] to cache[index] for (x) items
        System.arraycopy(nums, i, cache, index, mid - i + 1); // copy remaining of left segment (in case it didn't reach end)
        System.arraycopy(nums, j, cache, index, end - j + 1); // copy remaining of right segment (in case it didn't reach end)
        System.arraycopy(cache, start, nums, start, end - start + 1); // copy whole cache[start,end] to original
    }
}
```