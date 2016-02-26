Median还是用min-heap 和 max-heap。
移动窗口2step：
1. 加一个数。
2. 减一个数。
加减时看好，是从前面的maxheap里面抽，还是从后面的minHeap里面抽。
抽完balance一下。

记得:
左边的maxHeap总有 x+1或者x个数字。
后边minHeap应该一直有x个数字。

```
/*
Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the median of the element inside the window at each moving. (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )

Example
For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]

At first the window is at the start of the array like this

[ | 1,2,7 | ,8,5] , return the median 2;

then the window move one step forward.

[1, | 2,7,8 | ,5], return the median 7;

then the window move one step forward again.

[1,2, | 7,8,5 | ], return the median 7;

Challenge
O(nlog(n)) time

Tags Expand 
LintCode Copyright Heap
*/

//NOT DONE
/*
Thoughts:
1. from 0 ~k, populate the maxHeap and minHeap
2. i = k ~ nums.length, add one value and minus one value, calculate median	
*/

public class Solution {
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;

    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
    	ArrayList<Integer> rst = new ArrayList<Integer>();
    	if (nums == null || nums.length == 0 || k <= 0) {
    		return rst;
    	}
    	minHeap = new PriorityQueue<Integer>();
    	maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>(){
    		public int compare(Integer x, Integer y){
    			return y - x;
    		}
    	});
    	maxHeap.offer(nums[0]);

    	for (int i = 1; i < k; i++) {
    		add(nums[i]);
    	}
    	
    	rst.add(maxHeap.peek());
    	for (int i = k; i < nums.length; i++) {
    		add(nums[i]);
    		remove(nums[i - k]);
    		rst.add(maxHeap.peek());
    	}
    	return rst;
    }

    public void add(int val) {
    	int preMedian = maxHeap.peek();
		if (val > preMedian) {
			minHeap.offer(val);
		} else {
			maxHeap.offer(val);
		}
		balance();
    }

    public void remove(int val) {
        int preMedian = maxHeap.peek();
        if (val > preMedian) {
            minHeap.remove(val);
        } else {
            maxHeap.remove(val);
        }
    	balance();
    }

    public void balance() {
    	if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.offer(maxHeap.poll());
		} else if (maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
    }

}


```