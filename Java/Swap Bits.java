E
1517982439
tags: Bit Manipulation

简单, 但是很多知识点:
1. Hex 0xaaaaaaaa 是1010101....1010; 0x55555555 是01010101....0101
2. 可以用这两个hex取单数和负数. 如果需要取其他的pattern, 也可以做.
3. x很可能是negative number, 所以right-shift 要用logic shift, >>> 避免leading负数补位.

```
/*
Write a program to swap odd and even bits in an integer with as few instructions as possible
(e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
*/

/*
Thoughts:
index '0' is even, so starting from even bits.
Goal:
Shift even bits << 1, shift odd bits >> 1.
We need to extract all of the odd bits and all of the even bits.

Trick:
0xaaaaaaaa represents: 10101010101....1010
Because 0xA: is number 10, and has binary of 1010.

Similarly:
0x55555555 represents: 01010101010....0101
Because 0x5: is number 5, and has binary of 0101

Therefore:
Get even bits: x & 0x55555555
Get odd bits: x & 0xaaaaaaaa

End:
Perform the shift and add up the two numbers together.

Note: right-shift has to use the logic-shift >>> to resolve the negative number issue.
*/
public class Solution {
    public int swapOddEvenBits(int x) {
        return ((x & 0xaaaaaaaa) >>> 1) + ((x & 0x55555555) << 1);
    }
}
```