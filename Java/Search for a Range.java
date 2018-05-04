M

前后跑2个while loop。 找first/last occurance
```
/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Tags Expand 
Binary Search Array Sorted Array
*/

/*
    Recap: 12.08.2015
    input sorted
    2 binary search while loop.
    First one, keep looking for 1st occurnace.
    Second one, keep looking for alst occurance.
    
    check border case:
    A = []


*/
public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] rst = new int[]{-1, -1};
        if (A == null || A.length == 0) {
            return rst;
        }
        int start = 0;
        int end = A.length - 1;
        int mid = start + (end - start)/2;

        //1st occurance.
        int first = 0;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A[mid] == target) {
                if (mid - 1 >= 0 && A[mid - 1] == target) {
                    end = mid;
                    continue;
                }
                break;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] == target) {
            first = start;
        } else if (A[mid] == target) {
            first = mid;
        } else if (A[end] == target) {
            first = end;
        } else {
            return rst;
        }
        rst[0] = first;

        //check last occurance
        int last = 0;
        start = first;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (A[mid] == target) {
                if (mid + 1 < A.length && A[mid + 1] == target) {
                    start = mid;
                    continue;
                }
                break;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] == target) {
            last = end;
        } else if (A[mid] == target) {
            last = mid;
        } else if (A[start] == target) {
            last = start;
        } else {
            last = first;
        }
        rst[1] = last;
        
        return rst;
    }
}










//Older solution:
public class Solution {
    /** 
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public ArrayList<Integer> searchRange(ArrayList<Integer> A, int target) {
        // write your code here
        int start = 0;
        int end = A.size() - 1;
        int mid;
        ArrayList<Integer> bound = new ArrayList<Integer>();
        bound.add(-1);
        bound.add(-1);
        
        if ( A.size() == 0) {
            return bound;
        }
        
        //Left:
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A.get(mid) < target) {
                start = mid;
            } else if (A.get(mid) > target) {
                end = mid;
            } else {
                end = mid;//Run to left
            }
        }
        if (A.get(start) == target) {//Run to left
            bound.set(0, start);
        } else if (A.get(end) == target) {
            bound.set(0, end);
        } else {
            return bound;
        }
        
        //Right:
        start = 0;
        end = A.size() - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A.get(mid) < target) {
                start = mid;
            } else if (A.get(mid) > target) {
                end = mid;
            } else {
                start = mid;//Run to right
            }
        }
        if (A.get(end) == target) {//Run to right
            bound.set(1, end);
        } else if (A.get(start) == target) {
            bound.set(1, start);
        } else {
            return bound;
        }
        
        return bound;
    }
}


```