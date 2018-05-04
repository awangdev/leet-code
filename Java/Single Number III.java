M

```

/*
Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
Given [1,2,2,3,4,4,5,3] return 1 and 5

Challenge
O(n) time, O(1) extra space.

Thinking Process:
The 2 exception must have this feature: a ^ b != 0, since they are different
Still want to do 2n + 1 problem as in Single Number I, then we need to split a and b into 2 groups and deal with two 2n+1 problems
Assume c = a^b, there mush be a bit where a and b has the difference, so that bit in c is 1.
Find this bit position and use it to split the group: shift number in the array by ‘bit-position’ indexes. If the shifted number has 1 at the ‘bit-position’, set it to one group; otherwise to another group. 
*/

public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        List<Integer> rst = new ArrayList<Integer>();
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        int bitOnePos = 0;
        for (int i = 0; i < 32; i++) {
            if ((xor >> i & 1) == 1) {
                bitOnePos = i;
            }
        }
        int rstA = 0;
        int rstB = 0;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] >> bitOnePos & 1) == 1) {
                rstA ^= A[i];
            } else {
                rstB ^= A[i];
            }
        }
        rst.add(rstA);
        rst.add(rstB);
        return rst;
    }
}


```