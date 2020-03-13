E
tags: String

#### Palindrome String
- delete an index: 有两种情况
- 用一个boolean parameter来表现state. 如果有其他status, state可以变成 String/enum

```
/*
Given a non-empty string s, you may delete at most one character. 
Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

*/

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        return validate(s, 0, s.length() - 1, true);
    }
    
    public boolean validate(String s, int start, int end, boolean state) {
        if (start > end) return false;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
                continue;
            } else if (state) {
                return validate(s, start + 1, end, false) || validate(s, start, end - 1, false);
            }
            return false;
        }
        return true;
    }
}

// Simplify start++, end--
class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        
        return validate(s, 0, s.length() - 1, true);
    }
    
    public boolean validate(String s, int start, int end, boolean state) {
        if (start > end) return false;
        while (start < end) {
            if (s.charAt(start++) == s.charAt(end--)) {
                continue;
            } else if (state) {
                return validate(s, start - 1, end, false) || validate(s, start, end + 1, false);
            }
            return false;
        }
        return true;
    }
}

```