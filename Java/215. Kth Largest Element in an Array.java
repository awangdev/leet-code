M
tags: Divide and Conquer, Heap, PriorityQueue, MinHeap, Quick Sort, Quick Select
time: O(nlogk)
space: O(k)

kth largest in array

#### PriorityQueue, MinHeap
- Use minHeap to maintain PQ of k size and return PQ.peek()
    - Maintain MinHeap: only allow larger elements (which will squzze out the min value)
    - Remove peek() of queue if over size
- O(nlogk)

#### Quick Select, Quick Sort
- 用Quick Sort 里面partion的一部分: sort结束后是ascending的.
  - kth largest = (n - k)th smallest
  - in partioned array (quick sort), the portion before pivot are less than pivot
  - that is, the `pivot value` is the divider: anything after pivot is larger than it.
  - after `swap(nums, low, pivot)`: index low has the (n-k)th smallest, if `low = n-k`
- Steps:
  - each iteration: pick pivot,然后从low,和high都和pivot作比较
  - Find `low>pivot, high<pivot` to swap
  - The new low is the next partion point
- Time: average O(n), worst case O(n^2)
- space: O(1) extra spaces besides recursive stack

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


/*
- Quick sort/ partition
- Partition to return the `low` index, which should match targetIndex.
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return partition(nums, 0, n - 1, n - k);
    }
    
    private int partition (int[] nums, int start, int end, int targetIndex) {
        // define low/high
        int pivot = end;
        int low = start, high = end, pivotNum = nums[pivot];
        
        // move pointer and swap
        while (low < high) {
            while (low < high && nums[low] < pivotNum) low++; // break when nums[low] >= pivotNum
            while (low < high && nums[high] >= pivotNum) high--; // break when nums[high] < pivotNum
            swap(nums, low, high);
        }
        swap(nums, low, pivot);

        // compare if low == targetIndex; or recursively partition to find targetIndex
        if (low == targetIndex) return nums[low];
        else if (low < targetIndex) return partition(nums, low + 1, end, targetIndex);
        return partition(nums, start, low - 1, targetIndex);
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
```