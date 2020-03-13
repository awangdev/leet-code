E
tags: Hash Table, Sort
time: O(n)
space: O(1), unique chars

#### int[26]

#### HashMap<Character, Integer>

```
/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


*/
/*
Thoughts:
Anagram: reorder of letters.
Use HashMap<charactor, count> to store the frequency of chars of 1st string, and check aginst 2nd string.
s character: +1;
t character: -1;
check count of each index in the map; they should all be 0
*/

/*
Thoughts: if only lower case letters, use int[26] for simplicity
*/
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        if (s.equals(t)) return true;
        int[] count = countChars(s, t);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) return false;
        }
        return true;
    }

    private int[] countChars(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'] += 1;
            count[t.charAt(i) - 'a'] -= 1;
        }
        return count;
    }
}

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        if (s.equals(t)) return true;
        Map<Character, Integer> count = new HashMap<>();
        countChars(s, count, 1);
        countChars(t, count, -1);

        for (Map.Entry<Character, Integer> entry: count.entrySet()) {
            if (entry.getValue() != 0) return false;
        }
        return true;
    }

    private void countChars(String s, Map<Character, Integer> count, int val) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count.putIfAbsent(c, 0);
            count.put(c, count.get(c) + val);
        }
    }
}



```