E
tags: Hash Table
time: O(n)
space: O(n)

#### HashMap
- check 2 failture cases:
    - same key, value not matching
    - two key maps to same value

```
/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

Hide Company Tags LinkedIn
Hide Tags Hash Table
Hide Similar Problems (E) Word Pattern

*/

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i), charT = t.charAt(i);
            if (map.containsKey(charS)) {
                if (map.get(charS) != charT) return false; // same key, value not matching
            } else {
                if (map.containsValue(charT)) return false; // two key maps to same value
                map.put(charS, charT);
            }
        }
        
        return true;
    }
}

```
