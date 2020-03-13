H
tags: Two Pointers, Array, Binary Search

```
/*
Given an integer array, find a subarray where the sum of numbers is in a given interval.
Your code should return the number of possible answers. (The element in the array should be positive)

Example
Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:

[0, 0]
[0, 1]
[1, 1]
[2, 2]
*/

/*
Thoughts:
Brutle O(n2) with preSum can do. Do better: O(nlogn)
- presum
- iterate over start point, which will be the 'must have' index in result
- find the largest end point, where preSum[end] - preSum[start - 1] <= max.  Binary Search since the presum will be increasing
- count += end - start + 1
- move on to next start
- Overall it should be O(nlogn)
- should write the binary search as a separate function
*/
```