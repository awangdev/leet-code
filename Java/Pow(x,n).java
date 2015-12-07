注意n/2的奇数偶数。
n的正负。
n == 0的情况。
```
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