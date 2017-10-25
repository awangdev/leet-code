E

Power of 3:  3 ^ x == n ?

做出发. 查%.

```
/*

Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.

Hide Company Tags Google
Hide Tags Math
Hide Similar Problems (E) Power of Two


*/

//Check if n = 3 ^ x;
public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 3 != 0) {
            return false;
        }
        return isPowerOfThree(n / 3);
    }
}


```