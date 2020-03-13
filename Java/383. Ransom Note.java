E
1558026139
tags:String, Basic Implementation

count chars in int[256]

```
/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/

/*
count ransom note letter occurrence, and count magazine letters
compare occurrence
edge: null ransom -> true; null or shorter magazine, false
*/
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null) return true;
        if (magazine == null || magazine.length() < ransomNote.length()) return false;

        int[] count = new int[256];
        for (char c : magazine.toCharArray()) {
            count[c]++;
        }

        for (char c : ransomNote.toCharArray()) {
            count[c]--;
            if (count[c] < 0) {
                return false;
            }
        }

        return true;
    }
}

```