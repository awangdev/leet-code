M
1517984778

熟悉bits的一些trick:
- ~0 = -1 = 111111...11111111 (32-bit)
- Create mask by shifting right >>>, and shifting left
- Reverse to get 0000...11110000 format mask
- & 0000 = clean up; | ABC = assign ABC

```
/*
Given two 32-bit numbers, N and M, and two bit positions, i and j. 
Write a method to set all bits between i and j in N equal to M 
(e g , M becomes a substring of N located at i and starting at j)   


Example
Given N = (10000000000)2, M = (10101)2, i = 2, j = 6

return N = (10001010100)2

Challenge
Minimum number of operations？

Tags Expand 
Cracking The Coding Interview Bit Manipulation Binary Representation

Thinking process:
Create a mask: xxxx000000xxxx.
Trick part: when it encounters negative number or dealing with index at edge index = 31, it starts having issue. Interesting fix: use long for masks.
*/
/*
Thoughts:
Need a mask of 1111110000001111... where the '0's are representing range [i, j].
Use the mask to n & mask, so this block will be 0 in n.
shift m << i, then n | m will do.

Problem is:
how to create that mask?
Trick:
We can create 00000001111110000, and reverse it.

32-bit 111111...111 is actually -1 in decimal. Or, we can get it by ~0.

1. We want a block of size [j - i], so right-logic-shift -1 >>> (j - i + 1). (be careful with negative leading 1)
2. Left-shift << i so the block is at correct position.
3. Reverse to complete the mask

End:
1. n & mask
2. left-shift m by i
3. n | m

*/
class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        // Prepare mask
        int mask = -1; // 11111111...1111
        mask = -1 >>> (32 - (j - i + 1)); // 000000...00111111, shift and leave only [j, i] block
        mask = mask << i; // 000000..11111000
        mask = ~mask; // reverse: 111111...00000111
        
        // Apply mask
        n = n & mask; // Remove existing
        return n | (m << i); // apply m
    }
}
```