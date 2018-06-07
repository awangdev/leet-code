H
1528263371
tags: Array, Binary Search, Divide and Conquer, DFS

著名的找两个sorted array的中位数. 中位数定义: 如果两个array总长为偶数, 取平均值.
题目要求在 log(m + n) 时间内解决

- 看到log(m+n), 就想到binary search, 或者是recursive 每次砍一半
- 两个sorted array 参差不齐, 肯定不能做简单的binary search

#### Divide and Conquer, recursive
- 这里有个数学排除思想: 考虑A, B各自的中间点.
- 如果A[mid] < B[mid], 那么 A[0 ~ mid - 1] 就不在 median的range里面, 可以排除. divide/conquer就这么来的.
- 具体逻辑看代码, 大致意思就是: 每次都取比较A 和 B [x + k / 2 - 1] 的位置, 然后做range 排除法
- end cases: 
- 1. 如果我们发现dfs()里面A或者B的start index溢出了, 那么就是最简单的case: midian一定在另外那个array里面
- 2. 如果 k == 1: 就是找A/B 里面的1st item, 那么做个 `Math.max(A[startA], B[startB])` 就可以
- 总共的数字长度是 (m + n) 而且每次都有一般的内容被删除, 那么time就是 O(log(m + n))

#### Binary Search
TODO:

```
/*

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5

LintCode examples:
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
Given A=[1,2,3] and B=[4,5], the median is 3.

*/


/*
    Thoughts:
    Trivial: merge and find median. NOPE: have to be in log(m+n) time
    http://www.jiuzhang.com/solutions/median-of-two-sorted-arrays/

    http://articles.leetcode.com/find-k-th-smallest-element-in-union-of
    
    Good one: http://blog.csdn.net/yutianzuijin/article/details/11499917
    http://blog.csdn.net/zxzxy1988/article/details/8587244

*/

/*
Thoughts:
A[1,2]
B[3,4,5]
Assume after merge: [1,2,3,4,5], we'll be looking for the item in the mid.
Math: consider A[mid/2] and B[mid/2]. If A[mid/2]<B[mid/2], then it's safe to cut off all items before mid/2 = n/4; they won't be in range for the median.
Same applies to when A[mid/2]>B[mid/2]

Approach: find kth number of two sorted array, k ~= (m+n)/2
- use startA, startB indexes to track the partition location
- Cut off half of one list at a time, recursively process the rest 3/4 of overall content.
- when k = 1, since A/B lists are sorted, should return the min
- Be careful with index: it's all 0 based
*/
class Solution {
    public double findMedianSortedArrays(int[] numsA, int[] numsB) {
        // Assume edge case is safe
        int n = numsA.length + numsB.length;

        // Handle even/odd cases
        if (n % 2 == 0) {
            return (
                findKth(numsA, 0, numsB, 0, n / 2) + 
                findKth(numsA, 0, numsB, 0, n / 2 + 1)
            ) / 2.0;
        }
        return (double)findKth(numsA, 0, numsB, 0, n / 2 + 1);
    }

    // Find kth number in two sorted array. k is size
    private int findKth(int[] numsA, int startA, int[] numsB, int startB, int k) {
        // check edge case for startA/startB index
        if (startA >= numsA.length) {
            return numsB[startB + k - 1];
        }
        if (startB >= numsB.length) {
            return numsA[startA + k - 1];
        }

        // handle final condition k == 1
        if (k == 1) {
            return Math.min(numsA[startA], numsB[startB]);
        }

        // compare and partition at each [x+(k/2-1)] position
        int halfKthOfA = startA + k / 2 - 1 < numsA.length ? numsA[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthOfB = startB + k / 2 - 1 < numsB.length ? numsB[startB + k / 2 - 1] : Integer.MAX_VALUE;
        if (halfKthOfA < halfKthOfB) {
            return findKth(numsA, startA + k / 2, numsB, startB, k - k / 2);
        } else {
            return findKth(numsA, startA, numsB, startB + k / 2, k - k / 2);
        }
    }
}

```