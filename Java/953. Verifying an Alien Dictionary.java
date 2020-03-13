E
tags: Hash Table
time: O(nm)
space: O(1)

#### Hash Table
- mark the char position
- check adjacent words
- Optimization
    - a) If s1 equals s2, just return true, no need to continue.
    - b) if s2 (app) is a substring of s1(apple), just return false.


```
/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

*/
// O(MN). M = words size, N = individual words size
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null) return false;

        int[] orderIndex = new int[256];
        for(int i = 0; i < order.length(); i++) {
            orderIndex[order.charAt(i)] = i; // preserve order # for each char.
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            if (!compareStr(words[i], words[i+1], orderIndex)) return false;
        }
        return true;
    }
    
    private boolean compareStr(String s1, String s2, int[] orderIndex) {
        if (s1.indexOf(s2) >= 0) return false;

        int size = Math.min(s1.length(), s2.length());
        for (int i = 0; i < size; i++) {
            int c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (orderIndex[c1] == orderIndex[c2]) continue;
            return orderIndex[c1] < orderIndex[c2];
        }
        return true;
    }
}

/**
Leetcode discussion solution https://leetcode.com/problems/verifying-an-alien-dictionary/discuss/203185/JavaC%2B%2BPython-Mapping-to-Normal-Order

Uses integer to compare. If s1.char[0] - s2.char[0] > 0, retrun false
It's same idea as my solution. 
I think mine is easier to read.
*/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // ... same
        
        for (int i = 0; i < words.length - 1; i++) {
            if (compareStr(words[i], words[i+1], orderIndex) > 0) return false;
        }
        return true;
    }
    
    private int compareStr(String s1, String s2, int[] orderIndex) {
        // ...
        // return corresponding s1/s2 index diff
    }
}


```