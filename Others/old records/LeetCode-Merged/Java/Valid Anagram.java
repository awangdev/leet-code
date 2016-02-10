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


public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
        	return s == null && t == null;
        } else if (s.length() != t.length()) {
        	return false;
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
        	if (!map.containsKey(s.charAt(i))) {
        		map.put(s.charAt(i), 1);
        	} else {
        		map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        	}
        	if (!map.containsKey(t.charAt(i))) {
        		map.put(t.charAt(i), -1);
        	} else {
        		map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
        	}
        }//END for
        
        for (int i = 0; i < s.length(); i++) {
        	if (map.get(s.charAt(i)) != 0) {
        		return false;
        	}
        }
        return true;
    }
}