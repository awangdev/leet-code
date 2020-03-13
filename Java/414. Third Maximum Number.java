E
tags: Array, PriorityQueue

#### pq
- 注意special case: `when less than 3 items, return maximum`
- PQ是natural order: remain peek() will be the 3rd maximum


```
/*
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
*/

class Solution {
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            if (!pq.contains(num)) {
                pq.offer(num);
                if (pq.size() > 3) pq.poll();
            }
        }
        // if no third maximum, return max
        if (pq.size() < 3) {
            while (pq.size() > 1) pq.poll();
        }
        return pq.peek();
    }
}
```