还挺多坑的.
1. border condition。 如果 haystack.length() < needle.length() 的话，必须错。但是这个可以优化省略。
2. 当S2是“”的时候，也就是能在s1的其实位置找到s2....index = 0.
3. 记得如何在s1里面找s2. 就是把遍历s1的 i , 加上遍历s2的 j。

优化：
1. s1, s2长短可以不比较。因为forloop的时候： s1.length() - s2.length() + 1，如果s2长于s1,这里自然就断了。
2. if(s1.charAT(i+j) == s2.charAT(j)). 可以省略。For loop 里面就Check到这个了。
```
/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Hide Company Tags Facebook
Hide Tags Two Pointers String
Hide Similar Problems (H) Shortest Palindrome

*/
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