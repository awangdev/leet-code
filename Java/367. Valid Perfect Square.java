E
tags: Math, Binary Search
time: O(logN)
space: O(1)

#### Binary找sqrt
- binary search template: mid+1, mid-1, `start <= end`
- define index as long. 

```
/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
*/

/*
Thoughts:
Find a number x that x * x == num. x = [0 ~ num]
Binary search [0 ~ num]
*/
/*
- Find a number n, that n*n == num
- Make it faster, use binary search
- End state: (n-1)^2 < num, and n^2 > num
*/
class Solution {
    public boolean isPerfectSquare(int num) {
        long start = 0, end = num;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long sqrt = mid * mid;
            if (sqrt == num) return true;
            else if (sqrt < num) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }
}
```