/*
Numbers keep coming, return the median of numbers at every time a new number added.

Have you met this question in a real interview? Yes
Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge
Total run time in O(nlogn).

Clarification
What's the definition of Median? - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Tags Expand 
LintCode Copyright Heap Priority Queue
*/



public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int[] rst = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return rst;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        });
        
        rst[0] = nums[0];
        maxHeap.offer(rst[0]);
        
        for (int i = 1; i < rst.length; i++){
            int preMedian = maxHeap.peek();
            if (nums[i] > preMedian) {
                minHeap.offer(nums[i]);
            } else {
                maxHeap.offer(nums[i]);
            }

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            rst[i] = maxHeap.peek();
        }
        return rst;
    }
}
