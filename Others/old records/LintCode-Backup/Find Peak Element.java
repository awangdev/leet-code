还是binary search. 
一个特别的check condition, 和特别的move left, move right的case罢了。
```
/*There is an integer array which has the following features:

    * The numbers in adjacent positions are different.

    * A[0] < A[1] && A[A.length - 2] > A[A.length - 1].

We define a position P is a peek if A[P] > A[P-1] && A[P] > A[P+1].

Find a peak in this array. Return the index of the peak.

Note
The array may contains multiple peeks, find any of them.

Example
[1, 2, 1, 3, 4, 5, 7, 6]

return index 1 (which is number 2)  or 6 (which is number 7)

Challenge
Time complexity O(logN)

Tags Expand 
Binary Search Array LintCode Copyright

Thinking Process:
画图
*/

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        int start = 1;
        int end = A.length - 2;
        int mid;
        
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            //Tricky: only when start< mid < mid + 1, we can set start = mid;
            //This is because we are cilmbing, so going up will finally find a peak
            } else if (A[mid] > A[start] && A[mid] < A[mid + 1]) {
                start = mid;
            } else {// this case A[start] > A[mid], so we climb backwards, all make sense
                end = mid;
            }
        }//while
        
        if (A[start] > A[start - 1] && A[start] > A[start + 1]) {
            return start;
        } else {
            return end;
        } 
        
    }
}


```