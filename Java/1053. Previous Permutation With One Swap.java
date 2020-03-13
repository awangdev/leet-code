M
tags: Array, Greedy, Permutation
time: O(n)
space: O(1)

#### Analyze Permutation behavior
- concept similar to `31. Next Permutation`
- 1) first pass: find the one that is in incorrect order
- 2) second pass: find the right spot to swap

```
/*
Given an array A of positive integers (not necessarily distinct), return the lexicographically largest permutation that is smaller than A, that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).  If it cannot be done, then return the same array.

 

Example 1:

Input: [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.
Example 2:

Input: [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.
Example 3:

Input: [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.
Example 4:

Input: [3,1,1,3]
Output: [1,3,1,3]
Explanation: Swapping 1 and 3.
 

Note:

1 <= A.length <= 10000
1 <= A[i] <= 10000
*/

class Solution {
    public int[] prevPermOpt1(int[] A) {
        if (A.length <= 1) return A;
        int index = -1;
		// find the largest i such that A[i] > A[i + 1]
        for (int i = A.length - 1; i >= 1; i--) {
            if (A[i] < A[i - 1]) {
                index = i - 1;
                break;
            }
        }
		// the array already sorted ascendingly, no need to procceed
        if (index == -1) return A;
		
        // find the largest i such that A[index] > A[i], then swap them
        for (int i = A.length - 1; i > index; i--) {
            if (A[i] < A[index] && A[i] != A[i - 1]) {
                swap(A, i, index);
                break;
            }
        }
        return A;
    }
    
    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
```