M
tags: Binary Search
time: O(logn)
space: O(1)

#### Binary Search
- total missing nums = nums[curr] - nums[0] - curr
- edge case: if k > total missing nums, then just add the diff from nums[end]
- otherwise, find this `missing count == k` in the nums using binary search
- After binary search: `start + 1 == end`: 
    - re-calculate `count = nums[start] - nums[0] - start;`
    - output final num: `nums[start] + k - count;`
- Option1: always compare total missing nums count
- Option2: compare partial missing nums count (inspired by: https://leetcode.com/problems/missing-element-in-sorted-array/discuss/303444/Java-O(logN)-solution-Binary-Search)


```
/*

Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

 

Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation: 
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation: 
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation: 
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 

Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8

*/
// Binary Search, Option1: always compare total missing nums
class Solution {
    public int missingElement(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        int count = nums[end] - nums[0] - end;
        if (count < k) {
            return nums[end] + k - count;
        }
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            count = nums[mid] - nums[0] - mid;
            if (count >= k) end = mid;
            else start = mid;
        }
        count = nums[start] - nums[0] - start;
        return nums[start] + k - count;
    }
}

// Binary Search, Option1: always compare pairtial missing nums in range [mid, end]
class Solution {
    public int missingElement(int[] nums, int k) {
        
        int start = 0, end = nums.length - 1;
        int count = nums[end] - nums[0] - end;
        if (count < k) {
            return nums[end] + k - count;
        }
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            count = nums[mid] - nums[start] - (mid - start);
            if (count >= k) {
                end = mid;
            } else {
                start = mid;
                k -= count;
            }
        }
        return nums[start] + k;
    }
}
```