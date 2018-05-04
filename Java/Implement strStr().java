E
1525220254
tags: Two Pointers, String

给两个string A, B, 找一个 B 在 A 种的起始位置.

#### Two Pointer
- 找到B在A中的起始位置, 然后看一下从这个点开始的substring是否等于B就可以了
- 还挺多坑的, 这些可以帮助优化:
- 1. 当B是“”的时候，也就是能在A的其实位置找到B....index = 0.
- 2. edge condition: 如果 haystack.length() < needle.length() 的话, 必须错, return -1
- 3. 如果在某个index, A后面剩下的长度, 比B的长度短, 也是误解, return -1

```
/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, 
or -1 if needle is not part of haystack.

Hide Company Tags Facebook
Hide Tags Two Pointers String
Hide Similar Problems (H) Shortest Palindrome

Clarification
Do I need to implement KMP Algorithm in an interview?

    - Not necessary. When this problem occurs in an interview, 
    the interviewer just want to test your basic implementation ability.

*/

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i < n; i++) {
            if (n - i < m) {
                return -1;
            }
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}

// Previous:
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        } 
        /*
        else if (haystack == null || haystack.length() == 0 
                   || haystack.length() < needle.length()) {
            return -1;
        }*/
        int i = 0;
        int j = 0;
        
        for (i = 0; i < haystack.length() - needle.length() + 1; i++) {
           // if (haystack.charAt(i + j) == needle.charAt(j)) {
                for (j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                }
                if (j == needle.length()) {
                    return i;
                }
           // }
        }
        return -1;
    }
}
```