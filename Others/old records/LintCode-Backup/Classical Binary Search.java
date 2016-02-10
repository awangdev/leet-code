E 

   while: start + 1 < end
   mid = start + (end - start) / 2;
   末尾double check start, end.


```
/*
Find any position of a target number in a sorted array. 
Return -1 if target does not exist.

Example
Given [1, 2, 2, 4, 5, 5].

For target = 2, return 1 or 2.

For target = 5, return 4 or 5.

For target = 6, return -1.

Challenge
O(logn) time

Tags Expand 
Binary Search
*/

/*
Thoughts: classic
start,mid,end
*/
public class Solution {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int findPosition(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        int mid;
        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            if (target == A[mid]) {
                return mid;
            } else if (target > A[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }//end while
        if (A[start] == target || A[end] == target) {
            return A[start] == target ? start : end;
        }
        return -1;
    }
}
```
