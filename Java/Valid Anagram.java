E
1519713982
tags: Hash Table, Sort

HashMap

```
/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

Tags: Hash Table, Sort
Similar Problems: (M) Group Anagrams, (E) Palindrome Permutation


*/
/*
Thoughts:
Anagram: reorder of letters.
Use HashMap<charactor, count> to store the frequency of chars of 1st string, and check aginst 2nd string.
s character: +1;
t character: -1;
check count of each index in the map; they should all be 0
*/


class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        final Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if (!charMap.containsKey(charS)) {
                charMap.put(charS, 0);
            }
            if (!charMap.containsKey(charT)) {
                charMap.put(charT, 0);
            }
            charMap.put(charS, charMap.get(charS) + 1);
            charMap.put(charT, charMap.get(charT) - 1);
        }
        for (Map.Entry<Character, Integer> entry: charMap.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }
}


/*
Thoughts: if only lower case letters, use int[26] for simplicity
*/
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a'] += 1;
            chars[t.charAt(i) - 'a'] -= 1;
        }
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```