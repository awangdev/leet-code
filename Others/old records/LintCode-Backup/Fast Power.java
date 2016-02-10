/*
Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)

Tags Expand 
Divide and Conquer

Thoughts:
Learn online:
(a * b) % p = (a % p * b % p) % p
Than mean: a ^ n can be divided into a^(n/2) * a^(n/2), that can be used for recursion: divde and conqure.

Note: when n is odd number, it cannot be evenly divided into n/2 and n/2. This case needs special treatment: n = n/2 + n/2 + 1;
*/

class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
    	if (n == 0) {
    		return 1 % b;
    	}
    	if (n == 1) {
    		return a % b;
    	}

    	long recurPow = fastPower(a, b, n / 2);
    	recurPow = (recurPow * recurPow) % b;

    	if (n % 2 == 1) {
    		recurPow = recurPow * a % b;
    	}

    	return (int)recurPow;
    }
};
