M
1531896299
tags: Divide and Conquer, Heap, Quick Sort

```
/**
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
sort O(nlogn). Better O(n)
use a min-heap with size k, so min item will always be at top to be removed O(logk)
Overall runtime O(nlogk)
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // min-heap
        
        for (int i = 0; i < nums.length; i++) { 
            if (i < k || nums[i] > queue.peek()) queue.offer(nums[i]);
            if (queue.size() > k) queue.poll();
        }
        
        return queue.poll();
    }
}
```