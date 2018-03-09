R
1518426971
tags: Math, Binary Search

Binary找sqrt. 基本 mid+1, mid-1 template.
注意: define index as long. 

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
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num <= 1) {
            return true;
        }
        long start = 0;
        long end = num;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long product = mid * mid;
            if (product == num) {
                return true;
            } else if (product < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
```