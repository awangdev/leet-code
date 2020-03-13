H
1529474471
tags: Math

Pure math problem, not quite representative

Explanation
https://leetcode.com/problems/number-of-digit-one/discuss/64381/4+-lines-O(log-n)-C++JavaPython

```
/*

Given an integer n, count the total number of digit 1 appearing in all 
non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6 
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

 */

 class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            count += (n / i + 8) / 10 * i;
            if (n / i % 10 == 1) {
                count += n % i + 1;
            }
        }
        return count;
    }
}
```