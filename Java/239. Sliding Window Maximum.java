H
tags: Sliding Window, Deque, Heap
time: O(n)
space: O(n)

#### Method1: Deque, Monotonous queue
- 维持monotonuous queue: `front is always at max` and the `tail end is min`. Always need to return the max end of queue.
- when adding new elements x: 
    - 1) start from small-end of the queue
    - 2) drop all smaller elements 
    - 3) append to the ending element that is larger than x.
    - This is to maintain a front->tail decreasing queue
- when sliding window: queue curr window 里面 最大的已经在max-end,  remove it if needed.
- 妙：用deque数据结构（实际上采用LinkedList的形式）来做一个`递减的queue`: better than using arraylist, since DeQueue(linked list) removes at O(1) cost
- 每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
- 我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
- Option1: sliding window template using right/left + while loop
    - 1) tailing the new number to max queue, if applicable
    - 2) process: record max
    - 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
- Option2: same concept, but use index `i` to mark right, and `i - k + 1` to mark left.
- time: O(n), one pass
- space: O(k), store the deque


#### Method2: Heap
- can always build a `class Node{index, val}`; and sort them with PQ of size k
- time: O(nlogK)
- space: O(k)
- this is not linear time, not as good as method1

```
/*
LeetCode:
Given an array nums, there is a sliding window of size k which is moving 
from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

*/
// Method1: sliding window, option2: using sliding window template
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) return new int[0];
        int left = 0, right = 0, n = nums.length;
        
        int[] rst = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // ArrayDeque is faster than LinkedList, when not removing node from middle
        while (right < k - 1) inQueue(deque, nums[right++]); // add first k - 1 items
        
        while(right < n) { // slide window on remain items
            // 1) tailing the new number to max queue, if applicable
            inQueue(deque, nums[right++]); 
            // 2) process: record max
            rst[left] = deque.peekFirst(); 
            // 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
            outQueue(deque, nums[left++]);
        }
        return rst;
    }
    /*
        Monotonous queue: front/top = max.
        Remove all smaller items from end of queue. Therefore to maintain a decreasing queue from front/top -> tail
    */
    private void inQueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) { // top
            deque.pollLast();
        }
        deque.offerLast(num);
    }
    /*
        if target is at top/max, remove due to sliding window
        if target is not at top, it must not in the queue anyway, skip.
    */
    private void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) deque.pollFirst();
    }
}

/*
Method1: option2
Brutle way: for every k window, calculate the max O(nk)
Can use priority queue to track max => O(nLogK) => O(nLogN)
Linear time: O(n)?

Want to maintain the window, a deque, where right-most element in the window is the lowest value in the deque.
1. when adding new element, we need to clean up the deque bottom
2. when removing item, simply just match see if any top max is match; if match, remove it.

Deque always have all unique max values, with right-most window element as smallest max in the deque.

*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) return new int[0];
        int n = nums.length;
        
        int[] rst = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) inQueue(deque, nums[i]); // add first k - 1 items
        
        for (int i = k - 1; i < n; i++) { // slide window on remain items
            // 1) tailing the new number to max queue, if applicable
            inQueue(deque, nums[i]); 
            // 2) process: record max
            rst[i - k + 1] = deque.peekFirst(); 
            // 3) contract/shrink left: remove top max if the topMax is the left-most val: rst[i - k + 1]
            outQueue(deque, nums[i - k + 1]);
        }
        return rst;
    }
    /*
        monotonous queue: front/top = max.
        Remove all smaller items from end of queue. Therefore to maintain a decreasing queue from front/top -> tail
    */
    private void inQueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) { // top
            deque.pollLast();
        }
        deque.offerLast(num);
    }
    /*
        if target is at top/max, remove due to sliding window
        if target is not at top, it must not in the queue anyway, skip.
    */
    private void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) deque.pollFirst();
    }
}


```