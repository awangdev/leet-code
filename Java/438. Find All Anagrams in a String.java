M
tags: Hash Table, Sliding Window, Two Pointers
time: O(n)
space: O(1)

跟 Permutation in String 很像. 给短string p， 长string s.

找所有p的 anagram (permutation) 在s 里面的起始index.

#### Method1: Sliding Window, Hash Table. 
- A creative way of using anagram char count `hash[c] >= 0` to determine if the curr c is a target char of the deesired anagram.
    - because we always reduce hash[c]-- for all characters
    - so only the anagram chars would be `hash[c] >= 0` after reducing.
- https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
- Slinding window always has left/right pointer:
    - 1) at any given time move 1 index at a time: expand right window, process rsult, shrink left window
    - 2) one of the basic goal is to maintain fixed window size
- algo:
    - calc char freq of the target p, and store in a hash[256]; it will be used to distinguish anagram chars: `hash[c] >= 0` indicates a anagram char
    - expand right window: move right to expand the window; ONLY when meeting a anagram char, count--
    - process result: if count reduces to 0, one anagram is found
    - shrink left window: if (right - left) == p.length(), drop curr left char, and move forward
- how could we rely on only just `count == 0`? 
    - the hidden pre-condition is `right - left must already be p.length()`, which is validaterd in prev iteration
- time: O(n)
- space: O(1)

#### Method2: HashTable
- count character apperance -> hash table, here just a int[26]
    - use a window to record count++ and count--, in order to compare with countP
- prep the countP takes O(m) time
- time: O(n) + O(m)
- space: O(n)



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


// Method1: Slide Window
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) return rst;
        
        int n = s.length(), m = p.length();

        int[] hash = new int[256]; // prep p hash, tracks char # 
        for (char c : p.toCharArray()) hash[c]++;
        
        int left = 0, right = 0;
        int count = m; // count of anagram chars. after reducing == 0, found 1 anagram
        
        while (right < n) {
            // move right side of the window, if c exist in window, decrease count
            char c = s.charAt(right++);
            if (hash[c] > 0) count--;
            hash[c]--; // if not exist in anagram, it'll drop to negative
            
            // if anagram char count reduced to 0, an anagram in s is found, mark start pos
            if (count == 0) rst.add(left);
            
            // full range reached, so need to slide the window:
            // 1. get val on left pos
            // 2. add count back (when it is a anagram candidate)
            // 3. + count back to hash 
            if (right - left == m) {
                c = s.charAt(left++); // pick up the left char that will be dropped in next step
                if (hash[c] >= 0) count++; // anagram candidate can be == 0 when evenly used; but can also be  > 0 when there are extra
                hash[c]++;
            }
        }
        return rst;
    }
}

/*
// Method2: HashTable
1. Two pointers with range of p.length(). O(n)
2. Use counter to count the character apperance in s and p.
3. For s, when moving the pointer, always -- on past index, and ++ on new index
4. Compare countS, countP => O(26)
*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) return rst;
        
        int n = s.length(), m = p.length();

        // prep the count and window
        int[] window = new int[26], countP = new int[26];
        for (int i = 0; i < m; i++) {
            window[s.charAt(i) - 'a']++;
            countP[p.charAt(i) - 'a']++;
        }
        
        // compare at index = 0
        if (compare(window, countP)) rst.add(0);
        
        for (int i = m; i < n; i++) {
            window[s.charAt(i) - 'a']++;
            window[s.charAt(i - m) - 'a']--;
            if (compare(window, countP)) rst.add(i - m + 1);
        }
        
        return rst;
    }
    
    private boolean compare(int[] window, int[] countP) {
        for (int i = 0; i < 26; i++) {
            if (window[i] != countP[i]) return false;
        }
        return true;
    }
}



/*
NOT recommend:
1. Two pointers with range of p.length(). O(n)
2. isAnagram(a, b). O(n)
=> Overall O(nm), bad solution
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