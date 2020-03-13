E
tags: Math, String, Two Pointers

#### Two Pointers
- 注意加法结果的位置.
- Use two pointers i, j to track the 2 strings
- Add when i and j are applicable. While (i >= 0 || j >= 0)
- `StringBuffer.insert(0, x);`
- handle carry

#### reverse
- Reverse string -> Convert to Integer List, add up -> Convert back to string
- pointer 从前向后, 所以只需要 1个pointer.
- 操作复杂, 如上, 证明可以解决. 没必要reverse.

#### Incorrect: convert to Integer
把binary换成数字作加法. 如果input很大，那么很可能int,long都hold不住。不保险。

```
/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.insert(0, carry);
        return sb.toString();
    }
}

/*
Thoughts:
Can't just convert to int because of Integer.MAX_VALUE limitation.
Convert to char, and add up all chars
*/



```
