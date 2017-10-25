E

方法1: 转换成String 然后 reverse
方法2: 每次加上x%10，然后x不断减小～0

```
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
        int arrLength = arr.length;
        for (int i = 0; i < arrLength/2; i++) {
            char temp = arr[i];
            arr[i] = arr[arrLength - i - 1];
            arr[arrLength - i - 1] = temp;
        }
        result = Long.parseLong(String.valueOf(arr)) * (x > 0 ? 1 : -1);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }
}


class Solution {
    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
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