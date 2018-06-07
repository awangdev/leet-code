H
1528350988
tags: String, KMP

#### Divide by mid point, Brutle
- check (mid, mid+1), or (mid-1, mid+1).
- If the two position matches, that is a palindrome candidate
- 比较front string 是否是 end string 的substring
- O(n^2)
- timeout on last case: ["aaaaaa....aaaacdaaa...aaaaaa"]

#### KMP
- TODO

```
/*
Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"

*/


/*
Thoughts:
1. Find mid (based on odd or even). n / 2 or n / 2 - 1
2. loop mid -> 0: check (mid, mid+1), or (mid-1, mid+1)
3. If match, do front.reverse() check if it's substring of end
4. if so, return end.reverse() + (mid) + end

Timeout on last case: ["aaaaaa"]
*/

class Solution {
    public String shortestPalindrome(String s) {
        // edge case
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        // find mid
        int n = s.length();
        int mid = n / 2 + (n % 2 == 0 ? -1 : 0);

        // loop from mid -> 0, have buffer to store end
        // perform 2 checks, and substring check
        for (int i = mid; i >= 0; i--) {
            String end = s.substring(i + 1);
            String reversedEnd = new StringBuffer(s.substring(i + 1)).reverse().toString();
            // check (mid, mid+1)
            if (validateSubstring(s, i, i + 1)) {
                return reversedEnd + end;
            }
            // check (mid-1, mid+1)
            if (i >= 1 && validateSubstring(s, i - 1, i + 1)) {
                return reversedEnd + s.charAt(i) + end;
            }
        }

        return new StringBuffer(s.substring(1)).reverse().toString() + s;
    }

    private boolean validateSubstring(String s, int i, int j) {
        String end = s.substring(j).toString();
        String front = new StringBuffer(s.substring(0, i + 1)).reverse().toString();
        if (front.length() <= end.length() && end.indexOf(front) == 0) {
            return true;
        }
        return false;
    }
}

```