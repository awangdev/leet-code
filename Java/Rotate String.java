E
1526570339
tags: String

给两个String, 看A rotate之后 能不能变成B

#### LeetCode
- Basics
- StringBuffer.deleteCharAt(xx), StringBuffer.append(xx)
- O(n)


#### LintCode
- Different problem: 给一个char[], 要rotate offset times.
- *三步rotate*
- 有个坑：offset可能很长，那么要%length，才能得到真正需要rotate的部分。
- Note: rotate 一个 full length之后，是string 不变

```
/*
We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. 
For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. 
Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.

 */

class Solution {
    public boolean rotateString(String A, String B) {
        // check edge condition
        if (A == null || B == null || A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            return true;
        }
        // move characters, one at a time, and compare
        StringBuffer sb = new StringBuffer(A);
        for (char c : A.toCharArray()) {
            sb.deleteCharAt(0);
            sb.append(c);
            if (sb.toString().equals(B)) {
                return true;
            }
        }
        return false;
    }
}




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


```