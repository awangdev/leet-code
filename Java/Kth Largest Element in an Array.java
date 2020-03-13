M
1533137926
tags: Divide and Conquer, Heap, PriorityQueue, MinHeap, Quick Sort

kth largest in array

#### PriorityQueue, MinHeap
- Need to maintain k large elements, where the smallest will be compared and dropped if applicable: 
- Maintain k elements with min value: consider using minHeap
- add k base elements first
- Maintain MinHeap: only allow larger elements (which will squzze out the min value)
- Remove peek() of queue if over size
- O(nlogk)


#### Quick Sort
- 用Quick Sort 里面partion的一部分
- sort结束后是ascending的, 那么 n - k 就是第k大. 
- partion的结果是那个low, 去找 low==nums.size() - k， 也就是倒数第K个。    
- 没找到继续partion recursively.
- sort的过程是排一个从小到大的list. (同样的代码还可以好xth smallest，mid变成x就好)
- Steps:
- 每个iteration, 找一个pivot,然后从low,和high都和pivot作比较。    
- 找到一个low>pivot, high<pivot, 也就可以swap了。    
- 得到的low就是当下的partion point了
- Overall O(nlogN), average O(n) for this problem.

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


// Quick sort/ partition
// Partition to return the `low` index, which should match targetIndex.
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        return partition(nums, 0, n - 1, n - k);
    }
    
    private int partition (int[] nums, int start, int end, int targetIndex) {
        // define low/high
        int pivot = end;
        int low = start, high = end, num = nums[pivot];
        
        // move pointer and swap
        while (low < high) {
            while (low < high && nums[low] < num) {
                low++;
            }
            while (low < high && nums[high] >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);

        // compare if low == targetIndex; or recursively partition to find targetIndex
        if (low == targetIndex) {
            return nums[low];
        } else if (low < targetIndex) {
            return partition(nums, low + 1, end, targetIndex);
        } else {
            return partition(nums, start, low - 1, targetIndex);
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

/*
LintCode
Find K-th largest element in an array.

Example
In array [9,3,2,4,8], the 3rd largest element is 4

In array [1,2,3,4,5], the 1st largest element is 5, 
2nd largest element is 4, 3rd largest element is 3 and etc.

Note
You can swap elements in the array

Challenge
O(n) time, O(1) space

Tags Expand 
Quick Sort Sort

*/

/*

Thoughts:
Almost the same as the Median problem: 
the only difference is, this one is not looking for the middle point, but for the last kth element. 
Using the same quick sort code with minor modifications, and we can solve this problem.
*/

class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        return helper(nums, 0, nums.size() - 1, nums.size() - k);
    }
    
    public void swap( ArrayList<Integer>nums, int x, int y){
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }
    
    public int helper( ArrayList<Integer> nums, int start, int end, int mid) {
        int pivot = end;
        int num = nums.get(pivot);
        int low = start;
        int high = end;
        while (low < high) {
            while(low < high && nums.get(low) < num) {
                low++;
            }
            while(low < high && nums.get(high) >= num) {
                high--;
            }
            swap(nums, low, high);
        }
        swap(nums, low, pivot);
        if (low == mid) {
            return nums.get(low);
        } else if (low < mid) {
            return helper(nums, low + 1, end, mid);
        } else {
            return helper(nums, start, low - 1, mid);
        }
    }
};

```