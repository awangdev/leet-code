E
1520830132
tags: Math

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

/*
Thoughts: reverse without extra O(n) space.
Time: O(n)
*/
class Solution {
    public int reverse(int x) {
        long rst = 0;
        while (x != 0) {
            rst = rst * 10 + x % 10;
            x /= 10;
            if (rst > Integer.MAX_VALUE || rst < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rst;
    }
}

/*
Reverse digits of an integer. Returns 0 when the reversed integer overflows (signed 32-bit integer).

Example
Given x = 123, return 321

Given x = -123, return -321

Tags Expand 
Integer
*/

/*
Thoughts:
Approach1:
1. Need a long to store the 32-bit integer, since the reversed can be go over the limit.
2. Save to a string and switch the head/tail digits.

Approach2:
Operate directly on the number and compute the final result using %
*/
class Solution {
    public int reverse(int x) {
        long result = (long) x;
        char[] arr = (Math.abs(result) + "").toCharArray();
        int n = arr.length;
        for (int i = 0; i < n/2; i++) {
            char temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
        result = Long.parseLong(String.valueOf(arr)) * (x > 0 ? 1 : -1);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }
}

/*
Thoughts:
1. Use long to capture the result. If > Integer.MAX_VALUE,return 0;
2. Use string to reverse, the conver to long
3. use string builder to reverse string

*/
public class Solution {
    /**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        long num = (long)n;
        int sign = n > 0 ? 1 : -1;
        String rst = new StringBuilder(Math.abs(num)+"").reverse().toString();
        num = Long.parseLong(rst) * sign;
        
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int)num;
        }
    }
}

```