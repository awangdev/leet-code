H
tags: Sliding Window, DP, Two Pointers, String, Hash Table
time: O(n^2)
space: O(1)

#### Sliding Window
- DIFFERENT from sliding window for substring (`76. Minimum Window Substring`)
    - because this problem rquries keeping the order of characters from the target string
    - Use a `backtrack mechanism` based on target matching to find closest left starting point to right
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T chars in S, in order.
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- Be VERY careful about pointer and index.
- time: O(n^2), backtrack n steps
- Since it requires **order of substring**, `freqMap+counter+twoPointers` approach is NOT applicable

#### DP
- TODO

```
/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". 
If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
*/


/*
Method1: final rewrite version; removed some fancy ++/-- and make it easy to understand
- Since it requires sequence in substring, freqMap+counter+twoPointers approach is NOT applicable
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T occurances in order in S
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- 
*/
class Solution {
    public String minWindow(String s, String t) {
       
        int right = 0, tIndex = 0, start = -1;
        int m = s.length(), n = t.length(), minLength = m;
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        
        while (right < m) {
            if(ss[right] == tt[tIndex]) { // char match
                if(tIndex++ == n - 1) { // tIndex exhausted, start backtrack and find shortest start index
                    // backtrack last matching index `right` to 1st match position; which is just left
                    int left = backtrack(ss, tt, right);

                    // calculate the start point and record
                    int len = right - left + 1;
                    if (len < minLength) {
                        minLength = len;
                        start = left;
                    }
                    // reset tIndex to 0 and right to left to restart; the curr candidate will expire once we drop the 1 valid character from left
                    tIndex = 0;
                    right = left;
                }
            }
            right++;
        }
        return start == -1 ? "" : s.substring(start, start + minLength);
    }
    
    /*
        Backtracking to revisit all T characters, from t.end -> 0 index
        Regardless of ss match, always reduce index
        In the end, ss index will be at a desiredIndex - 1 position
        So + 1 to set actual `left` starting point
    */
    private int backtrack(char[] ss, char[] tt, int index) {
        for (int i = tt.length - 1; i >= 0; i--) {
            while(ss[index--] != tt[i]);
        }
        return index + 1; // index = 1st char match index - 1; ++ to reset
    }
}


/*
- Since it requires sequence in substring, freqMap+counter+twoPointers approach is NOT applicable
- Simple two pointers:
    - 1) move sIndex and tIndex: find all T occurances in order in S
    - 2) backtrack tIndex to 0; backtrack sIndex to initial char match
    - 3) record potential min result
- 
*/
class Solution {
    public String minWindow(String s, String t) {
       
        int sIndex = 0, tIndex = 0, start = -1;
        int m = s.length(), n = t.length(), minLength = m;
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        
        while (sIndex < m) {
            if(ss[sIndex] == tt[tIndex]) { // char match
                if(tIndex++ == n - 1) { // tIndex exhausted, process:
                    int end = sIndex + 1; // mark end of candidate
                    
                    // backtrack tIndex to 0, and backtrack sIndex to 1st matching position
                    while(tIndex-- > 0) { // final state: tIndex = -1
                        while(ss[sIndex--] != tt[tIndex]);
                    } 
                    sIndex++; // sIndex = 1st char match index - 1; ++ to reset
                    tIndex++; // reset to 0

                    // record the candidate
                    if (end - sIndex < minLength) {
                        minLength = end - sIndex;
                        start = sIndex;
                    }
                }
            }
            sIndex++;
        }
        return start == -1 ? "" : s.substring(start, start + minLength);
    }
}

// Option2: Simpler way to write it
class Solution {
    public String minWindow(String s, String t) {
       
        int sIndex = 0, tIndex = 0, start = -1;
        int m = s.length(), n = t.length(), minLength = m;
        char[] ss = s.toCharArray(), tt = t.toCharArray();
    
        while (sIndex < m) {
            if(ss[sIndex] == tt[tIndex]) { // char match
                if(++tIndex == n) { // end
                    int end = sIndex + 1;
                        
                    while(--tIndex >= 0) {
                        while(ss[sIndex--] != tt[tIndex]);
                    }
                    sIndex++;
                    tIndex++;
                    
                    // record
                    if (end - sIndex < minLength) {
                        minLength = end - sIndex;
                        start = sIndex;
                    }
                }
            }
            ++sIndex;
        }
        return start == -1 ? "" : s.substring(start, start + minLength);
    }
}

// Option3: another way to write: use for loop to backtrack and direct reset tIndex = 0;
class Solution {
    public String minWindow(String s, String t) {
       
        int sIndex = 0, tIndex = 0, start = -1;
        int m = s.length(), n = t.length(), minLength = m;
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        
        while (sIndex < m) {
            if(ss[sIndex] == tt[tIndex]) { // char match
                if(tIndex++ == n - 1) { // tIndex exhausted, process it
                    int end = sIndex + 1; // mark end of candidate
                    // reset tIndex to 0 and backtrack sIndex to 1st match position
                    tIndex = 0;
                    sIndex = backtrack(ss, tt, sIndex);

                    // record the candidate
                    if (end - sIndex < minLength) {
                        minLength = end - sIndex;
                        start = sIndex;
                    }
                }
            }
            sIndex++;
        }
        return start == -1 ? "" : s.substring(start, start + minLength);
    }
    
    private int backtrack(char[] ss, char[] tt, int sIndex) {
        for (int i = tt.length - 1; i >= 0; i--) {
            while(ss[sIndex--] != tt[i]);
        }
        return ++sIndex; // sIndex = 1st char match index - 1; ++ to reset
    }
}


```