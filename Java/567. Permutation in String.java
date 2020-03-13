M
tags: Two Pointers, Sliding Window
time: O(m + n)
space: O(1)

#### Method1: Sliding window with left/right Pointers
- Sliding window template:
    - 1) Check right pointer and move right
    - 2) Move left when necessary
    - 3) Verify count == 0 & end state
    - Note: normally 2) and 3) are in reversed order; this problem is a bit different
- This is efficient when the number of characters is not limited to 26, the runtime is still O(m + n)
- time: O(m + n), m = s1 length, n = s2 length
- space: O(k), k = # of possible chars, 26 in this case

#### Method2: Two Pointer, but brutle verify freq count
- 如果做s1的permudation, 时间复杂度是O(n!) 肯定不可以
- 这里用HashTable的做法 (因为26字母, 所以用int[26]简化) 来记录window内的 character count
- 如果window内的character count 相等, 那么就是permudation
- 更进一步优化: 找两个map相互对应, 不如用一个 int[26]: s1对遇到的character做加法, s2对遇到的character做减法
- two pointer 运用在 n1, n2 的把控; 以及 s2.charAt(i - n1) 这一步
- time: (m + n)
    - However, if # of possible chars is more than 26
    - For example, `k unique characters`, then the runtime will become: O(m + nk)
    
- space: O(k), k = # of possible chars, 26 in this case

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

// Method1: Sliding Window Two Pointers. left/right. O(n + m)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) return false;

        Integer[] freq = countFreq(s1);
        int n = s2.length(), left = 0, right = 0;
        int count = s1.length();
        while (right < n) {
            char c = s2.charAt(right);
            // Verify and move right
            if (freq[c] == null) {// reset
                freq = countFreq(s1);
                left = ++right;
                count = s1.length();
                continue;
            }
            right++;
            count--;
            freq[c]--;
            
            // Move left when necessary
            while(freq[c] < 0) {
                freq[s2.charAt(left++)]++;
                count++;
            }
            
            // verify:
            if (count == 0) return true;
        }
        return count == 0;
    }
    
    private Integer[] countFreq(String s) {
        Integer[] freq = new Integer[256];
        for (char c : s.toCharArray()) freq[c] = freq[c] == null ? 1 : freq[c] + 1;
        return freq;
    }
}

// Method2: Optoin2, slight improvement by manually moving left when `freq[c]==null`,
// rather than calling `freq = countFreq(s1)`;
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) return false;

        Integer[] freq = countFreq(s1);
        int n = s2.length(), left = 0, right = 0;
        int count = s1.length();
        while (right < n) {
            char c = s2.charAt(right);
            // Verify and move right
            if (freq[c] == null) {
                while (left != right) { // reset
                    c = s2.charAt(left++);
                    if (freq[c] != null) {
                        freq[c]++;
                        count++;   
                    }
                }
                left = ++right;
                count = s1.length();
                continue;
            }
            right++;
            count--;
            freq[c]--;
            
            // Move left when necessary
            while(freq[c] < 0) {
                freq[s2.charAt(left++)]++;
                count++;
            }
            
            // verify:
            if (count == 0) return true;
        }
        return count == 0;
    }
    
    private Integer[] countFreq(String s) {
        Integer[] freq = new Integer[256];
        for (char c : s.toCharArray()) freq[c] = freq[c] == null ? 1 : freq[c] + 1;
        return freq;
    }
}


// Method2: Two Pointer, brutle check by updating freq map O(n + m)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() > s2.length()) {
            return false;
        }
        int m = s1.length(), n = s2.length();
        int[] charCount = new int[26];
        for (int i = 0; i < m; i++) {
            charCount[s1.charAt(i) - 'a']++;
            charCount[s2.charAt(i) - 'a']--;
        }
        
        if (zeroCount(charCount)) return true;
        
        for (int i = m; i < n; i++) {
            charCount[s2.charAt(i) - 'a']--;
            charCount[s2.charAt(i - m) - 'a']++;
            if (zeroCount(charCount)) return true;
        }     
        return false;
    }
    
    private boolean zeroCount(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}
```