H
1520753616
tags: Heap, Design

Median还是用min-heap 和 max-heap. Time(logN)
加/减: prioirtyQueue, log(n)
findMedian: O(1)

==== 思想
- 加一个数, 减一个数。
- 加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
- 抽完balance一下

==== 注意
用maxHeap, minHeap时候, 习惯选择让maxHeap多一个数字:
左边的maxHeap总有 x+1或者x个数字
后边minHeap应该一直有x个数字

```
/**
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */


/*
Thoughts:
Similar to Stream Data Median: addNum(num), findMedian(), removeNum(num)
*/
class Solution {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return null;
        }
        
        int n = nums.length;
        double[] rst = new double[n - k + 1];
        // Build median structure with window
        for (int i = 0; i < k - 1; i++) {
            addNum(nums[i]);
        }
        
        // Calculate
        for (int i = 0; i < n - k + 1; i++) {
            addNum(nums[i + k - 1]);
            rst[i] = findMedian();
            removeNum(nums[i]);
        }
        
        return rst;
    }
    
    private double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0; // be careful with Integer.MAX_VALUE, consider breaking or use long
        } else {
            return maxHeap.peek();
        }
    }
    
    private void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }
        int currentMedian = maxHeap.peek();
        if (num <= currentMedian) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balance();
    }
    
    private void removeNum(int num) {
        int currentMedian = maxHeap.peek();
        if (num <= currentMedian) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance();
    }
    
    // helper
    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
}

```