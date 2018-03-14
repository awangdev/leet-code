M
1520924985
tags: Array, Two Pointers, Binary Search

- 注意不要思维定式: 以为mid是index
- 这里mid其实是binary search on value [1, n] 的一个value.
- 再次用到validate() function

Time: O(nLogN)

```
/*
Given an array nums containing n + 1 integers,
where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist.
Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/


/*
Thoughts:
1. Cannot change array: can't sort. The array is unsorted.
2. Limited space
3. time < O(n^2), which should be O(nLogN)

n+1 numbers, each num is in [1,n] => the extra 1 number is the duplicate.
If we count # of numbers that's <= numCandidate, the unique ones should always have count <= numCandidate
However, the duplicate will have: count >= numCandidate!

Use this validate() function to binary search on number [1 ~ n].
In validate function, loop over nums to count.
*/
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int start = 1; // as given, the value is in [1, n]
        int end = n;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (validate(mid, nums)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        return validate(start, nums) ? end : start;
    }
    
    private boolean validate(int candidate, int[] nums) {
        int count = 0;
        for (int num: nums) {
            count += num <= candidate ? 1 : 0;
        }
        return count <= candidate;
    }
}
```