E
1524100532
tags: Hash Table, Sliding Window

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### HashTable
- count character apperance 就想到hashtable
- 注意countS, countP 的技巧: 作比较只需要O(26)
- Overall timeO(n)
- 小心不要用一个int[] count 来技术 查0, 复杂度是O(n)

```
/**
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/
/*
Thoughts:
1. Two pointers with range of p.length(). O(n)
2. Use counter to count the character apperance in s and p.
3. For s, when moving the pointer, always -- on past index, and ++ on new index
4. Compare countS, countP => O(26)
Overall O(n)
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return rst;
        }
        
        int n = p.length();
        int[] countS = new int[26];
        int[] countP = new int[26];
        for (int i = 0; i < n; i++) {
            countS[s.charAt(i) - 'a']++;
            countP[p.charAt(i) - 'a']++;
        }
        
        if (compare(countS, countP)) {
            rst.add(0);
        }
        
        for (int i = n; i < s.length(); i++) {
            countS[s.charAt(i) - 'a']++;
            countS[s.charAt(i - n) - 'a']--;
            if (compare(countS, countP)) {
                rst.add(i - n + 1);
            }
        }
        
        return rst;
    }
    
    private boolean compare(int[] countS, int[] countP) {
        for (int i = 0; i < 26; i++) {
            if (countS[i] != countP[i]) {
                return false;
            }
        }
        return true;
    }
}

/*
Thoughts:
1. Two pointers with range of p.length(). O(n)
2. isAnagram(a, b). O(n)
=> Overall O(n^2)
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return rst;
        }
        if (s.length() == p.length() && isAnagram(s, p)) {
            rst.add(0);
            return rst;
        }

        int i = 0;
        int j = p.length();
        while (j <= s.length()) {
            if (isAnagram(s.substring(i, j), p)) {
                rst.add(i);
            }
            i++;
            j++;
        }
        
        return rst;
    }
    
    private boolean isAnagram(String a, String b) {
        int[] arr = new int[26];
        for (int i = 0; i < a.length(); i++) {
            arr[a.charAt(i) - 'a']++;
            arr[b.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }
}




```