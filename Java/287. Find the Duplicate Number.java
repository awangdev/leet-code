M
tags: Array, Two Pointers, Binary Search, Binary Search on Value, Slow Fast Pointer, Cycle Detection
time: O(n)
space: O(1)

#### Method1: Slow Fast Pointer
- Use LinkedList Cycle Concept:
    - Each element the array is like a `Node {int currIndex; int val;}`, where the `val` is also pointer to next Node
    - A node is like a portal; a pointer can: 1) visit a node by currIndex, 2) pick up newIndex = `nums[currIndex]`, then keep repeating step 1 and 2.
    - Important: since nums is immutable, the pointer footprint is unique/linear
    - Just like linked list. Therefore, use slow/fast pointer to detect cycle.
- https://leetcode.com/problems/find-the-duplicate-number/solution/
- it is now the same as `142. Linked List Cycle II`

#### Method2: Binary Search on value
- 注意不要思维定式: binary search `NOT on index`
    - `binary search on value`: [1, n]
    - O(logN)
- validate(nums, candidate): for loop to count number of `value <= candidate`
    - `count == candidate`: no duplicate from [1 ~ candidate]. 
    - `count < candidate`: missing element in [1~ candidate], so duplicates are in later range. start = mid;
    - `count > candidate`: there are duplicates in [1~ candidate]. end = mid;
- Time: O(nLogN)
- Space: O(1)

```
/*

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/


/*
Method1: Use LinkedList Concept, utilize the cycle
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]]; // starts slow=nums[0], because there is no 0 value.
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int finder = 0;
        while (slow != finder) {
            slow = nums[slow];
            finder = nums[finder];
        }
        return slow;
    }
}


/*
#### Method2: Binary Search on value
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
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length, start = 1, end = n; // as given, the [start, end] is in [1, n]
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (validate(mid, nums)) start = mid;
            else end = mid;
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