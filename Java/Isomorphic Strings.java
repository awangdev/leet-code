E

HashMap 来确认match。有几种情况考虑:

1. Match. 就是map.containsKey, map.containsValue, and char1 == char2. Perfect.

2. Either Key not exist, or Value not exit. False;

3. Both key and Value exist, but map.get(char1) != char2.  Miss-match. False.

4. None of Key or Value exist in HashMap. Then add the match.

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


//Use hashMap<Char,Char> to store matches. If conflict, return false
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        } else if (s.equals(t)) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            if (!map.containsKey(charS) && !map.containsValue(charT)) {
                map.put(charS, charT);
            } else if (!map.containsKey(charS) || !map.containsValue(charT)) {
                return false;
            } else if (map.containsKey(charS) && map.containsValue(charT) && map.get(charS) != charT) {
                return false;
            } 
        }
        return true;
    }
}
```
