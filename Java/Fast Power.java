M
1527969371
tags: DFS, Divide and Conquer

如题: Calculate the a^n % b where a, b and n are all 32bit integers.

#### Divide and Conquer
- a^n可以被拆解成(a*a*a*a....*a)， 是乘机形式，而%是可以把每一项都mod一下的。所以就拆开来take mod.
- 这里用个二分的方法，recursively二分下去，直到n/2为0或者1，然后分别对待. 
- 注意1: 二分后要conquer，乘积可能大于Integer.MAX_VALUE, 所以用个long.
- 注意2: 要处理n%2==1的情况，二分时候自动省掉了一份 a，要乘一下。


```
/*
Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)

Tags Expand 
Divide and Conquer


*/

/*
Thoughts:
Learn online:
(a * b) % p = (a % p * b % p) % p
Than mean: a ^ n can be divided into a^(n/2) * a^(n/2), that can be used for recursion: divde and conqure.

Note: when n is odd number, it cannot be evenly divided into n/2 and n/2. This case needs special treatment: n = n/2 + n/2 + 1;
*/
class Solution {
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

```