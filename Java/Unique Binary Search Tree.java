M
1516865694

Not quite clear.
根据左右分割而总结出了原理, 每次分割, 左右两边都会有一定数量的permutation, 总体上的情况数量当然是相乘.
然后每一个不同的分割点都加一遍:
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

然后把数学公式转换成DP的方程, 有点玄学的意思啊! 不好想.

```
/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?



Example
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Tags Expand 
Catalan Number Dynamic Programming

Thinking proces:
Knowing what is Catalan number. 
C(n+1) = SUM(C(i)*C(n-i))
OR: C(n) = SUM(C(i)*C(n-i-1)).
*/

/*
Thoughts:
If pick 1, left: 0, right: 2, 3. f(0) = 1, f(2) with value 2,3, there are two ways of orientation, which makes f(2) = 2.
    Therefore, f(0) * f(2) = 2;
If pick 2, left: 1, right:3, there is only f(1)*f(1) = 1
If pick 3, left: 1, 2, right: 0, f(2) * f(0) = 2
So add all possible conditions together: 2 + 1 + 2 = 5
f(0) = 1
f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

This is more like a math problem: Catalan number
C(n+1) = SUM(C(i)*C(n-i))
OR: C(n) = SUM(C(i)*C(n-i-1)).
*/
class Solution {
    public int numTrees(int n) {
        if ( n <= 1) {
            return 1;
        }
        final int[] numTree = new int[n + 1];
        numTree[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                numTree[i] += numTree[j] * numTree[i - j - 1];
            }
        }
        return numTree[n];
    }
}


public class Solution {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}

```