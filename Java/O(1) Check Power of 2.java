E
tags: Bit Manipulation

```

/*
Using O(1) time to check whether an integer n is a power of 2.
Example
For n=4, return true

For n=5, return false

Challenge
O(1) time

Tags Expand 
Binary

Thinking process:
Any integer that's power of 2, follows one pattern. They are all: 1000000000....000 format.
so (n - 1) becomes: 01111111111...111. 
If bit-and them togeter, it will be 0.

*/

class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
};



```