H
tags: Sliding Window

妙：用deque数据结构（实际上采用LinkedList的形式）来做一个递减的queue.
每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
为啥可以不管不无地剔除？
因为我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
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

/*
Thoughts:
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
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }
        int[] rst = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, nums[i]);
        }
        
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(deque, nums[i]); // clean up and add new smallest maximum, if applicable
            rst[i - k + 1] = deque.peekFirst();
            outQueue(deque, nums[i - k + 1]); // remove top max if the num === max
        }
        return rst;
    }
    
    private void inQueue(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) { // top
            deque.pollLast();
        }
        deque.offerLast(num);
    }
    
    private void outQueue(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
}


/*
Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.

Example
For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]

At first the window is at the start of the array like this

[|1, 2, 7| ,7, 8] , return the maximum 7;

then the window move one step forward.

[1, |2, 7 ,7|, 8], return the maximum 7;

then the window move one step forward again.

[1, 2, |7, 7, 8|], return the maximum 8;

Challenge
o(n) time and O(k) memory

Tags Expand 
LintCode Copyright Deque
*/

/*
	Thoughts:
	Create deque: ArrayDeque, or LinkedList
	Maintain a decreasing deque that stores indexes: so peekFirst() is always the maxium value's index
	To do that, we need: whenever adding a new value, remove all values in the deque that are smaller than current node.
	Loop k elements first, then process the rest
	O(n) time, O(k) space
*/
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	if (nums == null || nums.length == 0 || k < 0) {
    		return rst;
    	}
    	Deque<Integer> deque = new LinkedList<Integer>();
    	for (int i = 0; i < k; i++) {
    		while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
    			deque.pollLast();
    		}
    		deque.offerLast(i);
    	}

    	for (int i = k; i < nums.length; i++) {
    		rst.add(nums[deque.peekFirst()]);
    		if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
    			deque.pollFirst();
    		}
    		while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
    			deque.pollLast();
    		}
    		deque.offerLast(i);
    	}
    	
    	//Last move's result needs to be recorded:
    	rst.add(nums[deque.peekFirst()]);
    	return rst;
    }
}


```