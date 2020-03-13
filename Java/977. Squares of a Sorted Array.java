E
tags: Array, Two Pointers
time: O(n)
space: O(n)

#### Two Pointers
- negative index i, positive index j

```

/*
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
*/

class Solution {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) return A;
        int n = A.length;
        int i = findPos(A), j = i + 1;
        int index = 0; 
        int[] result = new int[n];
        
        while (i >= 0 || j < n) {
            int squareI = i < 0 ? Integer.MAX_VALUE : A[i] * A[i];
            int squareJ = j >= n ? Integer.MAX_VALUE : A[j] * A[j];
            if (squareI < squareJ) {
                result[index++] = squareI;
                i--;
            } else {
                result[index++] = squareJ;
                j++;
            }
        }
        return result;
    }
    
    public int findPos(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) return i - 1;
        }
        return 0;
    }
}
```
