E
1524100532
tags: Hash Table

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### HashTable
- count character apperance 就想到hashtable
- 注意countS, countP 的技巧: 作比较只需要O(26)
- Overall timeO(n)
- 小心不要用一个int[] count 来技术 查0, 复杂度是O(n)

```
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