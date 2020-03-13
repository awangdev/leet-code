E
1516343302
tags: Math, Bit Manipulation

跟powerOfThree一样: 可以loop, check mod; 也可以用binary search找合适的数字.

```
/*
Given an integer, write a function to determine if it is a power of two.
*/

/*
Similar to powerOfThree. Two ways: 1 divide and check mode; 2. binary search
*/
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 2 != 0 || n / 2 == 0) {
                return false;
            }
            n/=2;
        }
        return true;
    }
}
```