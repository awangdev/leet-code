/*
17% Accepted
Given a string and an offset, rotate string by offset. (rotate from left to right)

Example
Given "abcdefg"

for offset=0, return "abcdefg"

for offset=1, return "gabcdef"

for offset=2, return "fgabcde"

for offset=3, return "efgabcd"

...


Tags Expand 
String
*/

public class Solution {
    /*
     * param A: A string
     * param offset: Rotate string with offset.
     * return: Rotated string.
     */
    public char[] rotateString(char[] A, int offset) {
        if (A == null || A.length == 0) return A;
        offset = offset % (A.length);
        reverse(A, 0, A.length - offset - 1);
        reverse(A, A.length - offset, A.length - 1);
        reverse(A, 0, A.length - 1);
        return A;
    }
    
    
    //Helper function: reverse certain range of array
    public void reverse(char[] A, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = A[j];
            A[j] = A[i];
            A[i] = temp;
        }
    }
};

