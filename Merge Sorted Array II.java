/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are mand n respectively.

Example
A = [1, 2, 3, empty, empty] B = [4,5]

After merge, A will be filled as [1,2,3,4,5]

Tags Expand 
Array Sorted Array

Thinking process:
1. start from the end, track back. End index = m + n;
2. when ever check a position, need to do index-1, because index=m+n is the size of array.
3. Make sure to clean up the second array B.
*/

class Solution {
    /**
     * @param A: sorted integer array A which has m elements, 
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int index = m + n;
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[--index] = A[--m];
            } else {
                A[--index] = B[--n];
            }
        }//While
        
        while (n > 0) {
            A[--index] = B[--n];
        }
    }
}

