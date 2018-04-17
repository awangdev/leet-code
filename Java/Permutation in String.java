M
1523946802
tags: Two Pointer

#### Two Pointer
- 如果做s1的permudation, 时间复杂度是O(n!) 肯定不可以
- 这里用HashTable的做法 (因为26字母, 所以用int[26]简化) 来记录window内的 character count
- 如果window内的character count 相等, 那么就是permudation
- 更进一步优化: 找两个map相互对应, 不如用一个 int[26]: s1对遇到的character做加法, s2对遇到的character做减法
- two pointer 运用在 n1, n2 的把控; 以及 s2.charAt(i - n1) 这一步

```
/*
Given two strings s1 and s2, write a function to return true 
if s2 contains the permutation of s1. In other words, 
one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() > s2.length()) {
            return false;
        }
        int n1 = s1.length();
        int n2 = s2.length();
        int[] charCount = new int[26];
        for (int i = 0; i < n1; i++) {
            charCount[s1.charAt(i) - 'a']++;
            charCount[s2.charAt(i) - 'a']--;
        }
        
        if (zeroCount(charCount)) {
            return true;
        }
        
        for (int i = n1; i < n2; i++) {
            charCount[s2.charAt(i) - 'a']--;
            charCount[s2.charAt(i - n1) - 'a']++;
            if (zeroCount(charCount)) {
                return true;
            }
        }     
        return false;
    }
    
    private boolean zeroCount(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```