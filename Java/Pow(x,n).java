M
1519508947

傻做就O(n), 要更好就考虑O(logN).
减少重复计算, 一切两半.

注意:
- n/2的奇数偶数
- n的正负
- n == 0的情况, x == 0, x == 1 的情况.
```
/**
LeetCode:
Implement pow(x, n).


Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
 */
/*
Thoughts:
Given n, O(n) can get result.
Want to improve to O(logN)
Divide the n to half, and do val= myPow(x, n/2);
1. If n == even: return val * val
2. If n != even: return val * val * x;

Also, consider the sign of x.
Even -> pos
Odd -> neg if x is negative

What if n < 0

*/
class Solution {
    public double myPow(double x, int n) {
        if (x == 0 || x == 1 || n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        double rst = helper(Math.abs(x), Math.abs(n));
        int sign = x < 0 && n % 2 == 1 ? -1 : 1;
        if (n < 0) {
            return sign / rst;
        } else {
            return sign * rst;
        }
    }

    public double helper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double val = helper(x, n / 2);
        if (n % 2 == 0) {
            return val * val;
        } else {
            return val * val * x;
        }
    }
}


/*
Pow(x, n)

Implement pow(x, n).

Have you met this question in a real interview? Yes
Example
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1
Note
You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.

Challenge
O(logn) time

Tags Expand 
Binary Search LinkedIn Divide and Conquer Mathematics Facebook
*/

/*
	Everytime: if divide the power n by 2, then it equlas to pow(x,n/2) * pow(x, n/2).
	Also consider n could be negative. Let myPow handle the negative n. basically return 1/myPow(x,n)
	Cnsider the case of 0: x^0 = 1.
	use a helper funtion: when n%2 == 0, regular; if n%2 ==1, do pow(x, (n-1)/2) * x.
		Note: n/2 = (n-1)/2. So this can be optmized.
*/

public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
    	if(n >= 0) {
    		return pow(x, n);
    	} else {
    		return 1/pow(x, n);
    	}
    }

    public double pow(double x, int n) {
    	if (n == 0) {
    		return 1;
    	}
    	double num = pow(x, n/2);
    	if (n % 2 == 0) {
    		return num * num;
    	}
    	return num * num * x;
    }
}

```