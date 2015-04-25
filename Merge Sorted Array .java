/*
33% Accepted
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


