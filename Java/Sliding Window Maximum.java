妙：用deque数据结构（实际上采用LinkedList的形式）来做一个递减的queue.
每次把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC.
为啥可以不管不无地剔除？
因为我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！
```
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