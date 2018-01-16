E
Same as https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Integer.java

```
/*
Reverse Integer 

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
//input = 1534236469

Thinking process:
Make sure of operators.
Note: check for overflow using long. When integer is > Integer.MAX_VALUE, then it's overflow.
Initialize long : long x = 1234L;
Convert using (int)

*/
public class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;    //123
        }
        boolean sign = x > 0; //sign = true
        long rst = 0L;
        x = Math.abs(x);                        // 123
        while (x != 0) {                        //x = 123, 12, 1
            rst = rst * 10 + x % 10;           //rst = 3, 30 + 2 = 32, 320 + 1 = 321
            x = x / 10;                         //x = 12; 1; 0
        }
        if (rst < 0 || rst > Integer.MAX_VALUE) {
            return 0;
        }
        return sign ? (int)rst : -(int)rst;
    }
}


```