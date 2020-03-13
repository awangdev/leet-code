E
tags: Math
time: O(n)
space: O(1)

#### 方法1
每次加上x%10，然后x不断减小～0
注意处理MAX_VALUE, MIN_VALUE
符号不重要, 直接处理, 也会保留.

#### 方法2
转换成String 然后 reverse
Space O(n), time O(n)

```
/*
LeetCode
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output:  321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
class Solution {
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) return 0; // check overflow. If newResult overflow, it won't resolve back to result
            result = newResult;
            x = x / 10;
        }

        return result;
    }
}

/*
use long to catch rst
    x%10 to get last digit
    x/10 to iterate
    rst *= 10 
while (x > 0)

*/
class Solution {
    public int reverse(int x) {
        long rst = 0;
        while (x != 0) {
            rst = rst * 10 + x % 10;
            x /= 10;
            if (rst > Integer.MAX_VALUE || rst < Integer.MIN_VALUE) return 0;
        }
        return (int) rst;
    }
}
```
