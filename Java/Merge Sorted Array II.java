E
1528179898
tags: Array

如题, merge two sorted array into 新的 sorted array

- 长度已经固定. Basic Implementation
- 如果一个array足够大, merge into this array, 那么就是从末尾merge.

```
/*

Merge two given sorted integer array A and B into a new sorted integer array.

Example
A=[1,2,3,4]

B=[2,4,5,6]

return [1,2,2,3,4,4,5,6]

Challenge
How can you optimize your algorithm if one array is very large and the other is very small?

Tags Expand 
Array Sorted Array

*/

/*
    12.07.2015
   Since the 2 list A,B are fixed, just add everyting into it.
   Basic implementation
*/

class Solution {
    public int[] mergeSortedArray(int[] A, int[] B) {
        if (A == null || B == null) {
            return A == null ? B : A;
        }
        int[] rst = new int[A.length + B.length];
        int indA = A.length - 1;
        int indB = B.length - 1;
        int i = rst.length - 1;
        while (indA >= 0 && indB >= 0) {
            if (A[indA] <= B[indB]) {
                rst[i--] = B[indB--];
            } else {
                rst[i--] = A[indA--];
            }
        }
        while (indA >= 0) {
            rst[i--] = A[indA--];
        }
        while (indB >= 0) {
            rst[i--] = B[indB--];
        }
        return rst;
    }
}

/*
Attemp1: Regular O(m+n) approach. Not optimizing anything.
*/
class Solution {
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */
    public ArrayList<Integer> mergeSortedArray(ArrayList<Integer> A, ArrayList<Integer> B) {
        if (A == null || A.size() == 0) {
            return B;
        } else if (B == null || B.size() == 0) {
            return A;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i + j < A.size() + B.size()) {
            if (i == A.size()) {
                result.add(B.get(j));
                j++;
            } else if (j == B.size()){
                result.add(A.get(i));
                i++;
            } else {
                if (A.get(i) <= B.get(j)) {
                    result.add(A.get(i));
                    i++;
                } else {
                    result.add(B.get(j));
                    j++;
                }
            }
        }//While
        
        return result;
    }
}



```