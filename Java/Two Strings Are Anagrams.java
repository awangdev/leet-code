Anagrams。 用count[256]
坑：不要想象这个是个26letter lowercase. may not be true.
```
/*
Write a method anagram(s,t) to decide if two strings are anagrams or not.

Example
Given s="abcd", t="dcab", return true.

Challenge
O(n) time, O(1) extra space

Tags Expand 
String Cracking The Coding Interview

*/
/*
    Recap 12.09.2015
    O(n) time: cannot Arrays.sort()
    O(1) extra space: cannot convert to char array. But we can use a count[256] to count the occuranceo of letters.

    do 1 for loop:
        + occurance index
        - occurance index

    border cases:
    null: false.
    both length() == 0, true
    length（） not equal: false
    
    don't assume all lower case.
    there are ' ' space, so it can't be just 26 letters. make it 256
*/
public class Solution {
    public boolean anagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        } else if (s.length() != t.length()) {
            return false;
        } else if (s.length() == 0 && t.length() == 0) {
            return true;
        }
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            count[t.charAt(i)]--;
        }
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
};





/*
Older version:
Thoughts:
1. s.charAt(i) is in t.
2. remove that char in t and s.
3. if at then all both are empty, return true.
NOTE: cannot use chararray to sort, because that takes O(n) space
BUG solved: when editing string, be careful with index. Index builds on original string length, 
but string length changes over time. Solution: extra care on index, extra care on loop length change.
*/
public class Solution {
    public boolean anagram(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int j = t.indexOf(s.charAt(0));
            if (j == -1)  {
                return false;
            }
            if (s.charAt(0) == t.charAt(j)) {
                s = s.substring(1);
                if (j == t.length() - 1) {
                    t = t.substring(0, j);
                } else {
                    t = t.substring(0, j) + t.substring(j + 1);
                }
            } else {
                return false;
            }
        }
        if (s.length() == 0 && t.length() == 0) {
            return true;
        } 
        return false;
    }
};

```