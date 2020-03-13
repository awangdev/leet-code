E
tags: Stack, Hash Table
time: O(n)
space: O(n)

#### stack
- use stack to hold all elements
    - keep poping if `stack.peek() < num`
    - use map to record (top, num)
- time O(n), run through base once and sub-sequence once
- space O(n), stack, map

#### HashMap
- O(n) space, O(n^2) time worst case

```
/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/
 
// stack
class Solution {
    public int[] nextGreaterElement(int[] sub, int[] base) {
        Map<Integer, Integer> baseMap = generateSubMap(base);
        for (int i = 0; i < sub.length; i++) {
            sub[i] = baseMap.getOrDefault(sub[i], -1);
        }
        return sub;
    }
    
    private Map<Integer, Integer> generateSubMap(int[] base) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> baseMap = new HashMap<>();
        for (int num : base) {
            while (!stack.isEmpty() && stack.peek() < num) {
                int pop = stack.pop();
                baseMap.put(pop, num);
            }
            stack.push(num);
        }
        return baseMap;
    }
}

// O(n^2)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[m];
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(nums2[i], i);
        }
        for (int i = 0; i < m; i++) {
            result[i] = check(nums2, pos.get(nums1[i]), nums1[i]);
        }
        return result;
    }
    
    private int check(int[] nums, int index, int val) {
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > val) return nums[i];
        }
        return -1;
    }
}
```