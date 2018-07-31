M
1533052458
tags: Divide and Conquer, Merge Sort, Binary Search Tree, Segment Tree, Binary Indexed Tree

给一串数字, count total reverse pair `nums[i] > 2*nums[j]`, i < j

This problem can be solved with Merge sort concept, BST, Segment Tree and Binary Indexed Tree. Good for learning/review.

#### Merge Sort
- Using merge sort concept, not exaclty merge sort implementation.
- One very simply concept: if we want to know how many elements between [i, j] are meeting requirements of `nums[i] > 2*nums[j]`, it would be really helpful, if the entire range is sorted:
- then we just need to keep one i index, and keep j++ for all elements meeting requirement `j<=e && nums[i]/2.0 > nums[j]`
- Then it comes to the sorting part: we cannot just directly sort entire array, because the restriction is `all elements on right side of curr element`. BUT, it is okay to sort `right side range` and compare with left side elements : )
- 灵感: use merge sort concept, divide and conquer:
- divide the elements from mid, compare each subarray
- sort once sub-array is completed (so that it can be used recursively at parent level)
- use classic while loop `while(j<=e && nums[i]/2.0 > nums[j])` to count pairs


#### Segment tree
- TODO
- split the array into index-based segment tree, where each element is at leaf
- store min of range: use max to determine if certain range is needed for further query
- query for each element right side range (i + 1, end), where it recursively query&aggregate sub-range if meeting requirement `nums[i] > 2*nums[j]`
- only when target > subRange.min * 2: there are possible candidates, query further
- worst case O(n^2) when all tailing elements are meeting requirement.

#### BST
- TODO
- Build the BST based on node value. It will be not applicable if we search after entire tree is built (our goal is right range), so we need to build right elements, and search/count right after the elements is added
- Worst case is still O(n^2), if all added nodes are meeting requirement 
- search(tree, curr / 2.0)



#### O(n^2)
- check each one of them


```
/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

*/
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    private int mergeSort(int[] nums, int start, int end) {
        // check end point
        if (start >= end) return 0;
        
        // divide and conquer
        int mid = start + (end - start) / 2;       
        int count = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        // count pair
        for (int i = start, j = mid + 1; i <= mid; i++) {
            while (j <= end && nums[i] / 2.0 > nums[j]) j++;
            count += j - (mid + 1);
        }
        
        Arrays.sort(nums, start, end + 1);
        
        return count;
    }
}
```