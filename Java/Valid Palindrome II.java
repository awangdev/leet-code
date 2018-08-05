E
1533454056
tags: String

#### Palindrome String
- delete an index = jump over the index
- 注意 boolean chance 可以用一个helper function

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
    
    public boolean validate(String s, int start, int end, boolean chance) {
        if (start > end) return false;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
                continue;
            } else if (chance) {
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
    
    public boolean validate(String s, int start, int end, boolean chance) {
        if (start > end) return false;
        while (start < end) {
            if (s.charAt(start++) == s.charAt(end--)) {
                continue;
            } else if (chance) {
                return validate(s, start - 1, end, false) || validate(s, start, end + 1, false);
            }
            return false;
        }
        return true;
    }
}

```