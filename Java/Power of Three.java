E
1516341495
tags: Math

方法1:
Power of 3:  3 ^ x == n ?
意思是 n / 3 一直除, 最后是可以等于1的, 那么就有了 n/=3, check n%3, 最后看结果是不是整除到1的做法. 用while loop.

方法2:
如果n是power of 3, 那么 3 ^ x的这个 x一定是个比n小的数字. 那么可以在 0 ~ n 之间做binary serach, 但是就比较慢.

方法3:
巧妙的想法.最大的3^x integer是 3^19. 那么找到这个数, 一定可以被n整除. 一步到位.

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

/*
Thoughts:
Use binary serach: pick an item, do 3^x and see if equals to n, < n or >n, then move the number.
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        int start = 0;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            long powerOfThree = (long) Math.pow(3, mid);
            if (powerOfThree == n) {
                return true;
            }
            if (powerOfThree < n) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.pow(3, start) == n;
    }
}

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

// Shorter version
class Solution {
    public boolean isPowerOfThree(int n) {
        while (n/3>=1){
            if (n%3!=0) return false;
            n/=3;
        }
        return n==1;
    }
}

/*
Thoughts:
The largest int is 2^31, and the largest 3^x = 3^19.
If n is power of 3, it should 3^19 % n should == 0
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        return (n > 0 && Math.pow(3, 19) % n == 0);
    }
}

```